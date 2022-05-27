package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.ChildListAndFamilyListDto;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.exception.BadRequestExceptions;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonManagerTest {

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
		Person personToAdd = new Person("firstNameTest", "lastNameTest", "address", "city", 3, "1234", "mail");

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
		Assertions.assertThrows(BadRequestExceptions.class, () -> personManager.addPerson(personToAdd));
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
		Assertions.assertTrue(datas.getPersons().isEmpty());

		Person personToUpdate = new Person("firstName1", "lastName1", "address", "city", 3, "1234", "mail");

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons());
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.empty());

		//THEN
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertThrows(BadRequestExceptions.class, () -> personManager.updatePerson(personToUpdate));

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
		Assertions.assertThrows(BadRequestExceptions.class, () -> personManager.deletePerson(personToDelete));
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

		data.getPersons().add(new Person("John", "Boyd", "testMail"));
		data.getPersons().add(new Person("Jacob", "Boyd", "testMail"));
		data.getPersons().add(new Person("test", "test", "mail"));

		data.getMedicalrecords().add(new Medicalrecord("John", "Boyd", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("Jacob", "Boyd", "02/02/1983"));
		data.getMedicalrecords().add(new Medicalrecord("test", "test", "02/02/2000"));

		//WHEN
		when(mockDataStorage.getData()).thenReturn(data);
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		when(mockDataStorage.getMedicalrecords()).thenReturn(data.getMedicalrecords());

		List<PersonByFirstNameAndLastNameDto> personByFirstNameAndLastNameDto = personManager.getPersonsByFirstNameAndLastName("John", "Boyd");

		//THEN
		Assertions.assertEquals(2, personByFirstNameAndLastNameDto.size());
		Assertions.assertEquals("John", personByFirstNameAndLastNameDto.get(0).getFirstName());
		Assertions.assertEquals("Jacob", personByFirstNameAndLastNameDto.get(1).getFirstName());
	}


	@Test
	void getPersonsByAddressStationForFloodAlert() {
		Data data = new Data();

		data.getPersons().add(new Person("firstName1", "lastName1", "address1", "1234"));
		data.getPersons().add(new Person("firstName2", "lastName2", "address2", "1234"));
		data.getPersons().add(new Person("firstName3", "lastName3", "address3", "1234"));
		data.getPersons().add(new Person("firstName4", "lastName4", "address4", "1234"));
		data.getPersons().add(new Person("firstName5", "lastName5", "address5", "1234"));
		data.getPersons().add(new Person("firstName6", "lastName6", "address6", "1234"));

		data.getMedicalrecords().add(new Medicalrecord("firstName1", "lastName1", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName2", "lastName2", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName3", "lastName3", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName4", "lastName4", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName5", "lastName5", "02/02/2000"));
		data.getMedicalrecords().add(new Medicalrecord("firstName6", "lastName6", "02/02/2000"));

		data.getFirestations().add(new Firestation(1, "address1"));
		data.getFirestations().add(new Firestation(1, "address2"));
		data.getFirestations().add(new Firestation(1, "address3"));
		data.getFirestations().add(new Firestation(2, "address4"));
		data.getFirestations().add(new Firestation(3, "address5"));
		data.getFirestations().add(new Firestation(3, "address6"));

		List<Integer> stations = new ArrayList<>();
		stations.add(1);
		stations.add(3);

		//WHEN
		when(mockDataStorage.getData()).thenReturn(data);
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		when(mockDataStorage.getMedicalrecords()).thenReturn(data.getMedicalrecords());
		when(mockDataStorage.getFirestations()).thenReturn(data.getFirestations());

		Assertions.assertEquals(5, personManager.getPersonsByAddressStationForFloodAlert(stations).size());
	}


	@Test
	void getChildrenByAddress() {
		//GIVEN
		Data data = new Data();

		data.getPersons().add(new Person("adult1", "adult1", "address1", "123"));
		data.getPersons().add(new Person("children1", "children1", "address1", "123"));
		data.getPersons().add(new Person("children4", "children4", "address1", "123"));
		data.getPersons().add(new Person("children5", "children5", "address1", "123"));
		data.getPersons().add(new Person("children6", "children6", "address1", "123"));

		data.getPersons().add(new Person("adult2", "adult2", "address2", "123"));
		data.getPersons().add(new Person("children2", "children2", "address2", "123"));
		data.getPersons().add(new Person("children3", "childre3", "address2", "123"));

		data.getMedicalrecords().add(new Medicalrecord("adult1", "adult1", "02/02/1983"));
		data.getMedicalrecords().add(new Medicalrecord("children1", "children1", "02/02/2013"));
		data.getMedicalrecords().add(new Medicalrecord("adult2", "adult2", "02/02/1983"));
		data.getMedicalrecords().add(new Medicalrecord("children2", "children2", "02/02/2013"));
		data.getMedicalrecords().add(new Medicalrecord("children3", "children3", "02/02/2013"));
		data.getMedicalrecords().add(new Medicalrecord("children4", "children4", "02/02/2013"));
		data.getMedicalrecords().add(new Medicalrecord("children5", "children5", "02/02/2013"));
		data.getMedicalrecords().add(new Medicalrecord("children6", "children6", "02/02/2013"));

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(data.getPersons());
		when(mockDataStorage.getMedicalrecords()).thenReturn(data.getMedicalrecords());

		ChildListAndFamilyListDto childListAndFamilyListDto = personManager.getChildrenByAddress("address1");

		//THEN
		Assertions.assertEquals(4, childListAndFamilyListDto.getGetChildrenByAddressDto().size());
		Assertions.assertEquals(1, childListAndFamilyListDto.getGetAdultsByAddressDto().size());

	}
}