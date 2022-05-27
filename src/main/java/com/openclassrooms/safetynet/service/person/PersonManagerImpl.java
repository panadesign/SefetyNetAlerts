package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
public class PersonManagerImpl implements PersonManager {

	private DataStorage dataStorage;

	@Autowired
	public PersonManagerImpl(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public PersonManagerImpl() {
	}

	public void addPerson(Person person) {

		log.debug("Add a person: " + person.getLastName() + " " + person.getFirstName());

		Optional<Person> optionalPerson =
				dataStorage.getPersonById(person.getId());

		if(optionalPerson.isPresent()) {
			log.error("Impossible to create this person, this person exist already");
			throw new BadRequestExceptions("This person exist already");
		}

		dataStorage
				.getPersons()
				.add(person);
		log.info("person has been added");
	}

	public void updatePerson(Person person) {

		log.debug("Update a person: " + person.getLastName() + " " + person.getFirstName());

		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());

		if(optionalPerson.isPresent()) {
			int indexOfPerson = dataStorage.getPersons().indexOf(optionalPerson.get());

			dataStorage
					.getPersons()
					.set(indexOfPerson, person);
			log.info("person has been updated");

		} else {
			log.error("Impossible to update this person, this person doesn't exist");
			throw new BadRequestExceptions("This person doesn't exist");

		}

	}

	public void deletePerson(Person person) {
		log.debug("Delete a person: " + person.getLastName() + " " + person.getFirstName());

		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());

		if(optionalPerson.isPresent()) {

			dataStorage
					.getPersons()
					.remove(optionalPerson.get());

			log.info("person has been removed");

		} else {
			log.error("Impossible to delete this person, this person doesn't exist");
			throw new BadRequestExceptions("This person doesn't exist");
		}
	}

	public Set<String> getAllMailsByCity(String city) {
		log.debug("Get all mails in: " + city);
		return dataStorage
				.getPersons()
				.stream()
				.filter(p -> city.equals(p.getCity()))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}

	public List<PersonByFirstNameAndLastNameDto> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		log.debug("Get all persons by address: " + firstName + " " + lastName);

		return dataStorage.getPersons()
				.stream()
				.filter(p -> lastName.equals(p.getLastName()))
				.map(person -> dataStorage.getMedicalrecords()
						.stream()
						.filter(medicalrecord -> medicalrecord.getId().equals(person.getId()))
						.map(medicalrecord -> new PersonByFirstNameAndLastNameDto(person, medicalrecord))
						.findFirst().orElse(null))
				.collect(Collectors.toList());

	}

	public Map<String, List<FamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations) {
		log.debug("Get all persons by address: " + stations);

		List<Medicalrecord> medicalrecords = dataStorage.getMedicalrecords();

		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<FamiliesByStationDto>> allPersons = new HashMap<>();

		for(Integer firestationNumber : stations) {
			List<String> getAllAddressesByStationNumber =
					dataStorage
							.getFirestations()
							.stream()
							.filter(firestation -> firestation.getStation() == firestationNumber)
							.map(Firestation::getAddress)
							.collect(Collectors.toList());
			allAddressesByStationNumber.addAll(getAllAddressesByStationNumber);
			log.info("get all addresses by station number");
		}

		for(String address : allAddressesByStationNumber) {
			List<FamiliesByStationDto> getAllPersonsWithThisAddress =
					dataStorage
							.getPersons()
							.stream()
							.filter(person -> person.getAddress().equals(address))
							.map(person -> {
										Medicalrecord m = medicalrecords
												.stream()
												.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
												.findFirst()
												.orElseThrow(() -> new BadRequestExceptions(""));
										return new FamiliesByStationDto(person, m);
									}
							)
							.collect(Collectors.toList());
			allPersons.put(address, getAllPersonsWithThisAddress);
			log.info("Get all persons by address");
		}

		return allPersons;

	}

	public ChildListAndFamilyListDto getChildrenByAddress(String address) {
		log.debug("Get children by address: " + address);

		List<Medicalrecord> medicalrecords = dataStorage.getMedicalrecords();

		List<ChildrenByAddressDto> getChild = new ArrayList<>();
		List<AdultsByAddressDto> getAdults = new ArrayList<>();
		ChildListAndFamilyListDto childListAndFamilyListDto = new ChildListAndFamilyListDto(getChild, getAdults);

		for(Medicalrecord medicalRecord : medicalrecords) {
			List<ChildrenByAddressDto> getChildrenByAddress =
					dataStorage
							.getPersons()
							.stream()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new ChildrenByAddressDto(person, medicalRecord))
							.filter(ChildrenByAddressDto::isMinor)
							.collect(Collectors.toList());

			getChild.addAll(getChildrenByAddress);
			log.info("Get child by address");
		}

		for(Medicalrecord medicalrecord : medicalrecords) {
			if(!getChild.isEmpty()) {
				List<AdultsByAddressDto> getAdultByAddress =
						dataStorage
								.getPersons()
								.stream()
								.filter(person -> person.getId().equals(medicalrecord.getId()))
								.filter(person -> person.getAddress().equals(address))
								.map(person -> new AdultsByAddressDto(person, medicalrecord))
								.filter(AdultsByAddressDto::iSMajor)
								.collect(Collectors.toList());

				getAdults.addAll(getAdultByAddress);
				log.info("Get adults by address");
			}
		}

		childListAndFamilyListDto.setGetChildrenByAddressDto(getChild);
		childListAndFamilyListDto.setGetAdultsByAddressDto(getAdults);
		log.info("List of child by address and adult by address");
		return childListAndFamilyListDto;
	}
}
