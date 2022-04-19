package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

public class Person {
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
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

	public Person(String lastName, String firstName, String address, String phone, int age) {
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

	public Id getId() {
		return  new Id(firstName, lastName);
	}
	
}
