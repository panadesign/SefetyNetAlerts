package com.openclassrooms.safetynet.model;

import java.util.List;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	public MedicalRecord(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	public MedicalRecord() {
	}

	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications) {
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

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
}
