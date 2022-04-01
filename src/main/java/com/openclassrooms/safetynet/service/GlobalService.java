package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class GlobalService {
	
	public int ageCalculation(String birthdate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthdate1 = LocalDate.parse(birthdate, dtf);
		LocalDate today = LocalDate.now();
		return Period.between(birthdate1, today).getYears();
	}
	
}
