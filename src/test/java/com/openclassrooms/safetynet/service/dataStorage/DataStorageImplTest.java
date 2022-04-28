package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataStorageImplTest {
	@Mock
	private Data mockData;
	@Autowired
	DataStorage dataStorage;

	@Test
	void getPersons() {
		List<Person> person = new ArrayList<>();
		person.add (new Person("John", "Boyd"));

		when(mockData.getPersons()).thenReturn(person);
		Stream<Person> personList = dataStorage.getPersons();

		assertEquals(person.stream(), personList);
	}

	@Test
	void getPersonById() {

	}

	@Test
	void getPersonsByAddress() {
	}

	@Test
	void getPersonsByStation() {
	}

	@Test
	void getFireStations() {
	}

	@Test
	void getFirestationsByNumber() {
	}

	@Test
	void getFirestationsByAddress() {
	}

	@Test
	void getMedicalRecord() {
	}

	@Test
	void getMedicalRecordById() {
	}
}