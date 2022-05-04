package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.GetPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.dto.GetPersonsByAddressDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
@RunWith(SpringRunner.class)
class PersonManagerImplTest {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	PersonManager personManager;

	Person person;

	@Test
	void addPerson() {

		//GIVEN
		person = new Person("Jeremy", "Charpentier", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");

		//WHEN
		personManager.addPerson(person);

		boolean personCreated =
				dataStorage
						.getData()
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
				.getData()
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
	void deletePerson() {

		personManager.deletePerson(new Person("Jacob", "Boyd"));

		assertTrue(dataStorage
				.getData()
				.getPersons()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("Jacob") && person.getLastName().equals("Boyd")));
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

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		//THEN
		assertTrue(getAllMailByCity.contains(emailExpected));
	}

	@Test
	void getAllMailsByCityWithoutCity() {
		//GIVEN
		String city = "";
		String emailExpected = "aly@imail.com";

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		//THEN
		assertFalse(getAllMailByCity.contains(emailExpected));
	}

	@Test
	void getPersons() {
		List<Person> allPersons =
				dataStorage
						.getPersons()
						.collect(Collectors.toList());

		assertNotNull(allPersons);
	}

	@Test
	void getPersonsByFirstNameAndLastName() {
		//GIVEN
		Person person = new Person("John", "Boyd");
		int numberOfPersonsExpected = 6;

		List<GetPersonByFirstNameAndLastNameDto> persons = personManager.getPersonsByFirstNameAndLastName("John", "Boyd");

		assertEquals(numberOfPersonsExpected, persons.size());

	}

	@Test
	void getPersonsByAddressStationForFloodAlert() {
		List<Integer> firestationsNumber = new ArrayList<>();
		firestationsNumber.add(1);
		firestationsNumber.add(3);

		assertEquals(12, personManager.getPersonsByAddressStationForFloodAlert(firestationsNumber).size());

	}

}