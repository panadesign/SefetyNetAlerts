package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalrecordController.class)
class MedicalrecordControllerTest {
	
	
	@MockBean
	MedicalrecordsManager medicalrecordsManager;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void addMedicalrecord() throws Exception {
		/*Medicalrecord medicalrecord = new Medicalrecord("firstName", "lastName");
		mockMvc.perform(post("/medicalRecord?firstName=firstName&lastName=lastname")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalrecord)))
				.andExpect(status().isCreated());*/
	}
	
	@Test
	void updateMedicalRecord() {
	}
	
	@Test
	void deleteMedicalRecord() throws Exception {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		
		Medicalrecord medicalrecord = new Medicalrecord("firstName", "lastName", "02/02/1922", medications, allergies);
		mockMvc.perform(delete("/medicalRecord?firstName=firstName&lastName=lastName")
						.queryParam("firstName", "firstName")
						.queryParam("lastName", "lastName")
						)
				.andExpect(status().isAccepted());
	}
}