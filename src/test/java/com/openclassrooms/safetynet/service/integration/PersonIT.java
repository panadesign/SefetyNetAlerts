package com.openclassrooms.safetynet.service.integration;

import com.openclassrooms.safetynet.dto.ChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.ChildrenByAddressDto;
import com.openclassrooms.safetynet.dto.FamiliesByStationDto;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest()
class PersonIT {
	
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
	void shouldAddAPerson() throws IOException {
		
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
	void shouldReturnRuntimeExceptionWhenAddAPersonAlreadyExisting() {
		
		//GIVEN
		person = new Person("John", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		
		//THEN
		assertThrows(RuntimeException.class, () -> personManager.addPerson(person));
		
	}
	
	@Test
	void shouldUpdateAPersonWhenPersonAlreadyExisting() {
		personManager.updatePerson(new Person("Jacob", "Boyd", "testMail"));
		
		assertTrue(dataStorage
				.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Boyd"))
				.anyMatch(p -> p.getEmail().equals("testMail")));
		
	}
	
	@Test
	void shouldReturnARuntimeExceptionWhenUpdateANotExistentPerson() {
		
		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		assertThrows(RuntimeException.class, () -> personManager.updatePerson(person));
		
	}
	
	@Test
	void shouldDeleteWhenPersonIsAlreadyExisting() throws IOException {
		personManager.addPerson(new Person("1", "1"));
		personManager.deletePerson(new Person("1", "1"));
		
		assertTrue(dataStorage
				.getPersons()
				.stream()
				.noneMatch(p -> p.getFirstName().equals("1") && person.getLastName().equals("1")));
	}
	
	@Test
	void shouldReturnRuntimeExceptionWhenDeletingANotExistentPerson() {
		
		person = new Person("Jeremy", "Boyd", "33 rue Pommier", "Paris", 75013, "0134434543", "jeremy@mail.com");
		assertThrows(RuntimeException.class, () -> personManager.deletePerson(person));
		
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
		Person personExpected = new Person("Jeremy", "Charpentier");
		List<Person> allPersons =
				dataStorage
						.getPersons();
		
		Assertions.assertNotNull(allPersons);
		Assertions.assertFalse(allPersons.isEmpty());
		Assertions.assertTrue(allPersons.contains(personExpected));
	}
	
	@Test
	void shouldReturnAPersonUsingIsFirstNameAndLastname() throws IOException {
		//GIVEN
		PersonByFirstNameAndLastNameDto personExpected = new PersonByFirstNameAndLastNameDto(new Person("John", "Boyd"));
		
		//WHEN
		List<PersonByFirstNameAndLastNameDto> persons = personManager.getPersonsByFirstNameAndLastName(personExpected.getFirstName(), personExpected.getLastName());
		
		//THEN
		Assertions.assertTrue(persons.contains(personExpected));
	}
	
	@Test
	void shouldReturnAddressesForStationNumber1And3() {
		
		List<Integer> stations = new ArrayList<>();
		stations.add(1);
		stations.add(3);
		
		Map<String, List<FamiliesByStationDto>> persons = personManager.getPersonsByAddressStationForFloodAlert(stations);
		
		Assertions.assertTrue(persons.containsKey("644 Gershwin Cir"));
		Assertions.assertTrue(persons.containsKey("748 Townings Dr"));
		Assertions.assertFalse(persons.containsKey("489 Manchester St"));
	}
	
	@Test
	void get2ChildrenByAddress1509CulverSt() {
		String address = "1509 Culver St";
		
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);
		
		Assertions.assertEquals(2, childListAndFamilyListDto.getGetChildrenByAddressDto().size());
	}
	
	@Test
	void get3AdultsByAddress1509CulverSt() {
		String address = "1509 Culver St";
		
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);
		
		Assertions.assertEquals(3, childListAndFamilyListDto.getGetAdultsByAddressDto().size());
	}
	
	@Test
	void get5MembersByAddress1509CulverSt() {
		String address = "1509 Culver St";
		
		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress(address);
		
		Assertions.assertEquals(5, childListAndFamilyListDto.getGetAdultsByAddressDto().size() + childListAndFamilyListDto.getGetChildrenByAddressDto().size());
	}
	
}