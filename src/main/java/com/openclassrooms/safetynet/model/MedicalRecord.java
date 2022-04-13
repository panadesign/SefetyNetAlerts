package com.openclassrooms.safetynet.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicalRecord {
	
	public Id getId() {
		return  new Id(firstName, lastName);
	}
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	private LocalDate birthdate;
	
	@Getter
	@Setter
	private List<String> medications;
	
	@Getter
	@Setter
	private List<String> allergies;
	
	public MedicalRecord(){};

	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = parseStringToLocalDate(birthdate);
		this.medications = medications;
	}
	
	public void setBirthdate(String birthdate){
		this.birthdate = parseStringToLocalDate(birthdate);
	}
	
	private LocalDate parseStringToLocalDate(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return LocalDate.parse(birthdate, formatter);
	}
	
	public Integer getAge() {
		return Period.between(birthdate, LocalDate.now()).getYears();
	}
	
}
