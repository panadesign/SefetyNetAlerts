package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FirestationManagerImplTest {

	@Mock
	DataStorage mockDataStorage;

	@Autowired
	FirestationManager firestationManager;

	@Test
	void addFirestationTest() {

		when(mockDataStorage.getFirestations()).thenReturn(Stream.empty());
		when(mockDataStorage.getData().getFirestations()).thenReturn(new ArrayList<>());
		firestationManager.addFirestation(new Firestation(5, "test"));

		Assertions.assertNotNull(mockDataStorage.getFirestations(), "Stream not empty");

	}
}