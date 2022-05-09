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
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DataStorageImplIntegrationTest {
	@Mock
	private Data mockData;

	DataStorage dataStorage = new DataStorageImpl();

	DataStorageImplIntegrationTest() throws IOException {
	}

	@Test
	void getPerson() {
		//GIVEN
		List<Person> persons = new ArrayList<>();
		Assertions.assertTrue(persons.isEmpty());
		Assertions.assertNotNull(persons);

		Person person = new Person("John", "Boyd");
		persons.add(person);

		//WHEN
		when(mockData.getPersons()).thenReturn(persons);
		dataStorage.getPersons();

		//THEN
		Assertions.assertNotNull(persons);
		Assertions.assertFalse(persons.isEmpty());
		Assertions.assertEquals("John", persons.get(0).getFirstName());
	}

	@Test
	void getPersonById() {

	}

	@Test
	void getPersonsByAddress() {
		//GIVEN
		List<Person> persons = new ArrayList<>();
		Assertions.assertTrue(persons.isEmpty());
		Assertions.assertNotNull(persons);

		Person person = new Person("test", "TEST", "addressTest", "123456");
		Person person1 = new Person("test1", "TEST1", "addressTest", "123456");
		Person person2 = new Person("test2", "TEST2", "addressTest3", "123456");

		persons.add(person);
		persons.add(person1);
		persons.add(person2);

		String address = "addressTest";

		//WHEN
		when(mockData.getPersons()).thenReturn(persons);
		List<Person> personList = dataStorage.getPersonsByAddress(address).collect(Collectors.toList());

		//THEN
		Assertions.assertFalse(persons.isEmpty());
		Assertions.assertNotNull(persons);
		Assertions.assertEquals(2, personList.size());

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