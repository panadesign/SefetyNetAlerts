package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.person.PersonManager;
import com.openclassrooms.safetynet.service.person.PersonManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerUnitTest {

	@Mock
	private DataStorageImpl mockDataStorage;


	@MockBean
	PersonManagerImpl personManagerImpl;

	@Autowired
	private MockMvc mockMvc;


	/*@Test
	void getPersonTest() throws Exception {
		mockMvc.perform(get("/person"))
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