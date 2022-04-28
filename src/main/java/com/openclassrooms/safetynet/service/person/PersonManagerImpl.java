package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonManagerImpl implements PersonManager {
	
	@Autowired
	private DataStorage dataStorage;
	
	@Autowired
	public PersonManagerImpl(DataStorage dataStorage) {this.dataStorage = dataStorage;}
	
	public void addPerson(Person person) {
		
		Logger.debug("Add a person: " + person);
		
		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());
		
		if (optionalPerson.isPresent()) {
			throw new RuntimeException("This person exist already");
		}
		
		dataStorage
				.getData()
				.getPersons()
				.add(person);
	}
	
	public void updatePerson(Person person) {
		
		Logger.debug("Update a person: " + person);
		
		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());
		
		if (optionalPerson.isPresent()) {
			
			int indexOfPerson = dataStorage.getData().getPersons().indexOf(optionalPerson.get());
			
			dataStorage
					.getData()
					.getPersons()
					.set(indexOfPerson, person);
			
		} else {
			throw new RuntimeException("This person doesn't exist");
		}
		
	}

	public void deletePerson(Person person) {

		Logger.debug("Delete a person: " + person);

		Optional<Person> optionalPerson =
				dataStorage
						.getPersonById(person.getId());

		if (optionalPerson.isPresent()) {

			dataStorage
					.getData()
					.getPersons()
					.remove(optionalPerson.get());

		} else {
			throw new RuntimeException("This person doesn't exist");
		}
	}
	
	public Set<String> getAllMailsByCity(String city) {
		Logger.debug("Get all mails by the city: " + city);
		return dataStorage
				.getPersons()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}
	
	public List<GetPersonByFirstNameAndLastNameDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {
		Logger.debug("Get all persons by address: " + firstName + " " + lastName);
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<GetPersonByFirstNameAndLastNameDto> personInfoDto = new ArrayList<>();
		
		for (Person person : persons) {
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
		Logger.debug("Get all persons by address: " + stations);
		
		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();
		
		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<GetFamiliesByStationDto>> allPersons = new HashMap<>();
		
		for (Integer firestationNumber : stations) {
			List<String> getAllAddressesByStationNumber =
					dataStorage
							.getFireStations()
							.filter(firestation -> firestation.getStation() == firestationNumber)
							.map(Firestation::getAddress)
							.collect(Collectors.toList());
			allAddressesByStationNumber.addAll(getAllAddressesByStationNumber);
		}
		
		for (String addresses : allAddressesByStationNumber) {
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
		Logger.debug("Get children by address: " + address);
		
		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();
		
		List<GetChildrenByAddressDto> getChild = new ArrayList<>();
		List<GetAdultsByAddressDto> getAdults = new ArrayList<>();
		GetChildListAndFamilyListDto getChildListAndFamilyListDto = new GetChildListAndFamilyListDto(getChild, getAdults);
		
		for (Medicalrecord medicalRecord : medicalrecords) {
			List<GetChildrenByAddressDto> getChildrenByAddress =
					dataStorage
							.getPersons()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new GetChildrenByAddressDto(person, medicalRecord))
							.filter(GetChildrenByAddressDto::isMinor)
							.collect(Collectors.toList());
			
			
			getChild.addAll(getChildrenByAddress);
		}
		for (Medicalrecord medicalrecord : medicalrecords) {
			if (getChild.size() > 0) {
				List<GetAdultsByAddressDto> getAdultByAddress =
						dataStorage
								.getPersons()
								.filter(person -> person.getId().equals(medicalrecord.getId()))
								.filter(person -> person.getAddress().equals(address))
								.map(person -> new GetAdultsByAddressDto(person, medicalrecord))
								.filter(GetAdultsByAddressDto::iSMajor)
								.collect(Collectors.toList());
				
				getAdults.addAll(getAdultByAddress);
			}
		}
		
		getChildListAndFamilyListDto.setGetChildrenByAddressDto(getChild);
		getChildListAndFamilyListDto.setGetAdultsByAddressDto(getAdults);
		return getChildListAndFamilyListDto;
	}
}
