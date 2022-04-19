package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.DataStorage;
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
	
	public List<getPersonsByStationDto> getPeopleByFirestationNumber(int stationNumber) {
		
		List<Person> persons = dataStorage.getData().getPersons();
		List<FireStation> fireStations = dataStorage.getData().getFirestations();
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<getPersonsByStationDto> firestationDto = new ArrayList<>();
		
		List<String> getAddressesByStationNumber =
				fireStations
						.stream()
						.filter(p -> p.getStation() == (stationNumber))
						.map(FireStation::getAddress)
						.collect(Collectors.toList());
		
		List<getPersonsByStationDto> getPersonsByFirestationsAddresses =
				persons
						.stream()
						.filter(person -> getAddressesByStationNumber.contains(person.getAddress()))
						.map(person -> {
							try {
								return new getPersonsByStationDto(person);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						})
						.collect(Collectors.toList());
		firestationDto.addAll(getPersonsByFirestationsAddresses);
		
		return firestationDto;
	}
	
	public Set<String> getPhoneNumbersByFirestationNumber(int station) {
		
		List<String> firestationAddress =
				dataStorage
						.getData()
						.getFirestations()
						.stream()
						.filter(p -> p.getStation() == (station))
						.map(FireStation::getAddress)
						.collect(Collectors.toList());
		
		return dataStorage
				.getData()
				.getPersons()
				.stream()
				.filter(p -> firestationAddress.contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}
	
	public List<getPersonsByAddressDto> getPeoplesByAddress(String address) {
		
		List<Person> persons = dataStorage.getPersonsByAddress(address);
				/*dataStorage
						.getData()
						.getPersons()
						.stream()
						.filter(person -> person.getAddress().equals(address))
						.collect(Collectors.toList());
				*/
		List<MedicalRecord> medicalRecords = dataStorage.getData().getMedicalrecords();
		
		List<FireStation> fireStations =
				dataStorage
						.getData()
						.getFirestations()
						.stream()
						.filter(fireStation -> fireStation.getAddress().equals(address))
						.collect(Collectors.toList());
		
		List<getPersonsByAddressDto> fireDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<getPersonsByAddressDto> aggregate =
					fireStations
							.stream()
							.map(fireStation -> {
								try {
									return new getPersonsByAddressDto(person, fireStation, medicalRecords.stream()
											.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
											.findFirst().orElse(null));
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
