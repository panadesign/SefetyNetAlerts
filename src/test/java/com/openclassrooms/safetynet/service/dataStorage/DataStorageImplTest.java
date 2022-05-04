package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@RunWith(SpringRunner.class)
class DataStorageImplTest {
	@Mock
	private Data mockData;

	@InjectMocks
	private DataStorageImpl dataStorage;



	DataStorageImplTest() throws IOException {
	}

	/*@Test
	void getPerson() {
		List<Person> persons = new ArrayList<>();
		Person person = new Person("John", "Boyd");
		persons.add(person);

		when(mockData.getPersons()).thenReturn(persons);
		List<Person> result = dataStorage.getPersons().collect(Collectors.toList());

		assertEquals(1, result.size());
	}*/

	@Test
	void getPersonById() {

	}

	@Test
	void getPersonsByAddress() {


	}

	@Test
	void getPersonsByStation() {
	}

	@Test
	void getFireStations() {
	}

	@Test
	void getFirestationsByNumber() {
	}

	@Test
	void getFirestationsByAddress() {
	}

	@Test
	void getMedicalRecord() {
	}

	@Test
	void getMedicalRecordById() {
	}
}