package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
public class PersonManagerImpl implements PersonManager {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	public PersonManagerImpl(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public void addPerson(Person person) {

		log.debug("Add a person: " + person.getLastName() + " " + person.getFirstName());

		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());

		if(optionalPerson.isPresent()) {
			log.error("Impossible to create this person, this person exist already");
			throw new RuntimeException("This person exist already");
		}

		dataStorage
				.getData()
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

			int indexOfPerson = dataStorage.getData().getPersons().indexOf(optionalPerson.get());

			dataStorage
					.getData()
					.getPersons()
					.set(indexOfPerson, person);
			log.info("person has been updated");

		} else {
			log.error("Impossible to update this person, this person doesn't exist");
			throw new RuntimeException("This person doesn't exist");

		}

	}

	public void deletePerson(Person person) {

		log.debug("Delete a person: " + person.getLastName() + " " + person.getFirstName());

		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());

		if(optionalPerson.isPresent()) {

			dataStorage
					.getData()
					.getPersons()
					.remove(optionalPerson.get());

			log.info("person has been removed");

		} else {
			log.error("Impossible to delete this person, this person doesn't exist");
			throw new RuntimeException("This person doesn't exist");
		}
	}

	public Set<String> getAllMailsByCity(String city) {
		log.debug("Get all mails by the city: " + city);
		return dataStorage
				.getPersons()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}

	public List<GetPersonByFirstNameAndLastNameDto> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		log.debug("Get all persons by address: " + firstName + " " + lastName);

		List<Person> persons = dataStorage.getData().getPersons();
		List<GetPersonByFirstNameAndLastNameDto> personInfoDto = new ArrayList<>();

		for(Person person : persons) {
			List<GetPersonByFirstNameAndLastNameDto> aggregate =
					dataStorage.getMedicalRecord()
							.filter(medicalrecord -> medicalrecord.getId().equals(person.getId()))
							.filter(m -> m.getLastName().equals(lastName))
							.map(medicalrecord -> new GetPersonByFirstNameAndLastNameDto(person, medicalrecord))
							.collect(Collectors.toList());

			personInfoDto.addAll(aggregate);
		}

		return personInfoDto;
	}

	public Map<String, List<GetFamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations) {
		log.debug("Get all persons by address: " + stations);

		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();

		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<GetFamiliesByStationDto>> allPersons = new HashMap<>();

		for(Integer firestationNumber : stations) {
			List<String> getAllAddressesByStationNumber =
					dataStorage
							.getFirestations()
							.filter(firestation -> firestation.getStation() == firestationNumber)
							.map(Firestation::getAddress)
							.collect(Collectors.toList());
			allAddressesByStationNumber.addAll(getAllAddressesByStationNumber);
		}

		for(String addresses : allAddressesByStationNumber) {
			List<GetFamiliesByStationDto> getAllPersonsIdWithThisAddress =
					dataStorage
							.getPersons()
							.filter(person -> person.getAddress().equals(addresses))
							.map(person -> new GetFamiliesByStationDto(person,
									medicalrecords
											.stream()
											.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
											.findFirst().orElse(null))
							)
							.collect(Collectors.toList());
			allPersons.put(addresses, getAllPersonsIdWithThisAddress);
		}

		return allPersons;

	}

	public GetChildListAndFamilyListDto getChildrenByAddress(String address) {
		log.debug("Get children by address: " + address);

		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();

		List<GetChildrenByAddressDto> getChild = new ArrayList<>();
		List<GetAdultsByAddressDto> getAdults = new ArrayList<>();
		GetChildListAndFamilyListDto getChildListAndFamilyListDto = new GetChildListAndFamilyListDto(getChild, getAdults);

		for(Medicalrecord medicalRecord : medicalrecords) {
			List<GetChildrenByAddressDto> getChildrenByAddress =
					dataStorage
							.getPersons()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new GetChildrenByAddressDto(person, medicalRecord))
							.filter(GetChildrenByAddressDto::isMinor)
							.collect(Collectors.toList());

			getChild.addAll(getChildrenByAddress);
			log.info("Child by address");
		}

		for(Medicalrecord medicalrecord : medicalrecords) {
			if(getChild.size() > 0) {
				List<GetAdultsByAddressDto> getAdultByAddress =
						dataStorage
								.getPersons()
								.filter(person -> person.getId().equals(medicalrecord.getId()))
								.filter(person -> person.getAddress().equals(address))
								.map(person -> new GetAdultsByAddressDto(person, medicalrecord))
								.filter(GetAdultsByAddressDto::iSMajor)
								.collect(Collectors.toList());

				getAdults.addAll(getAdultByAddress);
				log.info("Adult by address");
			}
		}

		getChildListAndFamilyListDto.setGetChildrenByAddressDto(getChild);
		getChildListAndFamilyListDto.setGetAdultsByAddressDto(getAdults);
		log.info("List of child by address and adult by address");
		return getChildListAndFamilyListDto;
	}
}
