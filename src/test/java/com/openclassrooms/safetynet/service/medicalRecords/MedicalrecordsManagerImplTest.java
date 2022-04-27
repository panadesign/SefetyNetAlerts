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

}