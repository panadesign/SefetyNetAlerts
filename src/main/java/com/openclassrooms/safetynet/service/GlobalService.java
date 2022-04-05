package com.openclassrooms.safetynet.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class GlobalService {
	
	public int ageCalculation(String birthdate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(birthdate, format);
		LocalDate today = LocalDate.now();
		return Period.between(birthDate, today).getYears();
	}

}
