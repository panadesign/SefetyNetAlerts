package com.openclassrooms.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataStorage {
	private Data data;
	
	public DataStorage() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new ClassPathResource("data.json").getFile();
		this.data = objectMapper.readValue(file, Data.class);
	}
	
	public Data getData() {
		return data;
	}
	
	public List<Person> getPersons() {
		return data.getPersons();
	}
	
	public List<Person> getPersonById(Id id) {
		return data.getPersons()
				.stream()
				.filter(person -> person.getId().equals(id))
				.map(person -> new Person())
				.collect(Collectors.toList());
	}
	
	public List<Person> getPersonsByAddress(String address) {
		List<Person> allPersons = getData().getPersons();
		
		return allPersons
				.stream()
				.filter(person -> person.getAddress().equals(address))
				.map(person -> new Person())
				.collect(Collectors.toList());
	}
	
	public List<Person> getPersonsByStation(Integer station) {
		List<Person> allPersons = getData().getPersons();
		List<FireStation> fireStations = getData().getFirestations();
		List<Person> getPersonsByStationNumber = new ArrayList<>();
		
		List<FireStation> getFirestationByStationNumber =
				fireStations
						.stream()
						.filter(fireStation -> fireStation.getStation() == station)
						.map(fireStation -> new FireStation())
						.collect(Collectors.toList());
		
		for (FireStation fireStation : getFirestationByStationNumber) {
			List<Person> aggregate = allPersons
					.stream()
					.filter(person -> person.getAddress().equals(fireStation.getAddress()))
					.map(person -> new Person())
					.collect(Collectors.toList());
			
			getPersonsByStationNumber.addAll(aggregate);
		}
		return getPersonsByStationNumber;
	}
	
	public List<FireStation> getFireStations() {
		return data.getFirestations();
	}
	
	public List<FireStation> getFirestationsByNumber(Integer station) {
		return data.getFirestations()
				.stream()
				.filter(fireStation -> fireStation.getStation() == station)
				.map(fireStation -> new FireStation())
				.collect(Collectors.toList());
	}
	
	public List<FireStation> getFirestationsByAddress(String address) {
		return data.getFirestations()
				.stream()
				.filter(fireStation -> fireStation.getAddress().equals(address))
				.map(fireStation -> new FireStation())
				.collect(Collectors.toList());
	}
	
	public List<MedicalRecord> getMedicalRecord() {
		return data.getMedicalrecords();
	}
	
	public List<MedicalRecord> getMedicalRecordById(Id id) {
		return data.getMedicalrecords()
				.stream()
				.filter(medicalRecord -> medicalRecord.getId().equals(id))
				.map(medicalRecord -> new MedicalRecord())
				.collect(Collectors.toList());
	}
}