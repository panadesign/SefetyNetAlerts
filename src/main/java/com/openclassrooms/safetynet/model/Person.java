package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * The type Person.
 */
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

	/**
	 * Instantiates a new Person.
	 */
	public Person() {
	}

	/**
	 * Instantiates a new Person.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param address   the address
	 * @param city      the city
	 * @param zip       the zip
	 * @param phone     the phone
	 * @param email     the email
	 */
	public Person(String firstName, String lastName, String address, String city, Integer zip, String phone, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Instantiates a new Person.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param address   the address
	 * @param phone     the phone
	 */
	public Person(String firstName, String lastName, String address, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * Instantiates a new Person.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param email     the email
	 */
	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * Instantiates a new Person.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	@JsonIgnore
	public Id getId() {
		return  new Id(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj){

		if(obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;

		Person person = (Person) obj;

		return firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
