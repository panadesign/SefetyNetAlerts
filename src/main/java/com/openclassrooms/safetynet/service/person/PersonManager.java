package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.GetChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.GetFamiliesByStationDto;
import com.openclassrooms.safetynet.dto.GetPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonManager {
	void addPerson(Person person);
	void updatePerson(Person person);
	void deletePerson(Person person);
	Set<String> getAllMailsByCity(String city);
	List<GetPersonByFirstNameAndLastNameDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName);
	GetChildListAndFamilyListDto getChildrenByAddress(String address);
	Map<String, List<GetFamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations);
}
