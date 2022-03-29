package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AlertController {

	@Autowired
	DataStorage dataStorage;
	@Autowired
	IFirestation iFirestation;

	@GetMapping("/phoneAlert")
	public Set<Person> getPhoneNumberByFirestationNumber (@RequestParam int station) {
		return iFirestation.getPhoneNumberByFirestationNumber(station);

	}
}
