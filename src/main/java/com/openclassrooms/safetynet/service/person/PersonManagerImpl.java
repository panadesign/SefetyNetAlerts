package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.service.DataStorage;
import com.openclassrooms.safetynet.dto.getChildrenByAddressDto;
import com.openclassrooms.safetynet.dto.getFamiliesByStationDto;
import com.openclassrooms.safetynet.dto.getPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonManagerImpl implements PersonManager {
	
	private final DataStorage dataStorage;
	
	@Autowired
	public PersonManagerImpl(DataStorage dataStorage) {this.dataStorage = dataStorage;}
	
	public void addPerson(Person person) {
		
		Logger.debug("Add a person" + person);
		
		Optional<Person> optionalPerson = dataStorage.getPersons()
				.filter(p -> person.getLastName().equals(p.getLastName()) && person.getFirstName().equals(p.getFirstName()))
				.findFirst();
		
		if (optionalPerson.isPresent()) {
			throw new RuntimeException("This person exist already");
		}
		
		dataStorage
				.getData()
				.getPersons()
				.add(person);
	}
	
	public void updatePerson(Person person) {
		
		Logger.debug("Update a person" + person);
		
		Optional<Person> optionalPerson = dataStorage.getPersons()
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
				.findFirst();
		
		if (optionalPerson.isPresent()) {
			//recuperer son emplacement
			//remplacer la person par la person modifiée
			
			int indexOfPerson = dataStorage.getData().getPersons().indexOf(optionalPerson.get());
			
			dataStorage
					.getData()
					.getPersons()
					.set(indexOfPerson, new Person(person));
			
		} else {
			throw new RuntimeException("This person doesn't exist");
		}
		
	}
	
	public void deletePerson(Person person) {
		
		Logger.debug("Delete a person" + person);
		
		Optional<Person> optionalPerson = dataStorage.getPersons()
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
				.findFirst();
		
		if (optionalPerson.isPresent()) {
			//recuperer son emplacement
			//remplacer la person par la person modifiée
			
			int indexOfPerson = dataStorage.getData().getPersons().indexOf(optionalPerson);
			dataStorage
					.getData()
					.getPersons()
					.remove(indexOfPerson);
			
		} else {
			throw new RuntimeException("This person doesn't exist");
		}
		
	}
	
	public Set<String> getAllMailsByCity(String city) {
		Logger.debug("Get all mails by the city" + city);
		return dataStorage
				.getPersons()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}
	
	public List<getPersonByFirstNameAndLastNameDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {
		Logger.debug("Get all persons by address" + firstName + " " + lastName);
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<getPersonByFirstNameAndLastNameDto> personInfoDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<getPersonByFirstNameAndLastNameDto> aggregate =
					dataStorage.getMedicalRecord()
							.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
							.filter(m -> m.getLastName().equals(lastName))
							.map(medicalRecord -> new getPersonByFirstNameAndLastNameDto(person, medicalRecord))
							.collect(Collectors.toList());
			
			personInfoDto.addAll(aggregate);
		}
		
		return personInfoDto;
	}
	
	public Set<getChildrenByAddressDto> getChildrenByAddress(String address) {
		Logger.debug("Get children by address" + address);
		
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		Set<getChildrenByAddressDto> getChildWithFamily = new HashSet<>();
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			List<getChildrenByAddressDto> getChildrenByAddress =
					dataStorage.getPersons()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new getChildrenByAddressDto(person, medicalRecord))
							.filter(getChildrenByAddressDto::isMinor)
							.collect(Collectors.toList());
			
			getChildWithFamily.addAll(getChildrenByAddress);
		}
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			List<getChildrenByAddressDto> getAdultByAddress =
					dataStorage.getPersons()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new getChildrenByAddressDto(person, medicalRecord))
							.filter(person -> !person.isMinor())
							.collect(Collectors.toList());
			
			getChildWithFamily.addAll(getAdultByAddress);
		}
		
		return getChildWithFamily;
	}
	
	public Map<String, List<getFamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations) {
		Logger.debug("Get all persons by address" + stations);
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<FireStation> fireStations = dataStorage.getData().getFirestations();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<getFamiliesByStationDto>> allPersons = new HashMap<>();
		
		for (Integer fireStationNumber : stations) {
			List<String> getAllAddressesByStationNumber =
					fireStations
							.stream()
							.filter(fireStation -> fireStation.getStation() == fireStationNumber)
							.map(FireStation::getAddress)
							.collect(Collectors.toList());
			allAddressesByStationNumber.addAll(getAllAddressesByStationNumber);
		}
		
		for (String addresses : allAddressesByStationNumber) {
			List<getFamiliesByStationDto> getAllPersonsIdWithThisAddress =
					persons
							.stream()
							.filter(person -> person.getAddress().equals(addresses))
							.map(person -> new getFamiliesByStationDto(person,
									Objects.requireNonNull(medicalRecords
											.stream()
											.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
											.findFirst().orElse(null))
							))
							.collect(Collectors.toList());
			allPersons.put(addresses, getAllPersonsIdWithThisAddress);
		}
		
		return allPersons;
		
	}
}
