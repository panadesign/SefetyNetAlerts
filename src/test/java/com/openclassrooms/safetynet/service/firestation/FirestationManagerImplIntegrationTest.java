package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
class FirestationManagerImplIntegrationTest {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	PersonManager personManager;
	@Autowired
	FirestationManager firestationManager;

	@Test
	void addFirestation() {

		//GIVEN
		Firestation newFirestation = new Firestation(9, "32 Rue Dupont");

		//WHEN
		firestationManager.addFirestation(newFirestation);

		//THEN
		assertTrue(dataStorage
				.getData()
				.getFirestations()
				.contains(newFirestation));

	}

	@Test
	void addFirestationException() {
		Firestation newFirestation = new Firestation(4, "489 Manchester St");
		assertThrows(RuntimeException.class, () -> firestationManager.addFirestation(newFirestation));
	}

	@Test
	void updateFirestation() {
		Firestation firestationToUpdate = new Firestation(2, "489 Manchester St");

		firestationManager.updateFirestation(firestationToUpdate);

		assertTrue(dataStorage
				.getData()
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 2)
				.anyMatch(f -> f.getAddress().equals("489 Manchester St")));

	}

	@Test
	void updateFirestationException() {
		Firestation firestationToUpdate = new Firestation(2, "test");
		assertThrows(RuntimeException.class, () -> firestationManager.updateFirestation(firestationToUpdate));
	}

	@Test
	void deleteFirestation() {
		Firestation firestationToDelete = new Firestation(4, "489 Manchester St");

		firestationManager.deleteFirestation(firestationToDelete);

		assertTrue(dataStorage
				.getData()
				.getFirestations()
				.stream()
				.filter(f -> f.getStation() == 4)
				.noneMatch(f -> f.getAddress().equals("489 Manchester St")));

	}

	@Test
	void deleteFirestationException() {
		Firestation firestationToDelete = new Firestation(1, "test");
		assertThrows(RuntimeException.class, () -> firestationManager.deleteFirestation(firestationToDelete));
	}

	@Test
	void getPhoneNumbersByFirestationNumber() {
		String phoneExpected = "841-874-7784";

		assertTrue(firestationManager.getPhoneNumbersByFirestationNumber(1)
				.contains(phoneExpected));

	}

	@Test
	void getPersonsByStation() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("lastName1", "firstName1", "address1", "villeTest1", 1111, "11111", "testMail1"));
		personList.add(new Person("lastName2", "firstName2", "address1", "villeTest2", 2222, "22222", "testMail2"));
		personList.add(new Person("lastName3", "firstName3", "address1", "villeTest3", 3333, "33333", "testMail3"));

		Firestation firestation = new Firestation(1, "address1");

		firestationManager.getPersonsByStation(firestation.getStation());

		assertEquals("address1", personList.get(1).getAddress());
		assertEquals("firstName1", personList.get(0).getFirstName());
	}


	@Test
	void getPersonsByAddress() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("lastName1", "firstName1", "address1", "villeTest1", 1111, "11111", "testMail1"));
		personList.add(new Person("lastName2", "firstName2", "address1", "villeTest2", 2222, "22222", "testMail2"));
		personList.add(new Person("lastName3", "firstName3", "address1", "villeTest3", 3333, "33333", "testMail3"));

		Firestation firestation = new Firestation(1, "address1");

		firestationManager.getPersonsByAddress(firestation.getAddress());

		assertEquals("address1", personList.get(1).getAddress());
		assertEquals("firstName1", personList.get(0).getFirstName());
	}



}