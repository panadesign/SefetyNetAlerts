package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.AlertChildDto;
import com.openclassrooms.safetynet.dto.PersonInfoDto;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonManagerImpl implements PersonManager {
	
	@Autowired
	private DataStorage dataStorage;
	
	public void addPerson(com.openclassrooms.safetynet.model.Person person) {
		dataStorage.getData().getPersons()
				.add(person);
	}
	
	public Set<String> getAllMailsByCity(String city) {
		return dataStorage
				.getData()
				.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(com.openclassrooms.safetynet.model.Person::getEmail)
				.collect(Collectors.toSet());
	}
	
	//http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	public List<PersonInfoDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<PersonInfoDto> personInfoDto = new ArrayList<>();
		
		for (Person person : persons) {
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
		
		List<com.openclassrooms.safetynet.model.Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<AlertChildDto> alertChildDto = new ArrayList<>();
		
		for (MedicalRecord medicalRecord : medicalRecords) {
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
	
}
