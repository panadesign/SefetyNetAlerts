package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
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
}