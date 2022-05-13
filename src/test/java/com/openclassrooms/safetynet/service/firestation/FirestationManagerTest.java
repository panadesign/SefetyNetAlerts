package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.exception.BadRequestException;
import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FirestationManagerTest {

	@Mock
	DataStorage mockDataStorage;
	@Mock
	MedicalrecordsManager medicalrecordsManager;

	@InjectMocks
	FirestationManagerImpl firestationManager;

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
		Data data = new Data();

		data.getFirestations().add(new Firestation(1, "address1"));

		data.getPersons().add(new Person("pers1", "pers1", "address1", "123"));
		data.getPersons().add(new Person("pers2", "pers2", "address1", "234"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(data.getFirestations());
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());

		Set<String> phoneNumbers = firestationManager.getPhoneNumbersByFirestationNumber(1);

		//THEN
		Assertions.assertTrue(phoneNumbers.contains("123"));
	}

	@Test
	void getPersonsByAddress() {
		//GIVEN
		Data data = new Data();

		data.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		data.getPersons().add(new Person("firstName2", "lastName2", "address2", "123"));
		data.getPersons().add(new Person("firstName3", "lastName3", "address2", "123"));

		data.getFirestations().add(new Firestation(1, "address2"));

		data.getMedicalrecords().add(new Medicalrecord("firstName1", "lastName1", "02/02/1987"));
		data.getMedicalrecords().add(new Medicalrecord("firstName2", "lastName2", "02/02/1987"));
		data.getMedicalrecords().add(new Medicalrecord("firstName3", "lastName3", "02/02/1987"));


		//WHEN
		when(mockDataStorage.getFirestationsByAddress(eq("address2"))).thenReturn(data.getFirestations().stream().filter(f -> f.getAddress().equals("address2")).collect(Collectors.toList()));
		when(mockDataStorage.getPersonsByAddress(eq("address2"))).thenReturn(data.getPersons().stream().filter(f -> f.getAddress().equals("address2")).collect(Collectors.toList()));
		when(mockDataStorage.getMedicalrecords()).thenReturn(data.getMedicalrecords());
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(data.getMedicalrecords().stream().findFirst());


		List<PersonsByAddressDto> personsByAddressDto = firestationManager.getPersonsByAddress("address2");

		Assertions.assertEquals(2, personsByAddressDto.size());


	}

	@Test
	void getPersonsByStation() {
		//GIVEN
		Data data = new Data();
		data.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		data.getPersons().add(new Person("firstName2", "lastName2", "address2", "123"));
		data.getPersons().add(new Person("firstName3", "lastName3", "address2", "123"));
		data.getPersons().add(new Person("firstName4", "lastName4", "address2", "123"));
		data.getPersons().add(new Person("firstName5", "lastName5", "address2", "123"));

		data.getFirestations().add(new Firestation(1, "address2"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(data.getFirestations());
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());

		List<PersonsByStationDto> personsByStation = firestationManager.getPersonsByStation(1);

		//THEN
		Assertions.assertEquals(4, personsByStation.size());

	}

	@Test
	void getNumbersOfChildrenAndAdultsByStation() {
		//GIVEN
		Data data = new Data();

		List<PersonsByStationDto> personsByStationDtoList = new ArrayList<>();
		PersonsByStationDto personsByStationDto1 = new PersonsByStationDto(new Person("firstName1", "lastName1", "address1", "123"));
		PersonsByStationDto personsByStationDto2 = new PersonsByStationDto(new Person("firstName2", "lastName2", "address1", "123"));
		PersonsByStationDto personsByStationDto3 = new PersonsByStationDto(new Person("firstName3", "lastName3", "address1", "123"));

		data.getPersons().add(new Person("firstName1", "lastName1", "address1", "123"));
		data.getPersons().add(new Person("firstName2", "lastName2", "address1", "123"));
		data.getPersons().add(new Person("firstName3", "lastName3", "address1", "123"));

		data.getMedicalrecords().add(new Medicalrecord("firstName1", "lastName1", "02/02/2012"));
		data.getMedicalrecords().add(new Medicalrecord("firstName2", "lastName2", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName3", "lastName3", "02/02/2015"));

		data.getFirestations().add(new Firestation(1, "address1"));

		//WHEN
		when(mockDataStorage.getFirestationsByNumber(1)).thenReturn(data.getFirestations());
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		when(medicalrecordsManager.getMedicalRecordByPersonId(data.getPersons().get(0).getId())).thenReturn(Optional.of(data.getMedicalrecords().get(0)));
		when(medicalrecordsManager.getMedicalRecordByPersonId(data.getPersons().get(1).getId())).thenReturn(Optional.of(data.getMedicalrecords().get(1)));
		when(medicalrecordsManager.getMedicalRecordByPersonId(data.getPersons().get(2).getId())).thenReturn(Optional.of(data.getMedicalrecords().get(2)));


		firestationManager.getNumbersOfChildrenAndAdultsByStation(1);
		int numberOfChild = firestationManager.getNumbersOfChildrenAndAdultsByStation(1).getNumberChildren();
		int numberOfAdults = firestationManager.getNumbersOfChildrenAndAdultsByStation(1).getNumberAdults();

		//THEN
		Assertions.assertEquals(1, numberOfAdults);
		Assertions.assertEquals(2, numberOfChild);


	}
}