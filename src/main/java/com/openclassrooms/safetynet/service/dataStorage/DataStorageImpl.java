package com.openclassrooms.safetynet.service.dataStorage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class DataStorageImpl implements DataStorage {
	private final Data data;
	
	public DataStorageImpl() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new ClassPathResource("data.json").getFile();
		log.info("File address = " + file.getAbsolutePath());
		this.data = objectMapper.readValue(file, Data.class);
	}
	public Data getData() {
		return data;
	}

	public List<Person> getPersons() {
		log.info("Get all persons");
		return data.getPersons();
	}

	public Optional<Person> getPersonById(Id id) {
		log.info("Get all persons by Id");
		return getPersons()
				.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}

	public List<Person> getPersonsByAddress(String address) {
		log.info("Get all persons by address");
		return getPersons()
				.stream()
				.filter(person -> person.getAddress().equals(address))
				.collect(Collectors.toList());
	}
	
	public List<Person> getPersonsByStation(Integer station) {
		log.info("Get all persons by station");
		List<String> getFirestationsByStationNumber =
				getFirestationsByNumber(station)
						.stream()
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		
		return 	getPersons()
				.stream()
				.filter(person -> getFirestationsByStationNumber.contains(person.getAddress()))
				.collect(Collectors.toList());
	}
	
	public List<Firestation> getFirestations() {
		
		log.info("Get all firestations");
		return data.getFirestations();
	}
	
	public List<Firestation> getFirestationsByNumber(Integer station) {
		log.info("Get all firestations by number");
		
		return getFirestations()
				.stream()
				.filter(firestation -> firestation.getStation() == station)
				.collect(Collectors.toList());
	}
	
	public List<Firestation> getFirestationsByAddress(String address) {
		log.info("Get all firestationsby address");
		
		return getFirestations()
				.stream()
				.filter(firestation -> firestation.getAddress().equals(address))
				.collect(Collectors.toList());
	}
	
	public List<Medicalrecord> getMedicalrecords() {
		log.info("Get all medicalrecords");
		return data.getMedicalrecords();
	}
	
	public Optional<Medicalrecord> getMedicalRecordById(Id id) {
		log.info("Get all medicalrecords by Id");
		return getMedicalrecords()
				.stream()
				.filter(medicalrecord -> medicalrecord.getId().equals(id))
				.findFirst();
	}

}