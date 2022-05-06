package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.*;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonManagerImplUnitTest {

	@Mock
	DataStorage mockDataStorage;

	PersonManager personManager;

	@BeforeEach
	public void Init(){
		personManager = new PersonManagerImpl(mockDataStorage);
	}

	@Test
	void addPerson() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertTrue(datas.getPersons().isEmpty());
		Assertions.assertNotNull(datas.getPersons());
		Person personToAdd = new Person("firstNameTest", "lastNameTest");

		//WHEN

		when(mockDataStorage.getPersons()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);
		personManager.addPerson(personToAdd);

		//THEN
		Assertions.assertFalse(datas.getPersons().isEmpty());
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertEquals(1, datas.getPersons().size());
		Person personAdded = datas.getPersons().get(0);
		Assertions.assertEquals(personToAdd, personAdded);
	}

	@Test
	void addPersonAlreadyExisting() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());
		Person existingPerson = new Person("firstName", "lastName");

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);
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
		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons().stream());
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(existingPerson));

		personManager.updatePerson(personUpdatedDatas);
		Assertions.assertEquals("testEmail", datas.getPersons().get(0).getEmail());

		//THEN
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertFalse(datas.getPersons().isEmpty());
		Assertions.assertEquals(1, datas.getPersons().size());

		Person personUpdated = datas.getPersons().get(0);
		Assertions.assertEquals(personUpdatedDatas, personUpdated);
	}

	@Test
	void deletePersonTest() {
		//GIVEN
		Data datas = new Data();
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());

		Person existingPerson = new Person("firstNameTest", "lastNameTest");
		datas.getPersons().add(existingPerson);

		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(Stream.of(existingPerson));
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getPersonById(any())).thenReturn(Optional.of(existingPerson));

		//THEN
		personManager.deletePerson(existingPerson);

		Assertions.assertEquals(0, datas.getPersons().size());
		Assertions.assertNotNull(datas.getPersons());
		Assertions.assertTrue(datas.getPersons().isEmpty());
	}
}