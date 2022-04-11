package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.MedicalRecord;

import java.util.List;

public class FireDto {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private List<String> medications;
	private List<String> allergies;
	private int station;
	
	public FireDto(Person person, FireStation fireStation) throws Exception {
		if (!person.getAddress().equals(fireStation.getAddress())) {
			throw new Exception("Address not found");
		}
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.station = fireStation.getStation();
	}
	
	public FireDto(Person person, FireStation fireStation, MedicalRecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.station = fireStation.getStation();
		this.age = medicalRecord.getAge();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<String> getMedications() {
		return medications;
	}
	
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	
	public List<String> getAllergies() {
		return allergies;
	}
	
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	public int getStation() {
		return station;
	}
	
	public void setStation(int station) {
		this.station = station;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
}
