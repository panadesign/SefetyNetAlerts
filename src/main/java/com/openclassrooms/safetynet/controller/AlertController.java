package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.AlertChildDto;
import com.openclassrooms.safetynet.dto.FireDto;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AlertController {

	@Autowired
	private DataStorage dataStorage;
	@Autowired
	private FirestationManager firestationManager;
	@Autowired
	private PersonManager personManager;

	@GetMapping("/fire")
	public List<FireDto> getPeoplesByAddress(@RequestParam String address) {
		return firestationManager.getPeoplesByAddress(address);
	}

	@GetMapping("/phoneAlert")
	public Set<String> getPhoneNumbersByFirestationNumber(@RequestParam int firestation) {
		return firestationManager.getPhoneNumbersByFirestationNumber(firestation);
	}

	@GetMapping("/childAlert")
	public List<AlertChildDto> getChildByAddress(@RequestParam String address) {
		return personManager.getChildByAddress(address);
	}
	
	@GetMapping("/flood/stations/<>")
	public void flood() {
	
	}

}
