package com.openclassrooms.safetynet.service.dataStorage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataStorageImpl implements DataStorage {
	private final Data data;
	
	public DataStorageImpl() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new ClassPathResource("data.json").getFile();
		this.data = objectMapper.readValue(file, Data.class);
	}
	
	public List<Person> getPersons() {
		return data.getPersons();
	}
	
	public Data getData() {
		return data;
	}
	
	public Optional<Person> getPersonById(Id id) {
		return getPersons()
				.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}

	public List<Person> getPersonsByAddress(String address) {
		return getPersons()
				.stream()
				.filter(person -> person.getAddress().equals(address))
				.collect(Collectors.toList());
	}
	
	public List<Person> getPersonsByStation(Integer station) {
		List<String> getFirestationsByStationNumber =
				getFirestationsByNumber(station)
						.stream()
						.map(firestation -> firestation.getAddress())
						.collect(Collectors.toList());
		
		return 	getPersons()
				.stream()
				.filter(person -> getFirestationsByStationNumber.contains(person.getAddress()))
				.collect(Collectors.toList());
	}
	
	public List<Firestation> getFirestations() {
		return data.getFirestations();
	}
	
	public List<Firestation> getFirestationsByNumber(Integer station) {
		return getFirestations()
				.stream()
				.filter(firestation -> firestation.getStation() == station)
				.collect(Collectors.toList());
	}
	
	public List<Firestation> getFirestationsByAddress(String address) {
		return getFirestations()
				.stream()
				.filter(firestation -> firestation.getAddress().equals(address))
				.collect(Collectors.toList());
	}
	
	public List<Medicalrecord> getMedicalRecord() {
		return data.getMedicalrecords();
	}
	
	public Optional<Medicalrecord> getMedicalRecordById(Id id) {
		return getMedicalRecord()
				.stream()
				.filter(medicalrecord -> medicalrecord.getId().equals(id))
				.findFirst();
	}

}