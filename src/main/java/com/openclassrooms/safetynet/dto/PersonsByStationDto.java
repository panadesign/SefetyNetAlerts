package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Person;

public class PersonsByStationDto {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private Id id;

	public PersonsByStationDto(Person person) {

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
	
	@Override
	public boolean equals(Object obj){
		
		if(obj == null) return false;
		if(obj instanceof PersonsByStationDto && this == obj) return true;
		
		PersonsByStationDto personsByStationDto = (PersonsByStationDto) obj;
		
		return firstName.equals(personsByStationDto.getFirstName()) && lastName.equals(personsByStationDto.getLastName());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
