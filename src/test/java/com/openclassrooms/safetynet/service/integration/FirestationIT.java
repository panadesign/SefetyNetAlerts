package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.firestation.FirestationManagerImpl;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
class FirestationIT {
	
	@Autowired
	private DataStorage dataStorage;
	
	@Autowired
	private FirestationManager firestationManager;
	
	@Autowired
	private MedicalRecordsManager medicalrecordsManager;
	
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
	void shouldReturnBadRequestExceptionsWhenTryingToAddAnExistingFirestation() {
		Firestation newFirestation = new Firestation(4, "489 Manchester St");
		assertThrows(BadRequestExceptions.class, () -> firestationManager.addFirestation(newFirestation));
	}
	
	@Test
	void shouldReturnAnUpdateFirestation() {
		//GIVEN
		Firestation firestationToUpdate = new Firestation(2, "489 Manchester St");
		//WHEN
		firestationManager.updateFirestation(firestationToUpdate);
		//THEN
		Assertions.assertTrue(dataStorage
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 2)
				.anyMatch(f -> f.getAddress().equals("489 Manchester St")));
		
	}
	
	@Test
	void shouldReturnBadRequestExceptionsWhenTryingToUpdateNonExistentFirestation() {
		Firestation firestationToUpdate = new Firestation(2, "Address not existing");
		assertThrows(BadRequestExceptions.class, () -> firestationManager.updateFirestation(firestationToUpdate));
	}
	
	@Test
	void shouldDeleteWhenFirestationExists() {
		//GIVEN
		Firestation firestationToDelete = new Firestation(4, "489 Manchester St");
		//WHEN
		firestationManager.deleteFirestation(firestationToDelete);
		//THEN
		Assertions.assertTrue(dataStorage
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 4)
				.noneMatch(f -> f.getAddress().equals("489 Manchester St")));
		
	}
	
	@Test
	void shouldReturnBadRequestExceptionsWhenTryingToDeleteNonExistentFirestation() {
		Firestation firestationToDelete = new Firestation(1, "test");
		assertThrows(BadRequestExceptions.class, () -> firestationManager.deleteFirestation(firestationToDelete));
	}
	
	@Test
	void shouldReturnPhoneExpectedWhenFirestationNumberIs1() {
		//GIVEN
		String phoneExpected = "841-874-7784";
		//WHEN
		Set<String> result = firestationManager.getPhoneByFirestationNumber(1);
		//THEN
		Assertions.assertTrue(result.contains(phoneExpected));
		
	}
	
	@Test
	void shouldReturn11PersonsUsingStation3() {
		//GIVEN
		int station = 3;
		//WHEN
		List<PersonsByStationDto> persons = firestationManager.getPersonsByStation(station);
		//THEN
		Assertions.assertEquals(11, persons.size());
		
	}
	
	@Test
	void shouldVerifyObjectContainsJohnBoydWhitStation3() {
		//GIVEN
		int station = 3;
		PersonsByStationDto personExpected = new PersonsByStationDto(new Person("John", "Boyd"));
		//WHEN
		List<PersonsByStationDto> persons = firestationManager.getPersonsByStation(station);
		
		List<PersonsByStationDto> personsByStationDto = persons
				.stream()
				.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
				.collect(Collectors.toList());
		//THEN
		Assertions.assertEquals(1, personsByStationDto.size());
		Assertions.assertEquals(personExpected, personsByStationDto.get(0));
	}
	
	
	@Test
	void shouldGetJohnBoydWithAddress() throws Exception {
		//GIVEN
		String address = "1509 Culver St";
		PersonsByAddressDto personExpected = new PersonsByAddressDto(new Person("John", "Boyd"), new Firestation(3, "1509 Culver St"), new Medicalrecord("John", "Boyd", "03/06/1984"));

		//WHEN
		List<PersonsByAddressDto> persons = firestationManager.getPersonsByAddress(address);
		
		List<PersonsByAddressDto> personsByAddressDtos = persons
				.stream()
				.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
				.collect(Collectors.toList());
		//THEN
		Assertions.assertEquals(1, personsByAddressDtos.size());
		Assertions.assertTrue(personsByAddressDtos.contains(personExpected));
	}
	
	@Test
	void shouldReturn3ChildrenForStation3() {
		//GIVEN
		int numberOfChildrenExpected = 3;
		int numberOfAdultsExpected = 8;
		int numberOfPersonsExpected = 11;
		//WHEN
		NumberOfAdultsAndChildrenDto numberOfAdultsAndChildrenDto = firestationManager.getNumbersOfChildrenAndAdultsByStation(3);
		//THEN
		Assertions.assertEquals(numberOfChildrenExpected, numberOfAdultsAndChildrenDto.getNumberChildren());
		Assertions.assertEquals(numberOfAdultsExpected, numberOfAdultsAndChildrenDto.getNumberAdults());
		Assertions.assertEquals(numberOfPersonsExpected, numberOfAdultsAndChildrenDto.getNumberAdults()+numberOfAdultsAndChildrenDto.getNumberChildren());
	}
}