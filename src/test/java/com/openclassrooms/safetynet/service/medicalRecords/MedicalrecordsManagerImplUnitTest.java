package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MedicalrecordsManagerImplUnitTest {

	MedicalrecordsManager medicalrecordsManager;

	@Mock
	DataStorage mockDataStorage;

	@BeforeEach
	public void Init() {
		medicalrecordsManager = new MedicalrecordsManagerImpl(mockDataStorage);
	}

	@Test
	void addMedicalRecord() {
		//WHEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());
		Assertions.assertNotNull(datas.getMedicalrecords());
		Medicalrecord medicalrecordToAdd = new Medicalrecord("firstNameTest", "lastNameTest");

		//WHEN
		when(mockDataStorage.getMedicalRecord()).thenReturn(new ArrayList<>());
		

		//THEN
		medicalrecordsManager.addMedicalRecord(medicalrecordToAdd);

		Assertions.assertFalse(datas.getMedicalrecords().isEmpty());
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertEquals(1, datas.getMedicalrecords().size());

		Medicalrecord medicalrecordAdded = datas.getMedicalrecords().get(0);
		Assertions.assertEquals(medicalrecordToAdd, medicalrecordAdded);

	}

	@Test
	void addMedicalRecordAlreadyExisting() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertEquals(0, datas.getMedicalrecords().size());

		Medicalrecord medicalrecordExisting = new Medicalrecord("firstNameTest", "lastNameTest");
		datas.getMedicalrecords().add(medicalrecordExisting);

		//WHEN
		when(mockDataStorage.getMedicalRecord()).thenReturn(datas.getMedicalrecords());
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(Optional.of(medicalrecordExisting));

		//THEN
		Assertions.assertFalse(datas.getMedicalrecords().isEmpty());
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertEquals(1, datas.getMedicalrecords().size());

		Medicalrecord medicalrecordToAdd = new Medicalrecord("firstNameTest", "lastNameTest");
		Assertions.assertThrows(RuntimeException.class, () -> medicalrecordsManager.addMedicalRecord(medicalrecordToAdd));

	}

	@Test
	void updateMedicalRecord() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());
		Assertions.assertNotNull(datas.getMedicalrecords());

		Medicalrecord existingMedicalrecord = new Medicalrecord("firstNametest", "lastNameTest");
		datas.getMedicalrecords().add(existingMedicalrecord);

		Medicalrecord medicalrecordUpdate = new Medicalrecord("firstNametest", "lastNameTest");
		medicalrecordUpdate.setBirthdate("03/03/1983");

		//WHEN
		when(mockDataStorage.getMedicalRecord()).thenReturn(datas.getMedicalrecords());
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(Optional.of(existingMedicalrecord));


		medicalrecordsManager.updateMedicalRecord(medicalrecordUpdate);
		Assertions.assertEquals("1983-03-03", datas.getMedicalrecords().get(0).getBirthdate().toString());

		//THEN
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertFalse(datas.getMedicalrecords().isEmpty());
		Assertions.assertEquals(1, datas.getMedicalrecords().size());

		Medicalrecord medicalrecordUpdated = datas.getMedicalrecords().get(0);
		Assertions.assertEquals(medicalrecordUpdate, medicalrecordUpdated);
	}

	@Test
	void deleteMedicalRecord() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());

		Medicalrecord existingMedicalRecord = new Medicalrecord("firstNametest", "lastNameTest");
		datas.getMedicalrecords().add(existingMedicalRecord);
		Assertions.assertEquals(1, datas.getMedicalrecords().size());

		//WHEN
		when(mockDataStorage.getMedicalRecord()).thenReturn(datas.getMedicalrecords());
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(Optional.of(existingMedicalRecord));


		//THEN
		Medicalrecord medicalrecordToDelete = new Medicalrecord("firstNametest", "lastNameTest");
		medicalrecordsManager.deleteMedicalRecord(medicalrecordToDelete);

		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());
		Assertions.assertEquals(0, datas.getMedicalrecords().size());
	}

	@Test
	void getMedicalRecordByPersonId() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertTrue(datas.getMedicalrecords().isEmpty());

		Medicalrecord medicalrecordExisting = new Medicalrecord("firstNameTest", "lastNameTest");
		datas.getMedicalrecords().add(medicalrecordExisting);

		//WHEN
		when(mockDataStorage.getMedicalRecordById(any())).thenReturn(Optional.of(medicalrecordExisting));

		medicalrecordsManager.getMedicalRecordByPersonId(medicalrecordExisting.getId());
		Optional medicalRecordID = medicalrecordsManager.getMedicalRecordByPersonId(new Id("firstNameTest", "lastNameTest"));


		//THEN
		Assertions.assertNotNull(datas.getMedicalrecords());
		Assertions.assertFalse(datas.getMedicalrecords().isEmpty());

		Assertions.assertEquals(Optional.of(medicalrecordExisting), medicalRecordID);

	}
}