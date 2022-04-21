package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.DataStorage;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = AlertController.class)
class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataStorage dataStorage;
	@MockBean
	private PersonManager personManager;
	@MockBean
	private FirestationManager firestationManager;


	/*@Test
	void getPhoneNumbersByFirestationNumberTest() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=<station>", 1))
				.andExpect(status().isOk());
	}*/
}