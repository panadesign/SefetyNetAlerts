package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerUnitTest {
	

	@MockBean
	PersonManager personManagerImpl;

	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void addPerson() throws Exception {
		Person personToAdd = new Person("newFirstname", "newLastName", "addressTest", "Paris", 75013, "1234", "mail");
		
		doNothing().when(personManagerImpl).addPerson(personToAdd);
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personToAdd)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void updatePerson() throws Exception {
	
	}

	@Test
	void deletePerson() throws Exception {
		mockMvc.perform(delete("/person?John&Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						new Person("John", "Boyd"))))
				.andExpect(status().isOk());
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