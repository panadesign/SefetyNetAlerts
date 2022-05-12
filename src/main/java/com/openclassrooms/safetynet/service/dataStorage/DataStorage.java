package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.*;

import java.util.List;
import java.util.Optional;

public interface DataStorage {
	
	public List<Person> getPersons();
	
	public Optional<Person> getPersonById(Id id);
	
	public List<Person> getPersonsByAddress(String address);
	
	public List<Person> getPersonsByStation(Integer station);
	
	public List<Firestation> getFirestations();
	
	public List<Firestation> getFirestationsByNumber(Integer station);
	
	public List<Firestation> getFirestationsByAddress(String address);
	
	public List<Medicalrecord> getMedicalrecords();
	
	public Optional<Medicalrecord> getMedicalRecordById(Id id);
	
	public Data getData();
	
}
