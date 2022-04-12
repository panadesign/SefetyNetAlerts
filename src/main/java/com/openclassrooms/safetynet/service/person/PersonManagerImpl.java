package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.AlertChildDto;
import com.openclassrooms.safetynet.dto.FloodDto;
import com.openclassrooms.safetynet.dto.PersonInfoDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Id;
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
		dataStorage.getData().getPersons()
				.add(person);
	}

	public Set<String> getAllMailsByCity(String city) {
		return dataStorage
				.getData()
				.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}

	//http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	public List<PersonInfoDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {

		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();

		List<PersonInfoDto> personInfoDto = new ArrayList<>();

		for(Person person : persons) {
			List<PersonInfoDto> aggregate =
					medicalRecords.stream()
							.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
							.filter(m -> m.getLastName().equals(lastName))
							.map(medicalRecord -> new PersonInfoDto(person, medicalRecord))
							.collect(Collectors.toList());

			personInfoDto.addAll(aggregate);
		}

		return personInfoDto;
	}

	//http://localhost:8080/childAlert?address=<address>
	public List<AlertChildDto> getChildByAddress(String address) {

		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();

		List<AlertChildDto> alertChildDto = new ArrayList<>();

		for(MedicalRecord medicalRecord : medicalRecords) {
			List<AlertChildDto> getAllPersonsByAddress =
					persons
							.stream()
							.filter(person -> person.getId().equals(medicalRecord.getId()))
							.filter(person -> person.getAddress().equals(address))
							.map(person -> new AlertChildDto(person, medicalRecord))
							.collect(Collectors.toList());


			alertChildDto.addAll(getAllPersonsByAddress);
		}

		return alertChildDto;
	}

	public Map<String, List<FloodDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations) {
		List<Person> persons = dataStorage.getData().getPersons();
		List<FireStation> fireStations = dataStorage.getData().getFirestations();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();

		List<String> allAddressesByStationNumber = new ArrayList<>();
		Map<String, List<FloodDto>> allPersons = new HashMap<>();

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
			List<FloodDto> getAllPersonsIdWithThisAddress =
					persons
							.stream()
							.filter(person -> person.getAddress().equals(addresses))
							.map(person -> new FloodDto(person,
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
