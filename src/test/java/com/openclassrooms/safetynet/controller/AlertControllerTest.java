package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import com.openclassrooms.safetynet.service.person.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlertController.class)
class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataStorage dataStorage;
	@MockBean
	private IPerson iPerson;
	@MockBean
	private IFirestation iFirestation;


	@Test
	void getPhoneNumbersByFirestationNumberTest() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=<station>", 1))
				.andExpect(status().isOk());
	}
}