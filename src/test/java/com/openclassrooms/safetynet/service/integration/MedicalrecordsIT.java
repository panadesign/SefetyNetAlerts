package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManager;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
class MedicalrecordsIT {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	private MedicalRecordsManager medicalrecordsManager;

	private Medicalrecord medicalrecord;

	@BeforeEach
	void setUp() throws IOException {
		dataStorage = new DataStorageImpl();
		medicalrecordsManager = new MedicalRecordsManagerImpl(dataStorage);
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
		Assertions.assertTrue(dataStorage
				.getMedicalrecords()
				.contains(medicalrecord));

	}

	@Test
	void addMedicalrecordException() {
		medicalrecord = new Medicalrecord("Tenley", "Boyd");
		assertThrows(BadRequestExceptions.class, () -> medicalrecordsManager.addMedicalRecord(medicalrecord));
	}

	@Test
	void updateMedicalrecord() {
		medicalrecord = new Medicalrecord("John", "Boyd", "01/05/1984");

		medicalrecordsManager.updateMedicalRecord(medicalrecord);

		Assertions.assertTrue(dataStorage
				.getMedicalrecords()
				.stream()
				.anyMatch(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd")));
	}

	@Test
	void updateMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test", "01/05/1984");
		assertThrows(BadRequestExceptions.class, () -> medicalrecordsManager.updateMedicalRecord(medicalrecord));
	}

	@Test
	void deleteMedicalrecord() {
		medicalrecord = new Medicalrecord("Jacob", "Boyd", "01/05/1984");

		medicalrecordsManager.deleteMedicalRecord("Jacob", "Boyd");

		Assertions.assertTrue(dataStorage
				.getMedicalrecords()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Boyd")));
	}

	@Test
	void deleteMedicalrecordException() {
		medicalrecord = new Medicalrecord("Test", "Test");
		assertThrows(BadRequestExceptions.class, () -> medicalrecordsManager.deleteMedicalRecord("Test", "Test"));
	}

	@Test
	void shouldReturnMedicalRecordJohnBoyd() {
		Person person = new Person("John", "Boyd");
		Optional<Medicalrecord> result = medicalrecordsManager.getMedicalRecordByPersonId(person.getId());

		Assertions.assertEquals(result.get().getFirstName(), person.getFirstName());
	}


}