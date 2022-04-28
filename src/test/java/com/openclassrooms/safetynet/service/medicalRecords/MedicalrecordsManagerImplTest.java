package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.DataStorage;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class MedicalrecordsManagerImplTest {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	private MedicalrecordsManagerImpl medicalrecordsManager;

	private Medicalrecord medicalrecord;

	@Test
	@Order(1)
	void addMedicalrecord() {

		//GIVEN
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medicalrecord = new Medicalrecord("Jeremy", "Charpentier", "02/23/1984", medications, allergies);

		//WHEN
		medicalrecordsManager.addMedicalRecord(medicalrecord);

		Boolean medicalrecordCreated =
				dataStorage
						.getData()
						.getMedicalrecords()
						.contains(medicalrecord);

		//THEN
		assertTrue(medicalrecordCreated);

	}

	@Test
	void addMedicalrecordException() {
		medicalrecord = new Medicalrecord("John", "Boyd");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.addMedicalRecord(medicalrecord));
	}

	@Test
	@Order(2)
	void updateMedicalrecord() {
		medicalrecord = new Medicalrecord("John", "Boyd", "01/05/1984");

		medicalrecordsManager.updateMedicalRecord(medicalrecord);

		Boolean medicalrecordUpdated =
				dataStorage
						.getData()
						.getMedicalrecords()
						.stream()
						.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
						.anyMatch(p -> p.getBirthdate().equals(p.getBirthdate()));

		assertTrue(medicalrecordUpdated);
	}

	@Test
	void updateMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test", "01/05/1984");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.updateMedicalRecord(medicalrecord));
	}

	@Test
	@Order(3)
	void deleteMedicalrecord() {
		medicalrecord = new Medicalrecord("Jacob", "Boyd", "01/05/1984");

		medicalrecordsManager.deleteMedicalRecord(medicalrecord);

		boolean medicalrecordDeleted =
				dataStorage
						.getData()
						.getMedicalrecords()
						.stream()
						.noneMatch(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Boyd"));

		assertTrue(medicalrecordDeleted);
	}


	@Test
	void deleteMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test");
		assertThrows(RuntimeException.class, () -> medicalrecordsManager.deleteMedicalRecord(medicalrecord));
	}

}