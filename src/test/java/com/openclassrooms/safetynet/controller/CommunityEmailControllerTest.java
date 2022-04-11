package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommunityEmailController.class)
class CommunityEmailControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataStorage dataStorage;
	@MockBean
	private PersonManager iPerson;

	@Test
	void getAllMailsByCityTest() throws Exception {
		mockMvc.perform(get("/communityEmail?city=<>"))
				.andExpect(status().isOk());
	}
}