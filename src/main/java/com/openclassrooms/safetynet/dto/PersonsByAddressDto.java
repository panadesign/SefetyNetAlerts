package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Medicalrecord;

import java.util.List;

public class PersonsByAddressDto {
	
	private String firstName;
	private String lastName;

	private String address;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;
	private int station;
	
	public PersonsByAddressDto(Person person, Firestation fireStation) throws Exception {
		if (!person.getAddress().equals(fireStation.getAddress())) {
			throw new Exception("Addresses are not equals");
		}
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.station = fireStation.getStation();
	}
	
	public PersonsByAddressDto(Person person, Firestation fireStation, Medicalrecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.station = fireStation.getStation();
		this.age = medicalRecord.getAge();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
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
	@JsonIgnore
	public String getAddress() {
		return address;
	}
	
	@JsonGetter("medications")
	public List<String> getMedications() {
		return medications;
	}
	
	@JsonGetter("allergies")
	public List<String> getAllergies() {
		return allergies;
	}
	
	@JsonGetter("station")
	public int getStation() {
		return station;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
}
