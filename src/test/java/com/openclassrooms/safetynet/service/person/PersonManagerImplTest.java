package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.DataStorage;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonManagerImplTest {

	@Autowired
	DataStorage dataStorage;

	@Autowired
	PersonManagerImpl personManager;

	Person person;

	@Test
	@Order(1)
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

	/*@Test
	void updatePerson() {
		person = new Person("Jacob", "Boyd");


	}*/

	@Test
	void deletePerson() {


		personManager.deletePerson(new Person("Jacob", "Boyd"));

		boolean personDeleted =
				dataStorage
						.getData()
						.getPersons()
						.stream()
						.noneMatch(p -> p.getFirstName().equals("Jacob")) && person.getLastName().equals("Boyd");

		assertTrue(personDeleted);
	}

	@Test
	void getAllMailsByCity() {
		//GIVEN
		String city = "Culver";
		String emailExpected = "aly@imail.com";

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		boolean mailExpectedInList = getAllMailByCity.contains(emailExpected);

		//THEN
		assertTrue(mailExpectedInList);
	}

	@Test
	void getAllMailsByCityWithoutCity() {
		//GIVEN
		String city = "";
		String emailExpected = "aly@imail.com";

		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		boolean mailExpectedInList = getAllMailByCity.contains(emailExpected);

		//THEN
		assertFalse(mailExpectedInList);
	}

	@Test
	void getPersons() {
		List<Person> allPersons = dataStorage
				.getPersons()
				.collect(Collectors.toList());

		assertNotNull(allPersons);
	}



}