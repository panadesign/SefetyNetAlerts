package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManagerImpl;
import com.openclassrooms.safetynet.service.person.PersonManagerImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@SpringBootTest()
class MedicalrecordsIT {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	private MedicalrecordsManager medicalrecordsManager;

	private Medicalrecord medicalrecord;
	
	@BeforeEach
	void setUp() throws IOException {
		dataStorage = new DataStorageImpl();
		medicalrecordsManager = new MedicalrecordsManagerImpl(dataStorage);
	}
	
	@Test
	void addMedicalrecord() {

		//GIVEN
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medicalrecord = new Medicalrecord("Jeremy", "Charpentier", "02/23/1984", medications, allergies);

		//WHEN
		medicalrecordsManager.addMedicalRecord(medicalrecord);

		//THEN
		assertTrue(dataStorage
				.getMedicalrecords()
				.contains(medicalrecord));

	}

	@Test
	void addMedicalrecordException() {
		medicalrecord = new Medicalrecord("Tenley", "Boyd");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.addMedicalRecord(medicalrecord));
	}

	@Test
	void updateMedicalrecord() {
		medicalrecord = new Medicalrecord("John", "Boyd", "01/05/1984");

		medicalrecordsManager.updateMedicalRecord(medicalrecord);

		assertTrue(dataStorage
				.getMedicalrecords()
				.stream()
				.anyMatch(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd")));
	}

	@Test
	void updateMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test", "01/05/1984");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.updateMedicalRecord(medicalrecord));
	}

	@Test
	void deleteMedicalrecord() {
		medicalrecord = new Medicalrecord("Jacob", "Boyd", "01/05/1984");

		medicalrecordsManager.deleteMedicalRecord(medicalrecord);

		assertTrue(dataStorage
				.getMedicalrecords()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Boyd")));
	}

	@Test
	void deleteMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.deleteMedicalRecord(medicalrecord));
	}
	

}