package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.*;

import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Alert controller.
 */
@RestController
public class AlertController {

	private final FirestationManager firestationManager;
	private final PersonManager personManager;

	/**
	 * Instantiates a new Alert controller.
	 *
	 * @param firestationManager the firestation manager
	 * @param personManager      the person manager
	 */
	AlertController(FirestationManager firestationManager, PersonManager personManager) {
		this.firestationManager = firestationManager;
		this.personManager = personManager;
	}

	/**
	 * Gets persons by address.
	 *
	 * @param address the address
	 * @return the persons by address
	 */
	@GetMapping("/fire")
	ResponseEntity<List<PersonsByAddressDto>> getPersonsByAddress(@RequestParam String address) {
		return new ResponseEntity<>(firestationManager.getPersonsByAddress(address), HttpStatus.OK);
	}

	/**
	 * Gets phone numbers by firestation number.
	 *
	 * @param firestation the firestation
	 * @return the phone numbers by firestation number
	 */
	@GetMapping("/phoneAlert")
	ResponseEntity<Set<String>> getPhoneNumbersByFirestationNumber(@RequestParam int firestation) {
		return new ResponseEntity<>(firestationManager.getPhoneByFirestationNumber(firestation), HttpStatus.OK);
	}

	/**
	 * Gets child by address.
	 *
	 * @param address the address
	 * @return the child by address
	 */
	@GetMapping("/childAlert")
	ResponseEntity<ChildListAndFamilyListDto> getChildByAddress(@RequestParam String address) {
		return new ResponseEntity<>(personManager.getChildrenByAddress(address), HttpStatus.OK);
	}

	/**
	 * Gets persons by address station for flood alert.
	 *
	 * @param stations the stations
	 * @return the persons by address station for flood alert
	 */
	@GetMapping("/flood/stations/")
	ResponseEntity<Map<String, List<FamiliesByStationDto>>> getPersonsByAddressStationForFloodAlert(@RequestParam List<Integer> stations) {
		return new ResponseEntity<>(personManager.getPersonsByAddressStationForFloodAlert(stations), HttpStatus.OK);
	}
	
}
