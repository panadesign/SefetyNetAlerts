package com.openclassrooms.safetynet.service.person;
import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.repository.Data;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonManagement implements IPerson{
	@Autowired
	ParseJSON parseJSON;
	@Autowired
	Data data;

	public void addPerson(Person person) {
		data.getPersons();
		addPerson(person);
	}

	public void updatePerson() {
	}

	public void deletePerson() {
	}

	public Set<String> getAllMailsByCity(String city) {
		Data data = parseJSON.getData();
		Set<String> allMailsWithoutDuplicates = data.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail).collect(Collectors.toSet());
		return allMailsWithoutDuplicates;
	}


}
