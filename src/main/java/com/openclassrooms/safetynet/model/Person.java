package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

	private String lastName;
	private String firstName;
	private String address;
	private String city;
	private Integer zip;
	private String phone;
	private String email;

	public Person() {
	}

	public Person(String lastName, String firstName, String address, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.phone = phone;
	}

	public Person(String lastName, String firstName, String address, String city, int zip, String phone, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
