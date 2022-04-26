package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

public class GetPersonsByStationDto {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private Id id;

	public GetPersonsByStationDto(Person person) {

		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
		this.id = person.getId();
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

	@JsonIgnore
	@JsonGetter("id")
	public Id getId() {
		return id;
	}
}
