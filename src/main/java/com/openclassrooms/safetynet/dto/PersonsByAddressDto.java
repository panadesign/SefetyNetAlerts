package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Medicalrecord;

import java.util.List;

public class PersonsByAddressDto {
	
	private final String firstName;
	private final String lastName;
	private String address;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;
	private final int station;

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
	
	@Override
	public boolean equals(Object obj){
		
		if(obj == null) return false;
		if(obj instanceof PersonsByAddressDto && this == obj) return true;
		
		PersonsByAddressDto personsByAddressDto = (PersonsByAddressDto) obj;
		
		return firstName.equals(personsByAddressDto.getFirstName()) && lastName.equals(personsByAddressDto.getLastName());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
