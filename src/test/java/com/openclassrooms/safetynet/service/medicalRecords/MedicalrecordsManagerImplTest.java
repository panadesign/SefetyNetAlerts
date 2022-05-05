package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MedicalrecordsManagerImplTest {

	MedicalrecordsManager medicalrecordsManager;

	@Mock
	DataStorage mockDataStorage;

	@BeforeEach
	public void Init(){
		medicalrecordsManager = new MedicalrecordsManagerImpl(mockDataStorage);
	}

	@Test
	void addMedicalRecord() {

		Data datas = new Data();
		when(mockDataStorage.getMedicalRecord()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getMedicalRecordById(new Id("", ""))).thenReturn(Optional.of(new Medicalrecord("firstName", "lastName")));

		datas.setMedicalrecords(new ArrayList<>());
		medicalrecordsManager.addMedicalRecord(new Medicalrecord("firstName", "lastName"));

		Assertions.assertEquals(1, datas.getMedicalrecords().size());

	}

	@Test
	void addMedicalRecordAlreadyExisting() {

		Data datas = new Data();
		when(mockDataStorage.getMedicalRecord()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getMedicalRecordById(new Id("firstName", "lastName"))).thenReturn(Optional.of(new Medicalrecord("firstName", "lastName")));

		datas.setMedicalrecords(new ArrayList<>());

		Assertions.assertThrows(RuntimeException.class, () -> medicalrecordsManager.addMedicalRecord(new Medicalrecord("firstName", "lastName")));

	}

	@Test
	void updateMedicalRecord() {
	}

	@Test
	void deleteMedicalRecord() {
	}

	@Test
	void getMedicalRecordByPersonId() {
	}
}