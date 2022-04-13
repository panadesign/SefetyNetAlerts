package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

public class AlertChildDto {
	private String firstName;
	private String lastName;
	private String phone;
	private Integer age;

	public AlertChildDto(Person person, MedicalRecord medicalRecord) {

		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.phone = person.getPhone();
		this.age=medicalRecord.getAge();
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}

	public boolean isMinor(Integer age) {
		return age <= 18;
	}

}
