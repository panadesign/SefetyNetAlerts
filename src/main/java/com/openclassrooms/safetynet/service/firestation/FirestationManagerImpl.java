package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.GetPersonsByAddressDto;
import com.openclassrooms.safetynet.dto.GetPersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
public class FirestationManagerImpl implements FirestationManager {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	private MedicalrecordsManager medicalrecordsManager;

	@Autowired
	public FirestationManagerImpl(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public void addFirestation(Firestation firestation) {

		log.debug("Add a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			log.error("Error creating a new firestation");
			throw new RuntimeException("Firestation serve already this address");
		}

		dataStorage
				.getData()
				.getFirestations()
				.add(firestation);
		log.info("Firestation has benn created");

	}

	public void updateFirestation(Firestation firestation) {

		log.debug("Update a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			int indexOfFirestation = dataStorage.getData().getFirestations().indexOf(optionalFirestation.get());
			dataStorage
					.getData()
					.getFirestations()
					.set(indexOfFirestation, firestation);
			log.info("Firestation has been updated");
		} else {
			log.error("Error updating a firestation");
			throw new RuntimeException("Firestation who serve this address doesn't exist");
		}

	}

	public void deleteFirestation(Firestation firestation) {

		log.debug("Delete a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			dataStorage
					.getData()
					.getFirestations()
					.remove(optionalFirestation.get());
			log.info("Firestation has benn removed");
		} else {
			log.error("Error deleting a firestation");
			throw new RuntimeException("Firestation who serve this address doesn't exist");
		}

	}

	public Set<String> getPhoneNumbersByFirestationNumber(int station) {

		log.debug("Get phone by firestation number" + station);

		List<String> firestationAddress =
				dataStorage
						.getFirestationsByNumber(station)
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		log.info("Phone number by firestation number has been recovered");
		return dataStorage
				.getPersons()
				.filter(p -> firestationAddress.contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}

	public List<GetPersonsByAddressDto> getPersonsByAddress(String address) {

		log.debug("Get persons by address" + address);

		List<Person> persons = dataStorage.getPersonsByAddress(address).collect(Collectors.toList());

		List<Medicalrecord> medicalrecords = dataStorage.getData().getMedicalrecords();

		List<Firestation> fireStations =
				dataStorage
						.getFirestationsByAddress(address)
						.collect(Collectors.toList());

		List<GetPersonsByAddressDto> personsByAddressDto = new ArrayList<>();

		for(Person person : persons) {
			List<GetPersonsByAddressDto> aggregate =
					fireStations
							.stream()
							.map(fireStation -> {
								try {
									return new GetPersonsByAddressDto(person, fireStation, medicalrecords
											.stream()
											.filter(medicalRecord -> medicalRecord.getId().equals(person.getId()))
											.findFirst().orElse(null));
								} catch(Exception e) {
									e.printStackTrace();
								}
								return null;
							})
							.collect(Collectors.toList());

			personsByAddressDto.addAll(aggregate);
		}

		return personsByAddressDto;
	}

	public List<GetPersonsByStationDto> getPersonsByStation(int stationNumber) {

		log.debug("Get persons by firestation number: " + stationNumber);

		List<String> firestationAddressByStationNumber =
				dataStorage
						.getFirestationsByNumber(stationNumber)
						.map(Firestation::getAddress)
						.collect(Collectors.toList());

		List<GetPersonsByStationDto> personsByStationDto =
				dataStorage
						.getPersons()
						.filter(person -> firestationAddressByStationNumber.contains(person.getAddress()))
						.map(person -> {

								return new GetPersonsByStationDto(person);

						})
						.collect(Collectors.toList());

		return personsByStationDto;
	}

	public NumberOfAdultsAndChildrenDto getNumbersOfChildrenAndAdultsByStation(int station) {

		int adultsNumber = 0;
		int childrenNumber = 0;

		for(GetPersonsByStationDto person : getPersonsByStation(station)) {

			Optional<Medicalrecord> medicalrecord = medicalrecordsManager.getMedicalRecordByPersonId(person.getId());

			if(medicalrecord.isPresent()) {
				Integer personAge = medicalrecord.get().getAge();
				if(personAge > 18) {
					adultsNumber++;
				} else childrenNumber++;
			}
		}

		return new NumberOfAdultsAndChildrenDto(childrenNumber, adultsNumber);
	}
}
