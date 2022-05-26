package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Medicalrecord {
	
	@JsonIgnore
	public Id getId() {
		return  new Id(firstName, lastName);
	}
	
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	private LocalDate birthdate = LocalDate.now();
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();
	
	public Medicalrecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = parseStringToLocalDate(birthdate);
		this.medications = medications;
		this.allergies = allergies;
	}
	
	public Medicalrecord(){}

	public Medicalrecord(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Medicalrecord(String firstName, String lastName, String birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = parseStringToLocalDate(birthdate);
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	public void setBirthdate(String birthdate){
		this.birthdate = parseStringToLocalDate(birthdate);
	}
	
	private LocalDate parseStringToLocalDate(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return LocalDate.parse(birthdate, formatter);
	}
	
	@JsonIgnore
	public Integer getAge() {
		return Period.between(birthdate, LocalDate.now()).getYears();
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
	
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<String> getMedications() {
		return medications;
	}
	
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	
	public List<String> getAllergies() {
		return allergies;
	}
	
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
}
