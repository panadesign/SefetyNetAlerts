package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Person {
	
	@Getter
	@Setter
	@NonNull
	private String firstName;
	
	@Getter
	@Setter
	@NonNull
	private String lastName;
	
	@Getter
	@Setter
	private String address;
	
	@Getter
	@Setter
	private String city;
	
	@Getter
	@Setter
	private Integer zip;
	
	@Getter
	@Setter
	private String phone;
	
	@Getter
	@Setter
	private String email;

	public Person() {
	}

	public Person(String lastName, String firstName, String address, String city, Integer zip, String phone, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	public Person(String lastName, String firstName, String address, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.phone = phone;
	}
	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person(String phone) {
		this.phone = phone;
	}

	public Person(Person person) {
	}
	
	@JsonIgnore
	public Id getId() {
		return  new Id(firstName, lastName);
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}
