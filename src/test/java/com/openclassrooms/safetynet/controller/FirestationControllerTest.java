package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.dto.PersonsByStationAndAdultsNumberAndChildrenNumberDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {
	
	@MockBean
	FirestationManager firestationManager;
	
	@MockBean
	DataStorageImpl dataStorage;
	
	@MockBean
	PersonManager personManager;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void addFirestation() throws Exception {
		Firestation firestationToAdd = new Firestation(1, "test");
		
		doNothing().when(firestationManager).addFirestation(firestationToAdd);
		
		mockMvc.perform(post("/firestation")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(firestationToAdd)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	void updateFirestation() throws Exception {
		Firestation firestationToUpdate = new Firestation(1, "test");
		Firestation firestationUpdated = new Firestation(1, "testUpdated");
		
		mockMvc.perform(put("/firestation?station=1&address=test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationUpdated)))
				.andExpect(status().isOk());
	}
	
	@Test
	void deleteFirestation() throws Exception {
		Firestation firestationToDelete = new Firestation(1, "test");
		
		mockMvc.perform(delete("/firestation?station=1&address=test")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(firestationToDelete)))
				.andExpect(status().isOk());
	}
}