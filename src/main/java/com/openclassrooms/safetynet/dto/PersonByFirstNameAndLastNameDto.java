package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

/**
 * The type Person by first name and last name dto.
 */
public class PersonByFirstNameAndLastNameDto {
	
	private final String firstName;
	private final String lastName;
	private final String email;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;

	/**
	 * Instantiates a new Person by first name and last name dto.
	 *
	 * @param person        the person
	 * @param medicalRecord the medical record
	 */
	public PersonByFirstNameAndLastNameDto(Person person, Medicalrecord medicalRecord) {
		
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
		this.age=medicalRecord.getAge();
		this.medications=medicalRecord.getMedications();
		this.allergies=medicalRecord.getAllergies();
	}

	/**
	 * Instantiates a new Person by first name and last name dto.
	 *
	 * @param person the person
	 */
	public PersonByFirstNameAndLastNameDto(Person person) {
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
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
	 * Gets email.
	 *
	 * @return the email
	 */
	@JsonGetter("email")
	public String getEmail() {
		return email;
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
		PersonByFirstNameAndLastNameDto personByFirstNameAndLastNameDto = (PersonByFirstNameAndLastNameDto)obj;

		return firstName.equals(personByFirstNameAndLastNameDto.getFirstName()) && lastName.equals(personByFirstNameAndLastNameDto.getLastName());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
