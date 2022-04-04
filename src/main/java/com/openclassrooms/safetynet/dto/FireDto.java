package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class FireDto {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	private int station;
	
	public FireDto(Person person, FireStation firestation) throws Exception {
		if (!person.getAddress().equals(firestation.getAddress())) {
			throw new Exception("Error");
		}
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.station = firestation.getStation();
	}
}
