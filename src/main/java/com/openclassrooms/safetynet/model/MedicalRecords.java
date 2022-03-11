package com.openclassrooms.safetynet.model;

import java.util.Date;
import java.util.List;

public class MedicalRecords {

	private String firstName;
	private String lastName;
	private Date birthdate;
	private List medications;

	public MedicalRecords() {
	}

	public MedicalRecords(String firstName, String lastName, Date birthdate, List medications) {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public List getMedications() {
		return medications;
	}

	public void setMedications(List medications) {
		this.medications = medications;
	}
}
