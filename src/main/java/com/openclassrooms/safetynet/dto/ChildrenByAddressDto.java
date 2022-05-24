package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

public class ChildrenByAddressDto {
	private final String firstName;
	private final String lastName;
	private final String phone;
	private final Integer age;

	public ChildrenByAddressDto(Person person, Medicalrecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.phone = person.getPhone();
		this.age=medicalRecord.getAge();
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
	
	@JsonGetter("isMinor")
	@JsonIgnore
	public boolean isMinor() {
		return age <= 18;
	}
	
}
