package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.service.DataStorage;
import com.openclassrooms.safetynet.dto.GetPersonsByAddressDto;
import com.openclassrooms.safetynet.dto.GetPersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FirestationManagerImpl implements FirestationManager {
	
	@Autowired
	private DataStorage dataStorage;
	
	@Autowired
	public FirestationManagerImpl(DataStorage dataStorage) {this.dataStorage = dataStorage;}
	
	public void addFirestation(Firestation firestation) {
		
		Logger.debug("Add a firestation" + firestation);
		
		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFireStations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();
		
		if (optionalFirestation.isPresent()) {
			throw new RuntimeException("Firestation serve already this address");
		}
		
		dataStorage
				.getData()
				.getFirestations()
				.add(firestation);
		
	}
	
	public void updateFirestation(Firestation firestation) {
		
		Logger.debug("Update a firestation" + firestation);
		
		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFireStations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();
		
		if (optionalFirestation.isPresent()) {
			int indexOfFirestation = dataStorage.getData().getFirestations().indexOf(optionalFirestation.get());
			
			dataStorage
					.getData()
					.getFirestations()
					.set(indexOfFirestation, new Firestation(firestation));
		} else {
			throw new RuntimeException("Firestation who serve this address doesn't exist");
		}
		
	}
	
	public void deleteFirestation(Firestation firestation) {
		
		Logger.debug("Delete a firestation" + firestation);
		
		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFireStations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();
		
		if (optionalFirestation.isPresent()) {
			int indexOfFirestation = dataStorage.getData().getFirestations().indexOf(optionalFirestation.get());
			
			dataStorage
					.getData()
					.getFirestations()
					.remove(indexOfFirestation);
		} else {
			throw new RuntimeException("Firestation who serve this address doesn't exist");
		}
		
	}
	
	public List<GetPersonsByStationDto> getPersonsByStation(int stationNumber) {
		
		Logger.debug("Get persons by firestation number" + stationNumber);
		
		Stream<Person> persons = dataStorage.getPersons();
		
		List<GetPersonsByStationDto> firestationDto = new ArrayList<>();
		
		List<String> getFirestationAddressesByStationNumber =
				dataStorage
						.getFirestationsByNumber(stationNumber)
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		
		return persons
				.filter(person -> getFirestationAddressesByStationNumber.contains(person.getAddress()))
				.map(person -> {
					try {
						return new GetPersonsByStationDto(person);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
		
	}
	
	public Set<String> getPhoneNumbersByFirestationNumber(int station) {
		
		Logger.debug("Get phone by firestation number" + station);
		
		List<String> firestationAddress =
				dataStorage
						.getFirestationsByNumber(station)
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		
		return dataStorage
				.getPersons()
				.filter(p -> firestationAddress.contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}
	
	public List<GetPersonsByAddressDto> getPersonsByAddress(String address) {
		
		Logger.debug("Get persons by address" + address);
		
		List<Person> persons = dataStorage.getPersonsByAddress(address).collect(Collectors.toList());
		
		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();
		
		List<Firestation> fireStations =
				dataStorage
						.getFirestationsByAddress(address)
						.collect(Collectors.toList());
		
		List<GetPersonsByAddressDto> getPersonsByAddressDto = new ArrayList<>();
		
		for (Person person : persons) {
			List<GetPersonsByAddressDto> aggregate =
					fireStations
							.stream()
							.map(fireStation -> {
								try {
									return new GetPersonsByAddressDto(person, fireStation, medicalrecords
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
