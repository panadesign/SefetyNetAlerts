package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.DataStorage;
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
public class PersonManagement implements IPerson {
	@Autowired
	private DataStorage dataStorage;
	
	public void addPerson(Person person) {
		dataStorage.getData().getPersons()
				.add(person);
	}
	
	public Set<String> getAllMailsByCity(String city) {
		return dataStorage.getData().getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}
	
	public List<PersonInfoDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName) {
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<PersonInfoDto> personInfoDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<PersonInfoDto> aggregate =
					medicalRecords.stream()
							.filter(medicalRecord -> medicalRecord.getFirstName().equals(person.getFirstName())
									&& medicalRecord.getLastName().equals(person.getLastName()) && person.getLastName().equals(lastName))
							.map(medicalRecord -> {
								try {
									return new PersonInfoDto(person, medicalRecord);
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							})
							.collect(Collectors.toList());
			
			personInfoDto.addAll(aggregate);
		}
		
		return personInfoDto;
	}
	
}
