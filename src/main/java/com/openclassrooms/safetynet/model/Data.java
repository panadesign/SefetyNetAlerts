package com.openclassrooms.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Data.
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
	private List<Person> persons = new ArrayList<>();
	private List<Firestation> firestations = new ArrayList<>();
	private List<Medicalrecord> medicalrecords = new ArrayList<>();
}
