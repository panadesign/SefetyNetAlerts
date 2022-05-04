package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@RunWith(SpringRunner.class)
class DataStorageImplITest {
	@Mock
	private Data mockData;

	@InjectMocks
	private DataStorageImpl dataStorage;



	DataStorageImplITest() throws IOException {
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