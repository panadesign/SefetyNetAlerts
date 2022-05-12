package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PersonControllerUnitTest {

	@Autowired
	private DataStorageImpl dataStorage;
	@Autowired
	private PersonManager personManager;

	@Autowired
	private MockMvc mockMvc;

	/*@Test
	void addPerson() throws Exception {
		Person person = new Person("firstName", "lastName");

		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.param(person.getFirstName(), person.getLastName()))
				.andExpect(status().isOk());
	}*/

	@Test
	void updatePerson() {
	}

	@Test
	void deletePerson() {
	}

	/*@Test
	void getPersonsByFirstNameAndLastName() throws Exception {
		Person person = new Person("firstName", "lastName");
		mockMvc.perform(MockMvcRequestBuilders
				.post("/personInfo")
				.param(person.getFirstName(), person.getLastName()))
				.andExpect(status().isOk());
	}*/
}