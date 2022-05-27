package com.openclassrooms.safetynet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Data.
 */
public class Data {
	private List<Person> persons = new ArrayList<>();
	private List<Firestation> firestations = new ArrayList<>();
	private List<Medicalrecord> medicalrecords = new ArrayList<>();

	/**
	 * Gets persons.
	 *
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * Sets persons.
	 *
	 * @param persons the persons
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * Gets firestations.
	 *
	 * @return the firestations
	 */
	public List<Firestation> getFirestations() {
		return firestations;
	}

	/**
	 * Sets firestations.
	 *
	 * @param firestations the firestations
	 */
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}

	/**
	 * Gets medicalrecords.
	 *
	 * @return the medicalrecords
	 */
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}

	/**
	 * Sets medicalrecords.
	 *
	 * @param medicalrecords the medicalrecords
	 */
	public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}
