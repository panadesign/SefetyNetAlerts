package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.FireDto;
import com.openclassrooms.safetynet.dto.FirestationDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Set;

public interface IFirestation {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<FirestationDto> getPeopleByFirestationNumber(int stationNumber);
	public Set<String> getPhoneNumbersByFirestationNumber(int station);
	public List<FireDto> getPeoplesByAddress(String address);
}
