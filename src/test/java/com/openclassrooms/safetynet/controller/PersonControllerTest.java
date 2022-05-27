package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
	
	
	@MockBean
	PersonManager personManagerImpl;
	
	@MockBean
	DataStorage dataStorage;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getPersons() throws Exception {
		mockMvc.perform(get("/person"))
				.andExpect(status().isOk());
	}
	
	@Test
	void addPerson() throws Exception {
		Person personToAdd = new Person("newFirstname", "newLastName", "addressTest", "Paris", 75013, "1234", "mail");

		mockMvc.perform(post("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(personToAdd)))
				.andExpect(status().isCreated());

	}
	
	@Test
	void updatePerson() throws Exception {
		Person personToUpdate = (new Person("firstName", "lastName", "address", "city", 75, "123", "mail"));

		mockMvc.perform(put("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(personToUpdate)))
				.andExpect(status().isOk());
		
	}
	
	@Test
	void deletePerson() throws Exception {
		mockMvc.perform(delete("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com"))))
				.andExpect(status().isOk());
	}
	
	@Test
	void getPersonsByFirstNameAndLastName() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=firstName&lastName=lastName")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								new Person("Jacob", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6513", "drk@email.com"))))
				.andExpect(status().isOk());
		
	}
}