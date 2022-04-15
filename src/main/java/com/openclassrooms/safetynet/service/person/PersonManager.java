package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.AlertChildDto;
import com.openclassrooms.safetynet.dto.FloodDto;
import com.openclassrooms.safetynet.dto.PersonInfoDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonManager {
	void addPerson(Person person);
	void updatePerson(String lastName, String firstName);
	Set<String> getAllMailsByCity(String city);
	List<PersonInfoDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName);
	List<AlertChildDto> getChildByAddress(String address);
	Map<String, List<FloodDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations);
}
