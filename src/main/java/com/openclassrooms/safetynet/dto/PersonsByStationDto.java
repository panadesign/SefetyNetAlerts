package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Person;

/**
 * The type Persons by station dto.
 */
public class PersonsByStationDto {

	private final String firstName;
	private final String lastName;
	private final String address;
	private final String phone;
	private final Id id;

	/**
	 * Instantiates a new Persons by station dto.
	 *
	 * @param person the person
	 */
	public PersonsByStationDto(Person person) {

		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
		this.id = person.getId();
	}

	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return the last name
	 */
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets address.
	 *
	 * @return the address
	 */
	@JsonGetter("address")
	public String getAddress() {
		return address;
	}

	/**
	 * Gets phone.
	 *
	 * @return the phone
	 */
	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	@JsonIgnore
	@JsonGetter("id")
	public Id getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		
		PersonsByStationDto personsByStationDto = (PersonsByStationDto) obj;
		
		return firstName.equals(personsByStationDto.getFirstName()) && lastName.equals(personsByStationDto.getLastName());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
