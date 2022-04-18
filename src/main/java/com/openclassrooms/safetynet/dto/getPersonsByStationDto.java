package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Person;

public class getPersonsByStationDto {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	public getPersonsByStationDto(Person person) {

		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonGetter("address")
	public String getAddress() {
		return address;
	}
	
	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}

}
