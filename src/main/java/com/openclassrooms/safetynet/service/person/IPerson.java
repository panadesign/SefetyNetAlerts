package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Set;

public interface IPerson {
	public Person addPerson(Person person);
	public Person updatePerson(String firstName, String lastName);
	//public Person deletePerson(Person person);
	public Set<Person> getAllMailsByCity(String city);
	public List<Person> getAllPersons(String firstName, String lastName);
}
