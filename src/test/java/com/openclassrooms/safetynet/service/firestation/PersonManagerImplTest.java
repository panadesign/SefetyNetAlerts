package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.DataStorage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
	FirestationManagerImpl firestationManagerImpl;

	@Test
	@Order(1)
	void addFirestation() {

		//GIVEN
		Firestation newFirestation = new Firestation(9, "32 Rue Dupont");

		//WHEN
		firestationManagerImpl.addFirestation(newFirestation);

		Boolean firestationCreated =
				dataStorage
						.getData()
						.getFirestations()
						.contains(newFirestation);

		//THEN
		assertTrue(firestationCreated);

	}

	@Test
	void addFirestationException() {
		Firestation newFirestation = new Firestation(4, "489 Manchester St");
		assertThrows(RuntimeException.class, () -> firestationManagerImpl.addFirestation(newFirestation));
	}

	@Test
	@Order(2)
	void updateFirestation() {
		Firestation firestationToUpdate = new Firestation(2, "489 Manchester St");

		firestationManagerImpl.updateFirestation(firestationToUpdate);

		boolean firestationUpdated =
				dataStorage
						.getData()
						.getFirestations()
						.stream()
						.filter(f -> f.getStation() == 2)
						.anyMatch(f -> f.getAddress().equals("489 Manchester St"));

		assertTrue(firestationUpdated);

	}

	@Test
	void updateFirestationException() {
		Firestation firestationToUpdate = new Firestation(2, "test");
		assertThrows(RuntimeException.class, () -> firestationManagerImpl.updateFirestation(firestationToUpdate));
	}

	@Test
	@Order(3)
	void deleteFirestation() {
		Firestation firestationToDelete = new Firestation(4, "489 Manchester St");

		firestationManagerImpl.deleteFirestation(firestationToDelete);

		boolean firestationDeleted =
				dataStorage
						.getData()
						.getFirestations()
						.stream()
						.filter(f -> f.getStation() == 4)
						.noneMatch(f -> f.getAddress().equals("489 Manchester St"));

		assertTrue(firestationDeleted);

	}

	@Test
	void deleteFirestationException() {
		Firestation firestationToDelete = new Firestation(1, "test");
		assertThrows(RuntimeException.class, () -> firestationManagerImpl.deleteFirestation(firestationToDelete));
	}

	@Test
	void getPhoneNumbersByFirestationNumber() {
		String phoneExpected = "841-874-7784";
		boolean phone = firestationManagerImpl.getPhoneNumbersByFirestationNumber(1)
				.contains(phoneExpected);

		assertTrue(phone);

	}

	@Test
	void getPersonsByAddress() {

	}
}