package com.openclassrooms.safetynet.service.dataStorage;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.ws.soap.Addressing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DataStorageImplTest {
	@Mock
	private Data mockData;

	DataStorage dataStorage = new DataStorageImpl();

	DataStorageImplTest() throws IOException {
	}

	/*@Test
	void getPersons() {
		List<Person> person = new ArrayList<>();
		person.add(new Person("John", "Boyd"));

		when(mockData.getPersons()).thenReturn(person);
		Stream<Person> personList = dataStorage.getPersons();

		assertEquals(person.stream(), personList);
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