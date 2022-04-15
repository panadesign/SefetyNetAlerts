package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class PersonInfoDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String city;
	private Integer zip;
	private String phone;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;
	
	public PersonInfoDto(Person person, MedicalRecord medicalRecord) {
		
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
		this.age=medicalRecord.getAge();
		this.medications=medicalRecord.getMedications();
		this.allergies=medicalRecord.getAllergies();
	}
	
	public PersonInfoDto(Person person) {
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
		this.phone= person.getPhone();
		this.city= person.getCity();
		this.zip = person.getZip();
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
	@JsonSetter
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
	
	@JsonGetter("phone")
	@JsonSetter
	public String getPhone() {
		return phone;
	}
	
	@JsonGetter("address")
	@JsonSetter
	public String getAddress() {
		return address;
	}
	
	@JsonGetter("city")
	@JsonSetter
	public String getCity() {
		return city;
	}
	
	@JsonGetter("zip")
	@JsonSetter
	public Integer getZip() {
		return zip;
	}
}
