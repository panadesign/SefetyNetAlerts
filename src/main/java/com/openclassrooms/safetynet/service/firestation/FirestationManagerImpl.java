package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.service.DataStorage;
import com.openclassrooms.safetynet.dto.getPersonsByAddressDto;
import com.openclassrooms.safetynet.dto.getPersonsByStationDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FirestationManagerImpl implements FirestationManager {
	
	@Autowired
	private DataStorage dataStorage;
	
	public void addFirestation() {
	}
	
	public void updateFirestation() {
	}
	
	public void deleteFirestation() {
	}
	
	public List<getPersonsByStationDto> getPersonsByStation(int stationNumber) {
		
		Stream<Person> persons = dataStorage.getPersons();
		
		List<getPersonsByStationDto> firestationDto = new ArrayList<>();
		
		List<String> getFirestationAddressesByStationNumber =
				dataStorage
						.getFirestationsByNumber(stationNumber)
						.map(FireStation::getAddress)
						.collect(Collectors.toList());
		
		return persons
				.filter(person -> getFirestationAddressesByStationNumber.contains(person.getAddress()))
				.map(person -> {
					try {
						return new getPersonsByStationDto(person);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
		
	}
	
	public Set<String> getPhoneNumbersByFirestationNumber(int station) {
		
		List<String> firestationAddress =
				dataStorage
						.getFirestationsByNumber(station)
						.map(FireStation::getAddress)
						.collect(Collectors.toList());
		
		return dataStorage
				.getPersons()
				.filter(p -> firestationAddress.contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}
	
	public List<getPersonsByAddressDto> getPeoplesByAddress(String address) {
		
		List<Person> persons = dataStorage.getPersonsByAddress(address).collect(Collectors.toList());
		
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<FireStation> fireStations =
				dataStorage
						.getFirestationsByAddress(address)
						.collect(Collectors.toList());
		
		List<getPersonsByAddressDto> getPersonsByAddressDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<getPersonsByAddressDto> aggregate =
					fireStations
							.stream()
							.map(fireStation -> {
								try {
									return new getPersonsByAddressDto(person, fireStation, medicalRecords
											.stream()
											.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
											.findFirst().orElse(null));
								} catch (Exception e) {
									e.printStackTrace();
								}
								return null;
							})
							.collect(Collectors.toList());
			
			getPersonsByAddressDto.addAll(aggregate);
		}
		
		return getPersonsByAddressDto;
	}
	
}
