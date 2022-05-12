package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonManagerImplUnitTest {

	@Mock
	DataStorage mockDataStorage;

	private PersonManager personManager;

	@BeforeEach
	public void Init() {
		personManager = new PersonManagerImpl(mockDataStorage);
	}

	@Test
	void addPerson() {

		//GIVEN
		Data data = new Data();
		Assertions.assertTrue(data.getPersons().isEmpty());
		Assertions.assertNotNull(data.getPersons());
		Person personToAdd = new Person("firstNameTest", "lastNameTest");

		//WHEN
		when(mockDataStorage.getData()).thenReturn(data);
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		personManager.addPerson(personToAdd);

		//THEN
		Assertions.assertFalse(data.getPersons().isEmpty());
		Assertions.assertTrue(data.getPersons().contains(personToAdd));

	}

	@Test
	void addPersonAlreadyExisting() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());
		Person existingPerson = new Person("firstName", "lastName");

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(new ArrayList<>());
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(existingPerson));

		//THEN
		Person personToAdd = new Person("firstName", "lastName");
		Assertions.assertThrows(RuntimeException.class, () -> personManager.addPerson(personToAdd));
	}

	@Test
	void updatePersonTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());

		Person existingPerson = new Person("firstNameTest", "lastNameTest");
		datas.getPersons().add(existingPerson);

		Person personUpdatedDatas = new Person(existingPerson.getLastName(), existingPerson.getFirstName());
		personUpdatedDatas.setEmail("testEmail");

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(existingPerson));

		personManager.updatePerson(personUpdatedDatas);

		//THEN
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertFalse(datas.getPersons().isEmpty());
		Assertions.assertEquals(1, datas.getPersons().size());

		Person personUpdated = datas.getPersons().get(0);
		Assertions.assertEquals(personUpdatedDatas, personUpdated);
		Assertions.assertEquals("testEmail", personUpdated.getEmail());
	}

	@Test
	void updatePersonNotExistingTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());

		Person existingPerson = new Person("firstName", "lastName", "test");

		Person personToUpdate = new Person("blab", "bloub");
		personToUpdate.setEmail("fds");
		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(personToUpdate));


		//THEN
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertThrows(RuntimeException.class, () -> personManager.updatePerson(personToUpdate));

	}

	@Test
	void deletePersonTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());
		List<Person> existingPersons = new ArrayList<>();

		Person existingPerson = new Person("firstNameTest", "lastNameTest");
		personManager.addPerson(existingPerson);

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(existingPersons);
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(existingPerson));

		//THEN
		personManager.deletePerson(existingPerson);

		Assertions.assertEquals(0, datas.getPersons().size());
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());
	}

	@Test
	void deletePersonNotExistingTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());


		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());


		Person personToDelete = new Person("test1", "test2");

		//THEN
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertThrows(RuntimeException.class, () -> personManager.deletePerson(personToDelete));
	}

	@Test
	void getAllMailsByCity() {

		String city = "Culver";
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com"));
		String mailExpected = "jaboyd@email.com";

		when(mockDataStorage.getPersons()).thenReturn(persons);
		Set<String> allMails = personManager.getAllMailsByCity(city);

		Assertions.assertTrue(allMails.contains(mailExpected));
		Assertions.assertEquals(1, allMails.size());
	}

	@Test
	void getPersonsByFirstNameAndLastName() {
		//GIVEN
		Data data = new Data();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "testMail"));
		persons.add(new Person("Jacob", "Boyd", "testMail"));
		persons.add(new Person("test", "test", "mail"));

		List<Medicalrecord> medicalrecords = new ArrayList<>();
		medicalrecords.add(new Medicalrecord("John", "Boyd", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("Jacob", "Boyd", "02/02/1983"));
		medicalrecords.add(new Medicalrecord("test", "test", "02/02/2000"));

		//WHEN
		when(mockDataStorage.getData()).thenReturn(data);
		when(mockDataStorage.getPersons()).thenReturn(persons);
		when(mockDataStorage.getMedicalrecords()).thenReturn(medicalrecords);

		List<PersonByFirstNameAndLastNameDto> personByFirstNameAndLastNameDto = personManager.getPersonsByFirstNameAndLastName("John", "Boyd");

		//THEN
		Assertions.assertEquals(2, personByFirstNameAndLastNameDto.size());
		Assertions.assertEquals("John", personByFirstNameAndLastNameDto.get(0).getFirstName());
		Assertions.assertEquals("Jacob", personByFirstNameAndLastNameDto.get(1).getFirstName());
	}

	@Test
	void getPersonsByAddressStationForFloodAlert() {
		Data data = new Data();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("firstName1", "lastName1", "address1", "1234"));
		persons.add(new Person("firstName2", "lastName2", "address2", "1234"));
		persons.add(new Person("firstName3", "lastName3", "address3", "1234"));
		persons.add(new Person("firstName4", "lastName4", "address4", "1234"));
		persons.add(new Person("firstName5", "lastName5", "address5", "1234"));
		persons.add(new Person("firstName6", "lastName6", "address6", "1234"));

		List<Medicalrecord> medicalrecords = new ArrayList<>();
		medicalrecords.add(new Medicalrecord("firstName1", "lastName1", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("firstName2", "lastName2", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("firstName3", "lastName3", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("firstName4", "lastName4", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("firstName5", "lastName5", "02/02/2000"));
		medicalrecords.add(new Medicalrecord("firstName6", "lastName6", "02/02/2000"));


		List<Firestation> firestations = new ArrayList<>();
		firestations.add(new Firestation(1, "address1"));
		firestations.add(new Firestation(1, "address2"));
		firestations.add(new Firestation(1, "address3"));
		firestations.add(new Firestation(2, "address4"));
		firestations.add(new Firestation(3, "address5"));
		firestations.add(new Firestation(3, "address6"));

		List<Integer> stations = new ArrayList<>();
		stations.add(1);
		stations.add(3);

		//WHEN
		when(mockDataStorage.getData()).thenReturn(data);
		when(mockDataStorage.getPersons()).thenReturn(persons);
		when(mockDataStorage.getMedicalrecords()).thenReturn(medicalrecords);
		when(mockDataStorage.getFirestations()).thenReturn(firestations);


		Assertions.assertEquals(5, personManager.getPersonsByAddressStationForFloodAlert(stations).size());
	}

	@Test
	void getChildrenByAddress() {
	}
}