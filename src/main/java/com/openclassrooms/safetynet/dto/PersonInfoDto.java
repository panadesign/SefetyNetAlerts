package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class PersonInfoDto {
	
	private String firstName;
	private String lastName;
	private String email;
	
	private List<String> medications;
	private List<String> allergies;
	
	public PersonInfoDto(Person person, MedicalRecord medicalRecord) throws Exception {
		if(!person.getFirstName().equals(medicalRecord.getFirstName()) || !person.getLastName().equals(medicalRecord.getLastName())){
			throw new Exception("Les informations saisies sont erronées");
		}
		this.firstName= person.getFirstName();
		this.lastName=person.getLastName();
		this.email= person.getEmail();
		this.medications=medicalRecord.getMedications();
		this.allergies=medicalRecord.getAllergies();
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List getMedications() {
		return medications;
	}
	
	public void setMedications(List medications) {
		this.medications = medications;
	}
	
	public List getAllergies() {
		return allergies;
	}
	
	public void setAllergies(List allergies) {
		this.allergies = allergies;
	}
}
