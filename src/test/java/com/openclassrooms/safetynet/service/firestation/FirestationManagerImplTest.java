package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class FirestationManagerImplTest {

	@Mock
	DataStorage mockDataStorage;

	FirestationManager firestationManager;

	@BeforeEach
	public void Init(){
		firestationManager = new FirestationManagerImpl(mockDataStorage);
	}

	@Test
	void addFirestationTest() {
		when(mockDataStorage.getFirestations()).thenReturn(Stream.empty());

		Data datas = new Data();
		when(mockDataStorage.getData()).thenReturn(datas);

		datas.setFirestations(new ArrayList<>());
		firestationManager.addFirestation(new Firestation(5, "test"));

		assertEquals(1, datas.getFirestations().size());
	}

	@Test
	void deleteFirestationTest() {
		Data datas = new Data();

		when(mockDataStorage.getFirestations()).thenReturn(Stream.of(new Firestation(1, "test")));
		when(mockDataStorage.getData()).thenReturn(datas);

		firestationManager.deleteFirestation(new Firestation(1, "test"));

		assertEquals(0, datas.getFirestations().size());
	}


}