package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DataStorage {

	public Data getData();
	public Stream<Person> getPersons();
	public Optional<Person> getPersonById(Id id);
	public Stream<Person> getPersonsByAddress(String address);
	public List<Person> getPersonsByStation(Integer station);
	public Stream<Firestation> getFireStations();
	public Stream<Firestation> getFirestationsByNumber(Integer station);
	public Stream<Firestation> getFirestationsByAddress(String address);
	public Stream<Medicalrecord> getMedicalRecord();
	public Optional<Medicalrecord> getMedicalRecordById(Id id);

}
