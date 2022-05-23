package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.firestation.FirestationManagerImpl;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest()
class FirestationIT {
	
	@Autowired
	private DataStorage dataStorage;
	
	@Autowired
	private FirestationManager firestationManager;
	
	@Autowired
	private MedicalrecordsManager medicalrecordsManager;
	
	@BeforeEach
	void setUp() throws IOException {
		dataStorage = new DataStorageImpl();
		firestationManager = new FirestationManagerImpl(dataStorage, medicalrecordsManager);
	}
	
	@Test
	void shouldReturnANewFirestation() {
		//GIVEN
		Firestation newFirestation = new Firestation(9, "32 Rue Dupont");
		
		//WHEN
		firestationManager.addFirestation(newFirestation);
		
		//THEN
		Assertions.assertTrue(dataStorage
				.getFirestations()
				.contains(newFirestation));
		
	}
	
	@Test
	void shouldReturnRuntimeExceptionWhenTryingToAddAnExistingFirestation() {
		Firestation newFirestation = new Firestation(4, "489 Manchester St");
		assertThrows(RuntimeException.class, () -> firestationManager.addFirestation(newFirestation));
	}
	
	@Test
	void shouldReturnAnUpdateFirestation() {
		Firestation firestationToUpdate = new Firestation(2, "489 Manchester St");
		
		firestationManager.updateFirestation(firestationToUpdate);
		
		Assertions.assertTrue(dataStorage
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 2)
				.anyMatch(f -> f.getAddress().equals("489 Manchester St")));
		
	}
	
	@Test
	void shouldReturnRuntimeExceptionWhenTryingToUpdateNonExistentFirestation() {
		Firestation firestationToUpdate = new Firestation(2, "Address not existing");
		assertThrows(RuntimeException.class, () -> firestationManager.updateFirestation(firestationToUpdate));
	}
	
	@Test
	void shouldDeleteWhenFirestationExists() {
		Firestation firestationToDelete = new Firestation(4, "489 Manchester St");
		
		firestationManager.deleteFirestation(firestationToDelete);
		
		Assertions.assertTrue(dataStorage
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 4)
				.noneMatch(f -> f.getAddress().equals("489 Manchester St")));
		
	}
	
	@Test
	void shouldReturnRuntimeExceptionWhenTryingToDeleteNonExistentFirestation() {
		Firestation firestationToDelete = new Firestation(1, "test");
		assertThrows(RuntimeException.class, () -> firestationManager.deleteFirestation(firestationToDelete));
	}
	
	@Test
	void shouldReturnPhoneExpectedWhenFirestationNumberIs1() {
		String phoneExpected = "841-874-7784";
		
		Assertions.assertTrue(firestationManager.getPhoneByFirestationNumber(1)
				.contains(phoneExpected));
		
	}
	
	@Test
	void shouldReturn11PersonsUsingStation3() {
		int station = 3;
		List<PersonsByStationDto> persons = firestationManager.getPersonsByStation(station);
		Assertions.assertEquals(11, persons.size());
		
	}
	
	@Test
	void shouldVerifyObjectContainsJohnBoydWhitStation3() {
		int station = 3;
		PersonsByStationDto personExpected = new PersonsByStationDto(new Person("John", "Boyd"));
		List<PersonsByStationDto> persons = firestationManager.getPersonsByStation(station);
		
		List<PersonsByStationDto> personsByStationDto = persons
				.stream()
				.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
				.collect(Collectors.toList());
		
		Assertions.assertEquals(1, personsByStationDto.size());
		Assertions.assertEquals(personExpected, personsByStationDto.get(0));
	}
	
	
	@Test
	void shouldGetJohnBoydWithAddress() throws Exception {
		String address = "1509 Culver St";
		PersonsByAddressDto personExpected = new PersonsByAddressDto(new Person("John", "Boyd"), new Firestation(3, "1509 Culver St"), new Medicalrecord("John", "Boyd", "03/06/1984"));
		List<PersonsByAddressDto> persons = firestationManager.getPersonsByAddress(address);
		
		List<PersonsByAddressDto> personsByAddressDtos = persons
				.stream()
				.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
				.collect(Collectors.toList());
		
		Assertions.assertEquals(1, personsByAddressDtos.size());
		Assertions.assertTrue(personsByAddressDtos.contains(personExpected));
	}
	
	@Test
	void shouldReturn3ChildrenForStation3() {
		int numberOfChildren = 3;
		NumberOfAdultsAndChildrenDto numberOfAdultsAndChildrenDto = firestationManager.getNumbersOfChildrenAndAdultsByStation(3);
		Assertions.assertEquals(numberOfChildren, numberOfAdultsAndChildrenDto.getNumberChildren());
	}
	
	@Test
	void shouldReturn8AdultsForStation3() {
		int numberOfAdults = 8;
		NumberOfAdultsAndChildrenDto numberOfAdultsAndChildrenDto = firestationManager.getNumbersOfChildrenAndAdultsByStation(3);
		Assertions.assertEquals(numberOfAdults, numberOfAdultsAndChildrenDto.getNumberAdults());
	}
	
	@Test
	void shouldReturn11PersonsForStation3() {
		int numberOfPersons = 11;
		NumberOfAdultsAndChildrenDto numberOfAdultsAndChildrenDto = firestationManager.getNumbersOfChildrenAndAdultsByStation(3);
		Assertions.assertEquals(numberOfPersons, numberOfAdultsAndChildrenDto.getNumberAdults()+numberOfAdultsAndChildrenDto.getNumberChildren());
	}
}