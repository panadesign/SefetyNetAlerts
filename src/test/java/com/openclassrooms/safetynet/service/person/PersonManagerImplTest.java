package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.*;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.firestation.FirestationManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonManagerImplTest {

	@Mock
	DataStorage mockDataStorage;

	PersonManager personManager;

	@BeforeEach
	public void Init(){
		personManager = new PersonManagerImpl(mockDataStorage);
	}

	@Test
	void addPerson() {
		Data datas = new Data();

		when(mockDataStorage.getPersons()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);

		datas.setPersons(new ArrayList<>());
		personManager.addPerson(new Person("firstNameTest", "lastNameTest"));

		Assertions.assertEquals(1, datas.getPersons().size());
	}

	@Test
	void addPersonAlreadyExisting() {
		Data datas = new Data();
		when(mockDataStorage.getPersons()).thenReturn(Stream.empty());
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getPersonById(new Id("firstName", "lastName"))).thenReturn(Optional.of(new Person("firstName", "lastName")));

		datas.setMedicalrecords(new ArrayList<>());

		Assertions.assertThrows(RuntimeException.class, () -> personManager.addPerson(new Person("firstName", "lastName")));
	}

	/*@Test
	void updatePersonTest() {
		Data datas = new Data();
		datas.getPersons().add(new Person("firstNameTest", "lastNameTest"));
		datas.setPersons(new ArrayList<>());

		when(mockDataStorage.getPersons()).thenReturn(Stream.of(new Person("firstNameTest", "lastNameTest", "testMail")));

		when(mockDataStorage.getPersons()).thenReturn(datas.getPersons().stream());
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getPersonById(new Id("firstNameTest", "lastNameTest"))).thenReturn(Optional.of(new Person("firstNameTest", "lastNameTest")));

		personManager.updatePerson(new Person("firstNameTest", "lastNameTest", "testMailUpdated"));

		Assertions.assertEquals("testMailUpdated", datas.getPersons().get(0).getEmail());
	}*/

	@Test
	void deletePersonTest() {
		Data datas = new Data();

		when(mockDataStorage.getPersons()).thenReturn(Stream.of(new Person("firstNameTest", "lastNameTest")));
		when(mockDataStorage.getData()).thenReturn(datas);
		when(mockDataStorage.getPersonById(new Id("firstNameTest", "lastNameTest"))).thenReturn(Optional.of(new Person("firstNameTest", "lastNameTest")));

		datas.setPersons(new ArrayList<>());
		personManager.deletePerson(new Person("firstNameTest", "lastNameTest"));

		Assertions.assertEquals(0, datas.getPersons().size());
	}
}