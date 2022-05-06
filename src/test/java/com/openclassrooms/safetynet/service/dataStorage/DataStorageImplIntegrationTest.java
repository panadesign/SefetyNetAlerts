package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DataStorageImplIntegrationTest {
	@Mock
	private Data mockData;

	DataStorage dataStorage;

	DataStorageImplIntegrationTest() throws IOException {
	}

	@Test
	void getPerson() {
		List<Person> persons = new ArrayList<>();
		Person person = new Person("John", "Boyd");
		persons.add(person);

		when(mockData.getPersons()).thenReturn(persons);
		List<Person> result = mockData.getPersons();

		Assertions.assertEquals(1, result.size());
	}

	@Test
	void getPersonById() {

	}

	@Test
	void getPersonsByAddress() {

		List<Person> persons = new ArrayList<>();
		Person person = new Person("test", "TEST", "addressTest", "123456");
		persons.add(person);

		when(mockData.getPersons()).thenReturn(persons);


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