package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class GetPersonByFirstNameAndLastNameDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;
	
	public GetPersonByFirstNameAndLastNameDto(Person person, Medicalrecord medicalRecord) {
		
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
		this.age=medicalRecord.getAge();
		this.medications=medicalRecord.getMedications();
		this.allergies=medicalRecord.getAllergies();
	}
	
	public GetPersonByFirstNameAndLastNameDto(Person person) {
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonGetter("email")
	public String getEmail() {
		return email;
	}
	
	@JsonGetter("medications")
	public List<String> getMedications() {
		return medications;
	}
	
	@JsonGetter("allergies")
	public List<String> getAllergies() {
		return allergies;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
	
}
