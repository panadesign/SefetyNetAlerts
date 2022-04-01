package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.FireDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import com.openclassrooms.safetynet.service.person.IPerson;
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
	@Autowired
	IPerson iPerson;
	
	@GetMapping("/fire")
	public List<Person> getPeoplesByAddressAndFirestationNumber(@RequestParam String address){
		return iFirestation.getPeoplesByAddressAndFirestationNumber(address);
	}

	@GetMapping("/phoneAlert")
	public Set<String> getPhoneNumberByFirestationNumber (@RequestParam int station) {
		return iFirestation.getPhoneNumberByFirestationNumber(station);

	}
	
}
