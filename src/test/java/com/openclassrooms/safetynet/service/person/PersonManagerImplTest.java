package com.openclassrooms.safetynet.service.person;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.firestation.FirestationManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
		personManager.addPerson(new Person("prenomTest", "nomTest"));

		assertEquals(1, datas.getPersons().size());

	}
}