package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class FireDto {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	private int station;
	
	public FireDto(Person person, MedicalRecord medicalRecord) throws Exception {
		if (person.getFirstName() != medicalRecord.getFirstName() || person.getLastName() != medicalRecord.getLastName()) {
			throw new Exception("First name or last name are not good");
		}
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthdate = medicalRecord.getBirthdate();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}
	
}
