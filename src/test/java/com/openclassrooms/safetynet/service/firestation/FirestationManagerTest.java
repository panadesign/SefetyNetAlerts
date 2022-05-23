package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManagerImpl;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FirestationManagerTest {
	
	@Mock
	DataStorage mockDataStorage;
	
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

		Assertions.assertEquals(1, datas.getFirestations().size());
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

		Assertions.assertThrows(BadRequestExceptions.class, () -> firestationManager.addFirestation(firestationToAdd));

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
		Assertions.assertEquals(datas.getFirestations().get(0), firestationUpdated);
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

		//THEN
		Assertions.assertTrue(datas.getFirestations().isEmpty());
		Assertions.assertNotNull(datas.getFirestations());
		Assertions.assertThrows(BadRequestExceptions.class, () -> firestationManager.updateFirestation(firestationToUpdate));
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
		Assertions.assertEquals(0, datas.getFirestations().size());
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
		Assertions.assertThrows(BadRequestExceptions.class, () -> firestationManager.deleteFirestation(firestationToDelete));
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

	
	@Test
	void getNumbersOfChildrenAndAdultsByStation() {
		/*List<PersonsByStationDto> family = new ArrayList<>();
		
		family.add(new PersonsByStationDto(new Person("Jona", "Charpentier", "address")));
		family.add(new PersonsByStationDto(new Person("Leonie", "Charpentier", "addressTest", "123")));
		family.add(new PersonsByStationDto(new Person("Jeremy", "Charpentier", "addressTest", "123")));
		
		List<Medicalrecord> medicalrecordsFamily = new ArrayList<>();
		medicalrecordsFamily.add(new Medicalrecord("Jona", "Charpentier", "06/25/2018"));
		medicalrecordsFamily.add(new Medicalrecord("Leonie", "Charpentier", "09/07/2021"));
		medicalrecordsFamily.add(new Medicalrecord("Jeremy", "Charpentier", "01/05/1984"));
		
		Firestation firestation = new Firestation(3, "addressTest");
		
		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(family.get(0).getId())).thenReturn(Optional.of(new Medicalrecord("Jona", "Charpentier")));
		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(family.get(1).getId())).thenReturn(Optional.of(new Medicalrecord("Leonie", "Charpentier")));
		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(family.get(2).getId())).thenReturn(Optional.of(new Medicalrecord("Jeremy", "Charpentier")));
		
		int numberOfChildren = firestationManager.getNumbersOfChildrenAndAdultsByStation(firestation.getStation()).getNumberChildren();
		int numberOfAdults = firestationManager.getNumbersOfChildrenAndAdultsByStation(firestation.getStation()).getNumberAdults();
		
		Assertions.assertEquals(2, numberOfChildren);
		Assertions.assertEquals(1, numberOfAdults);
		*/
	}
}