package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonsByStationAndAdultsNumberAndChildrenNumberDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Firestation controller.
 */
@RestController
public class FirestationController {
	
	private final FirestationManager firestationManager;

	/**
	 * Instantiates a new Firestation controller.
	 *
	 * @param firestationManager the firestation manager
	 */
	FirestationController(FirestationManager firestationManager) {
		this.firestationManager = firestationManager;
	}

	/**
	 * Add firestation response entity.
	 *
	 * @param firestation the firestation
	 * @return the response entity
	 */
	@PostMapping("/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		firestationManager.addFirestation(firestation);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update firestation response entity.
	 *
	 * @param firestation the firestation
	 * @return the response entity
	 */
	@PutMapping("/firestation")
	public ResponseEntity<Void> updateFirestation(@RequestBody Firestation firestation) {
		firestationManager.updateFirestation(firestation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete firestation response entity.
	 *
	 * @param firestation the firestation
	 * @return the response entity
	 */
	@DeleteMapping("/firestation")
	public ResponseEntity<Void> deleteFirestation(@RequestBody Firestation firestation) {
		firestationManager.deleteFirestation(firestation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Gets persons by station and adults number and children number dto.
	 *
	 * @param stationNumber the station number
	 * @return the persons by station and adults number and children number dto
	 */
	@GetMapping("/firestation")
	ResponseEntity<PersonsByStationAndAdultsNumberAndChildrenNumberDto> getPersonsByStationAndAdultsNumberAndChildrenNumberDto(@RequestParam int stationNumber) {

		List<PersonsByStationDto> personsByStation = firestationManager.getPersonsByStation(stationNumber);
		NumberOfAdultsAndChildrenDto numberOfAdultsAndChildrenDto = firestationManager.getNumbersOfChildrenAndAdultsByStation(stationNumber);

		PersonsByStationAndAdultsNumberAndChildrenNumberDto result = new PersonsByStationAndAdultsNumberAndChildrenNumberDto(personsByStation, numberOfAdultsAndChildrenDto.getNumberAdults(), numberOfAdultsAndChildrenDto.getNumberChildren());

		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
}
