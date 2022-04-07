package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.medicalRecords.IMedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GlobalService {

	@Autowired
	private DataStorage dataStorage;

	private int ageCalculation(String birthdate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(birthdate, format);
		LocalDate today = LocalDate.now();
		return Period.between(birthDate, today).getYears();
	}

	public String evaluateStatus(int age) {
		if(age > 18) {
			return "Adult";
		} else return "Child";
	}

}
