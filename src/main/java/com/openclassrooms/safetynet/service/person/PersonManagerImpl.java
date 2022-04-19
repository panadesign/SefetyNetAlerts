package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.getChildrenByAddressDto;
import com.openclassrooms.safetynet.dto.getFamiliesByStationDto;
import com.openclassrooms.safetynet.dto.getPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonManagerImpl implements PersonManager {

	@Autowired
	private DataStorage dataStorage;

	public void addPerson(Person person) {
		dataStorage.getPersons()
				.add(person);
	}

	public void updatePerson(String firstName, String lastName) {
		List<Person> personList = dataStorage.getData().getPersons();

		for(Person person : personList) {
			List<getPersonByFirstNameAndLastNameDto> getPersonByFirstNameAndLastNameDto =
					personList.stream()
							.filter(person1 -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
							.map(person1 -> new getPersonByFirstNameAndLastNameDto(person))
							.collect(Collectors.toList());
		}

	}

	public Set<String> getAllMailsByCity(String city) {
		return dataStorage
				.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}

	public List<getPersonByFirstNameAndLastNameDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {

		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();

		List<getPersonByFirstNameAndLastNameDto> personInfoDto = new ArrayList<>();

		for(Person person : persons) {
			List<getPersonByFirstNameAndLastNameDto> aggregate =
					medicalRecords.stream()
							.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
							.filter(m -> m.getLastName().equals(lastName))
							.map(medicalRecord -> new getPersonByFirstNameAndLastNameDto(person, medicalRecord))
							.collect(Collectors.toList());

			personInfoDto.addAll(aggregate);
		}

		return personInfoDto;
	}

	public Set<getChildrenByAddressDto> getChildrenByAddress(String address) {

		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		Set<getChildrenByAddressDto> getChildWithFamily = new HashSet<>();
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			List<getChildrenByAddressDto> getChildrenByAddress =
					persons
							.stream()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new getChildrenByAddressDto(person, medicalRecord))
							.filter(getChildrenByAddressDto::isMinor)
							.collect(Collectors.toList());
			
			getChildWithFamily.addAll(getChildrenByAddress);
		}
		
		return getChildWithFamily;
	}

	public Map<String, List<getFamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations) {
		List<Person> persons = dataStorage.getData().getPersons();
		List<FireStation> fireStations = dataStorage.getData().getFirestations();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();

		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<getFamiliesByStationDto>> allPersons = new HashMap<>();

		for(Integer fireStationNumber : stations) {
			List<String> getAllAddressesByStationNumber =
					fireStations
							.stream()
							.filter(fireStation -> fireStation.getStation() == fireStationNumber)
							.map(FireStation::getAddress)
							.collect(Collectors.toList());
			allAddressesByStationNumber.addAll(getAllAddressesByStationNumber);
		}

		for(String addresses : allAddressesByStationNumber) {
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
