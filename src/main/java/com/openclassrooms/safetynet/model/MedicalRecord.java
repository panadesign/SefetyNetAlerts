package com.openclassrooms.safetynet.model;

import java.util.List;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthdate;
	private List medications;
	private List allergies;
	
	public MedicalRecord(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public List getAllergies() {
		return allergies;
	}

	public void setAllergies(List allergies) {
		this.allergies = allergies;
	}
	
	public MedicalRecord() {
	}

	public MedicalRecord(String firstName, String lastName, String birthdate, List medications) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List getMedications() {
		return medications;
	}

	public void setMedications(List medications) {
		this.medications = medications;
	}
}
