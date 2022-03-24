package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public interface IFirestation {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<Person> getPeopleByFirestationNumber(int station);
}
