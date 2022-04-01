package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IPerson {
	public void addPerson(Person person);
	public Set<String> getAllMailsByCity(String city);
}
