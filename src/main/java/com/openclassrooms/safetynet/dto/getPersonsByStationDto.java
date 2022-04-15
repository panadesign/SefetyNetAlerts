package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Person;

public class getPersonsByStationDto {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	public getPersonsByStationDto(Person person) throws Exception {

		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
