package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
class PersonManagerImplIT {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	PersonManager personManager;

	Person person;

	@BeforeEach
	void setUp() throws IOException {
		DataStorage dataStorage1 = new DataStorageImpl();
	}

	@Test
	void addPerson() throws IOException {

		//GIVEN
		person = new Person("test", "Test", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");

		//WHEN
		personManager.addPerson(person);

		boolean personCreated =
				dataStorage
						.getPersons()
						.contains(person);

		//THEN
		assertTrue(personCreated);

	}

	@Test
	void addPersonException() {

		//GIVEN
		person = new Person("John", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");

		//WHEN
		personManager.addPerson(person);

		//THEN
		assertThrows(RuntimeException.class, () -> personManager.addPerson(person));

	}

	@Test
	void updatePerson() {
		personManager.updatePerson(new Person("Jacob", "Boyd", "testMail"));

		assertTrue(dataStorage
				.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Boyd"))
				.anyMatch(p -> p.getEmail().equals("testMail")));

	}

	@Test
	void updatePersonException() {

		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		assertThrows(RuntimeException.class, () -> personManager.updatePerson(person));

	}

	@Test
	void deletePerson() throws IOException {
		personManager.addPerson(new Person("1", "1"));
		personManager.deletePerson(new Person("1", "1"));

		assertTrue(dataStorage
				.getPersons()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("1") && person.getLastName().equals("1")));
	}

	@Test
	void deletePersonException() {

		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		assertThrows(RuntimeException.class, () -> personManager.deletePerson(person));

	}

	@Test
	void getAllMailsByCity() {
		//GIVEN
		String city = "Culver";
		String emailExpected = "aly@imail.com";
		int numberOfEmailExpected = 15;

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		//THEN
		Assertions.assertTrue(getAllMailByCity.contains(emailExpected));
		Assertions.assertEquals(numberOfEmailExpected, getAllMailByCity.size());
	}

	@Test
	void getAllMailsByCityWithoutCity() {
		//GIVEN
		String city = "";
		String emailExpected = "aly@imail.com";

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		//THEN
		assertTrue(getAllMailByCity.isEmpty());
		assertFalse(getAllMailByCity.contains(emailExpected));
	}

	@Test
	void getPersons() {
		Person personExpected = new Person("John", "Boyd");
		List<Person> allPersons =
				dataStorage
						.getPersons();

		Assertions.assertNotNull(allPersons);
		Assertions.assertFalse(allPersons.isEmpty());
		Assertions.assertTrue(allPersons.contains(personExpected));
	}

	@Test
	void getPersonsByFirstNameAndLastName() throws IOException {

		//GIVEN
		PersonByFirstNameAndLastNameDto personExpected = new PersonByFirstNameAndLastNameDto(new Person("John", "Boyd"));

		//int numberOfPersonsExpected = 6;
		//WHEN
		List<PersonByFirstNameAndLastNameDto> persons = personManager.getPersonsByFirstNameAndLastName(personExpected.getFirstName(), personExpected.getLastName());
		//THEN
		//Assertions.assertEquals(numberOfPersonsExpected, persons.size());
		Assertions.assertTrue(persons.contains(personExpected));
	}

	@Test
	void getPersonsByAddressStationForFloodAlert() {

	}

}