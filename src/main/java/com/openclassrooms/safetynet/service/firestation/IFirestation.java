package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Set;

public interface IFirestation {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<Person> getPeopleByFirestationNumber(int station);
	public Set<Person> getPhoneNumberByFirestationNumber(int station);
}
