package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Person;

public interface IPerson {
	public void addPerson(Person person);
	public void updatePerson();
	public void deletePerson();
	public void allMailByCity();

}
