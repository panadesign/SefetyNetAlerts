package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.exception.BadRequestException;
import com.openclassrooms.safetynet.model.*;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FirestationManagerTest {
	
	@Mock
	DataStorage mockDataStorage;
	
	Medicalrecord medicalrecord = new Medicalrecord();
	
	@Mock
	MedicalrecordsManagerImpl mockMedicalrecordsManager;

	@InjectMocks
	FirestationManagerImpl firestationManager;


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

		Assertions.assertThrows(BadRequestException.class, () -> firestationManager.addFirestation(firestationToAdd));

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
		Assertions.assertThrows(BadRequestException.class, () -> firestationManager.updateFirestation(firestationToUpdate));
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
		Assertions.assertThrows(BadRequestException.class, () -> firestationManager.deleteFirestation(firestationToDelete));
	}

	@Test
	void getPhoneNumbersByFirestationNumber() {
		//GIVEN
		Data datas = new Data();

		datas.getFirestations().add(new Firestation(1, "address1"));

		datas.getPersons().add(new Person("pers1", "pers1", "address1", "123"));
		datas.getPersons().add(new Person("pers2", "pers2", "address1", "234"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(datas.getFirestations());
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());

		Set<String> phoneNumbers = firestationManager.getPhoneByFirestationNumber(1);

		//THEN
		Assertions.assertTrue(phoneNumbers.contains("123"));
	}

	@Test
	void getPersonsByAddress() {
		//GIVEN
		Data datas = new Data();

		datas.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		datas.getPersons().add(new Person("firstName2", "lastName2", "address2", "123"));
		datas.getPersons().add(new Person("firstName3", "lastName3", "address2", "123"));

		datas.getFirestations().add(new Firestation(1, "address2"));

		datas.getMedicalrecords().add(new Medicalrecord("firstName1", "lastName1", "02/02/1987"));
		datas.getMedicalrecords().add(new Medicalrecord("firstName2", "lastName2", "02/02/1987"));
		datas.getMedicalrecords().add(new Medicalrecord("firstName3", "lastName3", "02/02/1987"));


		//WHEN
		when(mockDataStorage.getFirestationsByAddress(eq("address2"))).thenReturn(datas.getFirestations().stream().filter(f -> f.getAddress().equals("address2")).collect(Collectors.toList()));
		when(mockDataStorage.getPersonsByAddress(eq("address2"))).thenReturn(datas.getPersons().stream().filter(f -> f.getAddress().equals("address2")).collect(Collectors.toList()));
		when(mockDataStorage.getMedicalrecords()).thenReturn(datas.getMedicalrecords());
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(datas.getMedicalrecords().stream().findFirst());


		List<PersonsByAddressDto> personsByAddressDto = firestationManager.getPersonsByAddress("address2");

		Assertions.assertEquals(2, personsByAddressDto.size());


	}

	@Test
	void getPersonsByStation() {
		//GIVEN
		Data datas = new Data();
		datas.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		datas.getPersons().add(new Person("firstName2", "lastName2", "address2", "123"));
		datas.getPersons().add(new Person("firstName3", "lastName3", "address2", "123"));
		datas.getPersons().add(new Person("firstName4", "lastName4", "address2", "123"));
		datas.getPersons().add(new Person("firstName5", "lastName5", "address2", "123"));

		datas.getFirestations().add(new Firestation(1, "address2"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(datas.getFirestations());
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());

		List<PersonsByStationDto> personsByStation = firestationManager.getPersonsByStation(1);

		//THEN
		Assertions.assertEquals(4, personsByStation.size());

	}

	/*
	@Test
	void getNumbersOfChildrenAndAdultsByStation() {
		//GIVEN
		Data datas = new Data();

		datas.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		datas.getPersons().add(new Person("firstName2", "lastName2", "address1", "123"));
		datas.getPersons().add(new Person("firstName3", "lastName3", "address1", "123"));

		datas.getMedicalrecords().add(new Medicalrecord("firstName1", "lastName1", "02/02/2012"));
		datas.getMedicalrecords().add(new Medicalrecord("firstName2", "lastName2", "02/02/2000"));
		datas.getMedicalrecords().add(new Medicalrecord("firstName3", "lastName3", "02/02/2015"));

		datas.getFirestations().add(new Firestation(1, "address1"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(datas.getFirestations());

//		when(mockDataStorage.getMedicalRecordById(data.getPersons().get(0).getId())).thenReturn(Optional.of(new Medicalrecord("firstName1", "lastName1")));
//		when(mockDataStorage.getMedicalRecordById(data.getPersons().get(1).getId())).thenReturn(Optional.of(new Medicalrecord("firstName2", "lastName2")));
//		when(mockDataStorage.getMedicalRecordById(data.getPersons().get(2).getId())).thenReturn(Optional.of(new Medicalrecord("firstName3", "lastName3")));
//		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(datas.getMedicalrecords().get(0).getId())).thenReturn(Optional.of(datas.getMedicalrecords().get(0)));
//		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(medicalrecord.getId())).thenReturn(Optional.of(datas.getMedicalrecords().get(1)));
//		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(medicalrecord.getId())).thenReturn(Optional.of(datas.getMedicalrecords().get(2)));
//		when(mockDataStorage.getMedicalRecordById(datas.getMedicalrecords().get(0).getId())).thenReturn(Optional.of(medicalrecord));
//		when(mockDataStorage.getMedicalRecordById(datas.getMedicalrecords().get(1).getId())).thenReturn(Optional.of(medicalrecord));
//		when(mockDataStorage.getMedicalRecordById(datas.getMedicalrecords().get(2).getId())).thenReturn(Optional.of(medicalrecord));
//		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		
		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(any())).thenReturn(Optional.of(new Medicalrecord("firstName1", "lastName1")));
		
		
		firestationManager.getNumbersOfChildrenAndAdultsByStation(1);
		int numberOfChild = firestationManager.getNumbersOfChildrenAndAdultsByStation(1).getNumberChildren();
		int numberOfAdults = firestationManager.getNumbersOfChildrenAndAdultsByStation(1).getNumberAdults();

		//THEN
		Assertions.assertEquals(1, numberOfAdults);
		Assertions.assertEquals(2, numberOfChild);

	}*/
}