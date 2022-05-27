package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Medicalrecord;

import java.util.List;

/**
 * The type Persons by address dto.
 */
public class PersonsByAddressDto {
	
	private final String firstName;
	private final String lastName;
	private final String address;
	private final Integer age;
	private final List<String> medications;
	private final List<String> allergies;
	private final int station;

	/**
	 * Instantiates a new Persons by address dto.
	 *
	 * @param person        the person
	 * @param fireStation   the fire station
	 * @param medicalRecord the medical record
	 */
	public PersonsByAddressDto(Person person, Firestation fireStation, Medicalrecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.station = fireStation.getStation();
		this.age = medicalRecord.getAge();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
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
	@JsonIgnore
	public String getAddress() {
		return address;
	}

	/**
	 * Gets medications.
	 *
	 * @return the medications
	 */
	@JsonGetter("medications")
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * Gets allergies.
	 *
	 * @return the allergies
	 */
	@JsonGetter("allergies")
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Gets station.
	 *
	 * @return the station
	 */
	@JsonGetter("station")
	public int getStation() {
		return station;
	}

	/**
	 * Gets age.
	 *
	 * @return the age
	 */
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		PersonsByAddressDto personsByAddressDto = (PersonsByAddressDto) obj;
		
		return firstName.equals(personsByAddressDto.getFirstName()) && lastName.equals(personsByAddressDto.getLastName());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
