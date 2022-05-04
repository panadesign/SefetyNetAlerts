package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.GetPersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class FirestationManagerImplTest {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	PersonManager personManager;
	@Autowired
	FirestationManager firestationManager;

	@Mock
	FirestationManager mockFirestationManager;

	@Mock
	MedicalrecordsManager mockMedicalrecordsManager;

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

	@Test
	void getNumbersOfChildrenByStation() {

		List<GetPersonsByStationDto> persons = new ArrayList<>();
		persons.add(new GetPersonsByStationDto(new Person("adult1", "adult1")));
		persons.add(new GetPersonsByStationDto(new Person("child2", "child2")));
		persons.add(new GetPersonsByStationDto(new Person("child3", "child3")));

		List<Medicalrecord> medicalrecords = new ArrayList<>();
		medicalrecords.add(new Medicalrecord("adult1", "adult1", "02/02/1980"));
		medicalrecords.add(new Medicalrecord("child2", "child2", "02/02/2018"));
		medicalrecords.add(new Medicalrecord("child3", "child3", "02/02/2020"));

		when(mockFirestationManager.getPersonsByStation(1)).thenReturn(persons);
		when(mockMedicalrecordsManager.getMedicalRecordByPersonId(any())).thenReturn(Optional.of(medicalrecords.get(0))).thenReturn(Optional.of(medicalrecords.get(1))).thenReturn(Optional.of(medicalrecords.get(2)));

		assertEquals(2, firestationManager.getNumbersOfChildrenByStation(1));

	}

}