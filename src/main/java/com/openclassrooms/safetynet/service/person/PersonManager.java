package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.ChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.FamiliesByStationDto;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The interface Person manager.
 */
public interface PersonManager {
	/**
	 * Used to add a new person(firstName and lastName cannot be non-existent)
	 *
	 * @param person is defined by a firstName and a lastName
	 */
	void addPerson(Person person);

	/**
	 * Method used to update a person(to find the person to update, this method use firstName + lastName)
	 *
	 * @param person is defined by a firstName and a lastName
	 */
	void updatePerson(Person person);

	/**
	 * Method used to delete a person(to find the person to update, this method use firstName + lastName)
	 *
	 * @param person is defined by a firstName and a lastName
	 */
	void deletePerson(Person person);

	/**
	 * This method is used to get all mails of persons using city for parameter
	 *
	 * @param city is used to filter persons
	 * @return a list of mails without duplicate for city in parameter
	 */
	Set<String> getAllMailsByCity(String city);

	/**
	 * Method used to return a list of persons using firstName and lastName
	 *
	 * @param firstName is used like identifier
	 * @param lastName  is used like identifier
	 * @return using firstName and lastName in parameters, the person with the same firstName and lastName, and a list of persons with the same lastName. return for each person : firstName, lastName, email, age, list of medications and list of allergies
	 */
	List<PersonByFirstNameAndLastNameDto> getPersonsByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * This method using firestation number return persons by addresses
	 *
	 * @param stations define the number of the firestation, one or more stations can be used
	 * @return This method return a map of persons by address for flood alert return by address list of persons with their firstName, lastName, phone, age, list of medications and list of allergies
	 */
	Map<String, List<FamiliesByStationDto>> getPersonsByAddressStationForFloodAlert(List<Integer> stations);

	/**
	 * Method used to get all children by address and their family, and the number of child and number of adults
	 *
	 * @param address used to filter persons
	 * @return a list of child with firstName, lastName, phone and age and a list of Adults with same description
	 */
	ChildListAndFamilyListDto getChildrenByAddress(String address);
}
