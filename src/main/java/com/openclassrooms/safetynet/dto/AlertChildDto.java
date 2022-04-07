package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

public class AlertChildDto {
	private String firstName;
	private String lastName;
	private String phone;
	private int age;
	private String status;

	public AlertChildDto(Person person, MedicalRecord medicalRecord) throws Exception {
		if(!person.getFirstName().equals(medicalRecord.getFirstName())|| !person.getLastName().equals(medicalRecord.getLastName())) {
			throw new Exception("Error");
		}

		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.phone = person.getPhone();
		this.age = person.getAge();
		this.status = person.getStatus;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
