package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.AlertChildDto;
import com.openclassrooms.safetynet.dto.PersonInfoDto;

import java.util.List;
import java.util.Set;

public interface PersonManager {
	void addPerson(com.openclassrooms.safetynet.model.Person person);
	Set<String> getAllMailsByCity(String city);
	List<PersonInfoDto> getPersonsByAddressWithMedicalrecords(String firstName, String lastName);
	List<AlertChildDto> getChildByAddress(String address);
}
