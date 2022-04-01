package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.FireDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Set;

public interface IFirestation {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<Person> getPeopleByFirestationNumber(int station);
	public Set<String> getPhoneNumberByFirestationNumber(int station);
	public List<Person> getPeoplesByAddressAndFirestationNumber(String address);
}
