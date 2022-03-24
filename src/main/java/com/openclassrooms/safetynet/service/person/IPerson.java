package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Person;
import java.util.Set;

public interface IPerson {
	public void addPerson(Person person);
	public void updatePerson();
	public void deletePerson();
	public Set<String> getAllMailsByCity(String city);
}
