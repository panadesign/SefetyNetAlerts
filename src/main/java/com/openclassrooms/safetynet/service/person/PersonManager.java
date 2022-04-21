package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.getChildrenByAddressDto;
import com.openclassrooms.safetynet.dto.getFamiliesByStationDto;
import com.openclassrooms.safetynet.dto.getPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonManager {
	void addPerson(Person person);
	void updatePerson(Person person);
	void deletePerson(Person person);
	Set<String> getAllMailsByCity(String city);
	List<getPersonByFirstNameAndLastNameDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName);
	Set<getChildrenByAddressDto> getChildrenByAddress(String address);
	Map<String, List<getFamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations);
}
