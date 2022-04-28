package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
class PersonManagerImplTest {

	@Autowired
	DataStorage dataStorage;

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

}