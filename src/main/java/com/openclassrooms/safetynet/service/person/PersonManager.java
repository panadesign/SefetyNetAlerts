package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.ChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.FamiliesByStationDto;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonManager {
	void addPerson(Person person);
	void updatePerson(Person person);
	void deletePerson(Person person);
	Set<String> getAllMailsByCity(String city);
	List<PersonByFirstNameAndLastNameDto> getPersonsByFirstNameAndLastName(String firstName, String lastName);
	ChildListAndFamilyListDto getChildrenByAddress(String address);
	Map<String, List<FamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations);
}
