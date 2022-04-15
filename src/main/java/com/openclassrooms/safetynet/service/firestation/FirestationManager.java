package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.getPersonsByAddressDto;
import com.openclassrooms.safetynet.dto.getPersonsByStationDto;

import java.util.List;
import java.util.Set;

public interface FirestationManager {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<getPersonsByStationDto> getPeopleByFirestationNumber(int stationNumber);
	public Set<String> getPhoneNumbersByFirestationNumber(int station);
	public List<getPersonsByAddressDto> getPeoplesByAddress(String address);
}
