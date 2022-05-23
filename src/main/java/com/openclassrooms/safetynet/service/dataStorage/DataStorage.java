package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.*;

import java.util.List;
import java.util.Optional;

public interface DataStorage {
	/**
	 * Method used to get all persons
	 * @return list of all persons
	 */
	public List<Person> getPersons();
	
	/**
	 * Method used to get person by id
	 * @param id defined by firstName + lastName
	 * @return an optional of person by his id
	 */
	public Optional<Person> getPersonById(Id id);
	
	/**
	 * Method used to get all persons with a defined address
	 * @param address used to get persons with this address
	 * @return a list of all persons with this address
	 */
	public List<Person> getPersonsByAddress(String address);
	
	/**
	 * Method used to get all persons with a defined station
	 * @param station used to get persons with this station
	 * @return a list of all persons with this station
	 */
	public List<Person> getPersonsByStation(Integer station);
	
	/**
	 * Method used to get all firestations
	 * @return a list of all firestations
	 */
	public List<Firestation> getFirestations();
	
	/**
	 * Method used to get all firestations with a defined station
	 * @param station used to get firestations with this station
	 * @return a list of all firestations with this station
	 */
	public List<Firestation> getFirestationsByNumber(Integer station);
	
	/**
	 * Method used to get all firestations with a defined address
	 * @param address used to get firestations with this address
	 * @return a list of all firestations with this address
	 */
	public List<Firestation> getFirestationsByAddress(String address);
	
	/**
	 * Method used to get all medicalrecords
	 * @return a list of all medicalrecords
	 */
	public List<Medicalrecord> getMedicalrecords();
	
	/**
	 * Method used to get medicalrecord by id
	 * @param id defined by firstName + lastName
	 * @return an optional of medicalrecord by his id
	 */
	public Optional<Medicalrecord> getMedicalRecordById(Id id);
	
	public Data getData();
	
}
