package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class FamiliesByStationDto {
	private String lastName;
	private String firstName;
	private String phone;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;


	public FamiliesByStationDto(Person person, Medicalrecord medicalRecord) {
		this.lastName = person.getLastName();
		this.firstName = person.getFirstName();
		this.phone = person.getPhone();
		this.age = medicalRecord.getAge();
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

	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}

	@JsonGetter("age")
	public Integer getAge() {
		return age;
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
