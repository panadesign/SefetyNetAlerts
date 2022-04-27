package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.DataStorage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest()
@RunWith(SpringRunner.class)
class PersonManagerImplTest {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	FirestationManagerImpl firestationManagerImpl;

	@Test
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
}