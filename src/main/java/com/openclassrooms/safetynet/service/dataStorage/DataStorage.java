package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.*;

import java.util.List;
import java.util.Optional;

/**
 * The interface Data storage.
 */
public interface DataStorage {
	/**
	 * Method used to get all persons
	 *
	 * @return list of all persons
	 */
	List<Person> getPersons();

	/**
	 * Method used to get person by id
	 *
	 * @param id defined by firstName + lastName
	 * @return an optional of person by his id
	 */
	Optional<Person> getPersonById(Id id);

	/**
	 * Method used to get all persons with a defined address
	 *
	 * @param address used to get persons with this address
	 * @return a list of all persons with this address
	 */
	List<Person> getPersonsByAddress(String address);

	/**
	 * Method used to get all firestations
	 *
	 * @return a list of all firestations
	 */
	List<Firestation> getFirestations();

	/**
	 * Method used to get all firestations with a defined station
	 *
	 * @param station used to get firestations with this station
	 * @return a list of all firestations with this station
	 */
	List<Firestation> getFirestationsByNumber(Integer station);

	/**
	 * Method used to get all firestations with a defined address
	 *
	 * @param address used to get firestations with this address
	 * @return a list of all firestations with this address
	 */
	List<Firestation> getFirestationsByAddress(String address);

	/**
	 * Method used to get all medicalrecords
	 *
	 * @return a list of all medicalrecords
	 */
	List<Medicalrecord> getMedicalrecords();

	/**
	 * Method used to get medicalrecord by id
	 *
	 * @param id defined by firstName + lastName
	 * @return an optional of medicalrecord by his id
	 */
	Optional<Medicalrecord> getMedicalRecordById(Id id);

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	Data getData();
	
}
