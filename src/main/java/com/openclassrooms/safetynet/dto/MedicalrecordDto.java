package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Medicalrecord;
import java.time.LocalDate;
import java.util.List;

public class MedicalrecordDto {
	
	private final String lastName;
	private final String firstName;
	private final LocalDate birthdate;
	private final List<String> medications;
	private final List<String> allergies;
	
	public MedicalrecordDto(Medicalrecord medicalRecord) {
		this.lastName = medicalRecord.getLastName();
		this.firstName = medicalRecord.getFirstName();
		this.birthdate = medicalRecord.getBirthdate();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}
	
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonGetter("birthdate")
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	@JsonGetter("medications")
	public List<String> getMedications() {
		return medications;
	}
	
	@JsonGetter("allergies")
	public List<String> getAllergies() {
		return allergies;
	}
}
