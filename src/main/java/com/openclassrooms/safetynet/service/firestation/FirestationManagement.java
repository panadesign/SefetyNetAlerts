package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.FireDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FirestationManagement implements IFirestation {
	
	@Autowired
	DataStorage dataStorage;
	
	public void addFirestation() {
	}
	
	public void updateFirestation() {
	}
	
	public void deleteFirestation() {
	}
	
	public List<Person> getPeopleByFirestationNumber(int station) {
		
		List<String> firestationAdress = dataStorage.getData().getFirestations()
				.stream()
				.filter(p -> p.getStation() == (station))
				.map(p -> p.getAddress())
				.collect(Collectors.toList());
		
		return dataStorage.getData().getPersons()
				.stream()
				.filter(p -> firestationAdress.contains(p.getAddress()))
				.map(p -> new Person(p.getLastName(), p.getFirstName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());
	}
	
	public Set<String> getPhoneNumberByFirestationNumber(int station) {
		
		List<String> firestationAdress = dataStorage.getData().getFirestations()
				.stream()
				.filter(p -> p.getStation() == (station))
				.map(p -> p.getAddress())
				.collect(Collectors.toList());
		
		return dataStorage.getData().getPersons()
				.stream()
				.filter(p -> firestationAdress.contains(p.getAddress()))
				.map(p -> p.getPhone())
				.collect(Collectors.toSet());
	}
	
	public List<FireDto> getPeoplesByAddressAndFirestationNumber(String address) {
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		List<FireStation> fireStations = dataStorage.getData().getFirestations();
		
		List<FireDto> fireDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<FireDto> aggregate =
					fireStations.stream()
							.filter(fireStation -> fireStation.getAddress().equals(person.getAddress()) && person.getAddress().equals(address))
							.map(fireStation -> {
								try {
									return new FireDto(person, fireStation);
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							})
							.collect(Collectors.toList());
			fireDto.addAll(aggregate);
		}
		return fireDto;
	}
	
}
