package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
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
	ResponseEntity<List<getPersonsByAddressDto>> getPeoplesByAddress(@RequestParam String address) {
		return new ResponseEntity<>(firestationManager.getPeoplesByAddress(address), HttpStatus.OK);
	}
	
	@GetMapping("/phoneAlert")
	ResponseEntity<Set<String>> getPhoneNumbersByFirestationNumber(@RequestParam int firestation) {
		return new ResponseEntity<>(firestationManager.getPhoneNumbersByFirestationNumber(firestation), HttpStatus.OK);
	}
	
	@GetMapping("/childAlert")
	ResponseEntity<Set<getChildrenByAddressDto>> getChildByAddress(@RequestParam String address) {
		return new ResponseEntity<>(personManager.getChildrenByAddress(address), HttpStatus.OK);
	}
	
	@GetMapping("/flood/stations/")
	ResponseEntity<Map<String, List<getFamiliesByStationDto>>> getPersonsByAddressStationForFloodAlert(@RequestParam List<Integer> stations) {
		return new ResponseEntity<>(personManager.getPersonsByAddressStationForFloodAlert(stations), HttpStatus.OK);
	}
	
}
