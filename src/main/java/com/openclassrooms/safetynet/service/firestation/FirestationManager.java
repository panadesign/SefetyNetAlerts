package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.GetPersonsByAddressDto;
import com.openclassrooms.safetynet.dto.GetPersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.type.NumberOfAdultsAndChildren;

import java.util.List;
import java.util.Set;

public interface FirestationManager {
	void addFirestation(Firestation firestation);
	void updateFirestation(Firestation firestation);
	void deleteFirestation(Firestation firestation);
	List<GetPersonsByStationDto> getPersonsByStation(int stationNumber);
	Set<String> getPhoneNumbersByFirestationNumber(int station);
	List<GetPersonsByAddressDto> getPersonsByAddress(String address);
	NumberOfAdultsAndChildren getNumbersOfChildrenAndAdultsByStation(int station);
}
