package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.dto.GetPersonsByStationAndAdultsNumberAndChildrenNumberDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import com.openclassrooms.safetynet.dto.GetPersonsByStationDto;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import com.openclassrooms.safetynet.type.NumberOfAdultsAndChildren;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {
	
	@Autowired
	private DataStorageImpl dataStorage;
	@Autowired
	private FirestationManager firestationManager;
	@Autowired
	private PersonManager personManager;
	
	@PostMapping("/firestation")
	
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		firestationManager.addFirestation(firestation);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<Void> updateFirestation(@RequestBody Firestation firestation) {
		firestationManager.updateFirestation(firestation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/firestation")
	public ResponseEntity<Void> deleteFirestation(@RequestBody Firestation firestation) {
		firestationManager.deleteFirestation(firestation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/firestation")
	ResponseEntity<GetPersonsByStationAndAdultsNumberAndChildrenNumberDto> getPersonsByStationAndAdultsNumberAndChildrenNumberDto(@RequestParam int stationNumber) {

		List<GetPersonsByStationDto> personsByStation = firestationManager.getPersonsByStation(stationNumber);
		NumberOfAdultsAndChildren numberOfAdultsAndChildren = firestationManager.getNumbersOfChildrenAndAdultsByStation(stationNumber);

		GetPersonsByStationAndAdultsNumberAndChildrenNumberDto result = new GetPersonsByStationAndAdultsNumberAndChildrenNumberDto(personsByStation, numberOfAdultsAndChildren.getNumberAdults(), numberOfAdultsAndChildren.getNumberChildren());

		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
}
