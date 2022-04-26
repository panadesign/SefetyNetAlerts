package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.firestation.FirestationManagerImpl;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FirestationServiceTest {

	DataStorage dataStorage;

	@InjectMocks
	FirestationManagerImpl firestationManager;

	@Test
	public void findByIdTest() {
		//GIVEN
		Integer expectedId = 1;

		//WHEN
		Stream id = dataStorage.getFirestationsByNumber(1);



		//THEN
		assertEquals(expectedId, id);
	}
}
