package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
public class FirestationManagerImpl implements FirestationManager {

	private final DataStorage dataStorage;
	private final MedicalrecordsManager medicalrecordsManager;

	public FirestationManagerImpl(DataStorage dataStorage, MedicalrecordsManager medicalrecordsManager) {
		this.dataStorage = dataStorage;
		this.medicalrecordsManager = medicalrecordsManager;
	}
	
	public void addFirestation(Firestation firestation) {

		log.debug("Add a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.stream()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			log.error("Error creating a new firestation");
			throw new BadRequestExceptions("Firestation serve already this address");
		}

		dataStorage
				.getFirestations()
				.add(firestation);
		log.info("Firestation has benn created");

	}

	public void updateFirestation(Firestation firestation) {

		log.debug("Update a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.stream()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			int indexOfFirestation = dataStorage.getFirestations().indexOf(optionalFirestation.get());
			dataStorage
					.getFirestations()
					.set(indexOfFirestation, firestation);
			log.info("Firestation has been updated");
		} else {
			log.error("Error updating a firestation");
			throw new BadRequestExceptions("Firestation who serve this address doesn't exist");
		}

	}

	public void deleteFirestation(Firestation firestation) {

		log.debug("Delete a firestation: " + firestation);

		Optional<Firestation> optionalFirestation =
				dataStorage
						.getFirestations()
						.stream()
						.filter(f -> f.getAddress().equals(firestation.getAddress()))
						.findFirst();

		if(optionalFirestation.isPresent()) {
			dataStorage
					.getFirestations()
					.remove(optionalFirestation.get());
			log.info("Firestation has benn removed");
		} else {
			log.error("Error deleting a firestation");
			throw new BadRequestExceptions("Firestation who serve this address doesn't exist");
		}

	}

	public Set<String> getPhoneByFirestationNumber(int station) {

		log.debug("Get phone by firestation number" + station);

		List<String> firestationAddress =
				dataStorage
						.getFirestationsByNumber(station)
						.stream()
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		log.info("Phone number by firestation number has been recovered");
		return dataStorage
				.getPersons()
				.stream()
				.filter(p -> firestationAddress.contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}

	public List<PersonsByAddressDto> getPersonsByAddress(String address) {

		log.debug("Get persons by address" + address);

		List<Person> persons = dataStorage.getPersonsByAddress(address);

		List<Medicalrecord> medicalrecords = dataStorage.getMedicalrecords();

		List<Firestation> fireStations =
				dataStorage
						.getFirestationsByAddress(address);

		List<PersonsByAddressDto> personsByAddressDto = new ArrayList<>();

		for(Person person : persons) {
			List<PersonsByAddressDto> aggregate =
					fireStations
							.stream()
							.map(fireStation -> {
								try {
									return new PersonsByAddressDto(person, fireStation, medicalrecords
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
			log.info("All persons by address has been recovered");
		}

		return personsByAddressDto;
	}

	public List<PersonsByStationDto> getPersonsByStation(int stationNumber) {

		log.debug("Get persons by firestation number: " + stationNumber);

		log.info("Get firestation address by station number");
		List<String> firestationAddressByStationNumber =
				dataStorage
						.getFirestationsByNumber(stationNumber)
						.stream()
						.map(Firestation::getAddress)
						.collect(Collectors.toList());
		

		log.info("Get all persons by address");
		List<PersonsByStationDto> personsByStationDto =
				dataStorage
						.getPersons()
						.stream()
						.filter(person -> firestationAddressByStationNumber.contains(person.getAddress()))
						.map(person -> {

							return new PersonsByStationDto(person);

						})
						.collect(Collectors.toList());

		return personsByStationDto;
	}

	public NumberOfAdultsAndChildrenDto getNumbersOfChildrenAndAdultsByStation(int station) {
		log.debug("Get numbers of adults and children by station");
		int adultsNumber = 0;
		int childrenNumber = 0;

		for(PersonsByStationDto person : getPersonsByStation(station)) {

			Optional<Medicalrecord> medicalrecord = medicalrecordsManager.getMedicalRecordByPersonId(person.getId());

			if(medicalrecord.isPresent()) {
				Integer personAge = medicalrecord.get().getAge();
				if(personAge > 18) {
					adultsNumber++;
				} else childrenNumber++;
			}
		}
		log.info("Return number of children and adults");
		return new NumberOfAdultsAndChildrenDto(childrenNumber, adultsNumber);
	}
}
