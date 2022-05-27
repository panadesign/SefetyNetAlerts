package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.dto.ChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.FamiliesByStationDto;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.person.PersonManager;
import com.openclassrooms.safetynet.service.person.PersonManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
class PersonIT {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	private PersonManager personManager;

	Person person;

	@BeforeEach
	void setUp() throws IOException {
		dataStorage = new DataStorageImpl();
		personManager = new PersonManagerImpl(dataStorage);
	}

	@Test
	void shouldAddAPerson() {
		//GIVEN
		person = new Person("test", "Test", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");

		//WHEN
		personManager.addPerson(person);
		boolean personCreated = dataStorage.getPersons().contains(person);

		//THEN
		Assertions.assertTrue(personCreated);

	}

	@Test
	void shouldReturnBadRequestExceptionsWhenAddAPersonAlreadyExisting() {
		//GIVEN
		person = new Person("John", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");

		//THEN
		assertThrows(BadRequestExceptions.class, () -> personManager.addPerson(person));

	}

	@Test
	void shouldUpdateAPersonWhenPersonAlreadyExisting() {
		personManager.updatePerson(new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-0000", "jaboyd@email.com"));

		Assertions.assertTrue(dataStorage.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Boyd"))
				.anyMatch(p -> p.getPhone().equals("841-874-0000")));


	}

	@Test
	void shouldReturnBadRequestExceptionsWhenUpdateANotExistentPerson() {

		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		Assertions.assertThrows(BadRequestExceptions.class, () -> personManager.updatePerson(person));

	}

	@Test
	void shouldDeleteWhenPersonIsAlreadyExisting() {
		personManager.deletePerson(new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com"));

		Assertions.assertTrue(dataStorage.getPersons()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("John") && person.getLastName().equals("Boyd")));
	}

	@Test
	void shouldReturnBadRequestExceptionsWhenDeletingANotExistentPerson() {

		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		assertThrows(BadRequestExceptions.class, () -> personManager.deletePerson(person));

	}

	@Test
	void shouldReturnAMailExistingWithCulverCity() {
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
	void shouldReturnAnEmptyList() {
		//GIVEN
		String city = "";
		//WHEN
		Set<String> getAllMailByCity = personManager.getAllMailsByCity(city);

		//THEN
		Assertions.assertTrue(getAllMailByCity.isEmpty());
	}

	@Test
	void shouldReturnAnExistingPerson() {
		Person personExpected = new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com");

		List<Person> allPersons = dataStorage.getPersons();

		Assertions.assertNotNull(allPersons);
		Assertions.assertFalse(allPersons.isEmpty());
		Assertions.assertTrue(allPersons.contains(personExpected));
	}

	@Test
	void shouldReturnAPersonUsingFirstNameAndLastname() {
		//GIVEN
		PersonByFirstNameAndLastNameDto personExpected = new PersonByFirstNameAndLastNameDto(new Person("Roger", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com"), new Medicalrecord("Roger", "Boyd", "09/06/2017", new ArrayList<>(), new ArrayList<>()));

		//WHEN
		List<PersonByFirstNameAndLastNameDto> persons = personManager.getPersonsByFirstNameAndLastName(personExpected.getFirstName(), personExpected.getLastName());

		//THEN
		Assertions.assertTrue(persons.contains(personExpected));
	}

	@Test
	void shouldReturnAddressesForStationNumber1And3() {
		//GIVEN
		List<Integer> stations = new ArrayList<>();
		stations.add(1);
		stations.add(3);

		//WHEN
		Map<String, List<FamiliesByStationDto>> persons = personManager.getPersonsByAddressStationForFloodAlert(stations);

		//THEN
		Assertions.assertTrue(persons.containsKey("644 Gershwin Cir"));
		Assertions.assertTrue(persons.containsKey("748 Townings Dr"));
		Assertions.assertFalse(persons.containsKey("489 Manchester St"));
	}

	@Test
	void get2ChildrenByAddress1509CulverSt() {
		//GIVEN
		String address = "1509 Culver St";

		//WHEN
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);

		//THEN
		Assertions.assertEquals(2, childListAndFamilyListDto.getGetChildrenByAddressDto().size());
	}

	@Test
	void get3AdultsByAddress1509CulverSt() {
		//GIVEN
		String address = "1509 Culver St";

		//WHEN
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);

		//THEN
		Assertions.assertEquals(3, childListAndFamilyListDto.getGetAdultsByAddressDto().size());
	}

	@Test
	void get5MembersByAddress1509CulverSt() {
		//GIVEN
		String address = "1509 Culver St";

		//WHEN
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);

		//THEN
		Assertions.assertEquals(5, childListAndFamilyListDto.getGetAdultsByAddressDto().size() + childListAndFamilyListDto.getGetChildrenByAddressDto().size());
	}

}