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
	
	public Data getData() {
		return data;
	}
	
	public Stream<Person> getPersons() {
		return data.getPersons().stream();
	}
	
	public Optional<Person> getPersonById(Id id) {
		return getPersons()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}

	public Stream<Person> getPersonsByAddress(String address) {
		return getPersons()
				.filter(person -> person.getAddress().equals(address));
	}
	
	public List<Person> getPersonsByStation(Integer station) {
		List<String> getFirestationsByStationNumber =
				getFirestationsByNumber(station)
						.map(fireStation -> fireStation.getAddress())
						.collect(Collectors.toList());
		
		return 	getPersons()
				.filter(person -> getFirestationsByStationNumber.contains(person.getAddress()))
				.collect(Collectors.toList());
	}
	
	public Stream<Firestation> getFireStations() {
		return data.getFirestations().stream();
	}
	
	public Stream<Firestation> getFirestationsByNumber(Integer station) {
		return getFireStations()
				.filter(fireStation -> fireStation.getStation() == station);
	}
	
	public Stream<Firestation> getFirestationsByAddress(String address) {
		return getFireStations()
				.filter(fireStation -> fireStation.getAddress().equals(address));
	}
	
	public Stream<Medicalrecord> getMedicalRecord() {
		return data.getMedicalrecords().stream();
	}
	
	public Optional<Medicalrecord> getMedicalRecordById(Id id) {
		return getMedicalRecord()
				.filter(medicalRecord -> medicalRecord.getId().equals(id))
				.findFirst();
	}

}