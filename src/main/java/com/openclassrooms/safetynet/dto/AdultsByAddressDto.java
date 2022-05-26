package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

public class AdultsByAddressDto {
	
	private final String firstName;
	private final String lastName;
	private final String address;
	private final String city;
	private final Integer zip;
	private final String phone;
	private final String email;
	private final Integer age;
	
	public AdultsByAddressDto(Person person, Medicalrecord medicalrecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.city = person.getCity();
		this.zip = person.getZip();
		this.phone = person.getPhone();
		this.email = person.getEmail();
		this.age = medicalrecord.getAge();
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
	
	@JsonGetter("city")
	public String getCity() {
		return city;
	}
	
	@JsonGetter("zip")
	public Integer getZip() {
		return zip;
	}
	
	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}
	
	@JsonGetter("email")
	public String getEmail() {
		return email;
	}
	
	@JsonGetter("age")
	@JsonIgnore
	public Integer getAge() {
		return age;
	}
	
	@JsonGetter("isMinor")
	@JsonIgnore
	public boolean iSMajor() {
		return age > 18;
	}
	
}
