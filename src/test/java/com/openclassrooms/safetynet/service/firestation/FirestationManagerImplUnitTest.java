package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FirestationManagerImplUnitTest {

	@Mock
	DataStorage mockDataStorage;

	FirestationManager firestationManager;

	@BeforeEach
	public void Init() {
		firestationManager = new FirestationManagerImpl(mockDataStorage);
	}

	@Test
	void addFirestationTest() {
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());

		firestationManager.addFirestation(new Firestation(5, "test"));

		assertEquals(1, datas.getFirestations().size());
	}

	@Test
	void addExistingFirestationTest() {
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		Firestation existingFirestation = new Firestation(1, "address");
		datas.getFirestations().add(existingFirestation);

		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());

		Firestation firestationToAdd = new Firestation(1, "address");

		Assertions.assertThrows(RuntimeException.class, () -> firestationManager.addFirestation(firestationToAdd));


	}

	@Test
	void updateFirestationTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		Firestation firestationToUpdate = new Firestation(2, "address");
		datas.getFirestations().add(firestationToUpdate);

		Firestation firestationUpdatedDatas = new Firestation(firestationToUpdate.getStation(), firestationToUpdate.getAddress());
		firestationUpdatedDatas.setAddress("address changed");

		//WHEN
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());


		Firestation firestationUpdated = new Firestation(3, "address");
		firestationManager.updateFirestation(firestationUpdated);

		//THEN
		assertEquals(datas.getFirestations().get(0), firestationUpdated);
	}

	@Test
	void updateFirestationNotExistingTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		Firestation firestationToUpdate = new Firestation(3, "address");

		//WHEN
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());

		Firestation firestationUpdated = new Firestation(3, "address");

		//THEN
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());
		Assertions.assertThrows(RuntimeException.class, () -> firestationManager.updateFirestation(firestationToUpdate));
	}

	@Test
	void deleteFirestationTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		Firestation firestationToDelete = new Firestation(1, "address");
		datas.getFirestations().add(firestationToDelete);

		//WHEN
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());
		when(mockDataStorage.getData()).thenReturn(datas);

		firestationManager.deleteFirestation(firestationToDelete);

		//THEN
		assertEquals(0, datas.getFirestations().size());
	}

	@Test
	void deleteFirestationNotExistingTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());

		Firestation firestationToDelete = new Firestation(1, "address");

		//WHEN
		when(mockDataStorage.getFirestations()).thenReturn(datas.getFirestations());
		when(mockDataStorage.getData()).thenReturn(datas);

		//THEN
		Assertions.assertThrows(RuntimeException.class, () -> firestationManager.deleteFirestation(firestationToDelete));
	}

	@Test
	void getPhoneNumbersByFirestationNumber() {
		//GIVEN
		Data data = new Data();

		List<Firestation> firestations = new ArrayList<>();
		firestations.add(new Firestation(1, "address1"));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("pers1", "pers1", "address1", "123"));
		persons.add(new Person("pers2", "pers2", "address1", "234"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(firestations);
		when(mockDataStorage.getPersons()).thenReturn(persons);

		Set<String> phoneNumbers = firestationManager.getPhoneNumbersByFirestationNumber(1);

		//THEN
		Assertions.assertTrue(phoneNumbers.contains("123"));

	}
}