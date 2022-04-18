package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

public class getChildrenByAddressDto {
	private String firstName;
	private String lastName;
	private String phone;
	private Integer age;

	public getChildrenByAddressDto(Person person, MedicalRecord medicalRecord) {
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
	public boolean isMinor() {
		return age <= 18;
	}

}
