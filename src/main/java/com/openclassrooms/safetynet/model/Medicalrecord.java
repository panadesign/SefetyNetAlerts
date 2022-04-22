package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Medicalrecord {
	
	public Medicalrecord(Medicalrecord medicalRecord) {}
	
	@JsonIgnore
	public Id getId() {
		return  new Id(firstName, lastName);
	}
	
	@Getter
	@Setter
	@NonNull
	private String firstName;
	
	@Getter
	@Setter
	@NonNull
	private String lastName;
	
	private LocalDate birthdate;
	
	@Getter
	@Setter
	private List<String> medications;
	
	@Getter
	@Setter
	private List<String> allergies;
	
	public Medicalrecord(){};

	public Medicalrecord(String firstName, String lastName, String birthdate, List<String> medications) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = parseStringToLocalDate(birthdate);
		this.medications = medications;
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
	
}
