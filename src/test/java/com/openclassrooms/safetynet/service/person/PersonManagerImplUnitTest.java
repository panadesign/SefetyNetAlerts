package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.dto.GetPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Data;
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
class PersonManagerImplUnitTest {
	
	@Mock
	DataStorage mockDataStorage;
	
	PersonManager personManager;
	
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
		when(mockDataStorage.getPersons()).thenReturn(new ArrayList<>());
		personManager.addPerson(personToAdd);
		
		//THEN
		Assertions.assertFalse(data.getPersons().isEmpty());
		Assertions.assertNotNull(data.getPersons());
		Assertions.assertEquals(1, data.getPersons().size());
		
		Person personAdded = data.getPersons().get(0);
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
		List<Person> existingPersons = new ArrayList<>();
		
		existingPersons.add(new Person("firstNameTest", "lastNameTest"));
		Person existingPerson = new Person("firstNameTest", "lastNameTest");
		
		datas.getPersons().add(existingPerson);
		
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
	void getAllMailsByCity() {
		
		String city = "Culver";
		List<Person> persons= new ArrayList<>();
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
		
		List<Person> person = new ArrayList<>();
		person.add(new Person(new Person("John", "Boyd")));
		person.add(new Person(new Person("Jacob", "Boyd")));
		person.add(new Person(new Person("test", "test")));
		
		List<Medicalrecord> medicalrecord = new ArrayList<>();
		medicalrecord.add(new Medicalrecord("John", "Boyd"));
		medicalrecord.add(new Medicalrecord("Jacob", "Boyd"));
		medicalrecord.add(new Medicalrecord("test", "test"));
		
		//WHEN
		when(mockDataStorage.getPersons()).thenReturn(person);
		
		when(mockDataStorage.getMedicalRecord()).thenReturn(medicalrecord);
		
		List<GetPersonByFirstNameAndLastNameDto> personByFirstNameAndLastNameDto = personManager.getPersonsByFirstNameAndLastName("John", "Boyd");
		
		//THEN
		Assertions.assertEquals(2, personByFirstNameAndLastNameDto.size());
	}
}