package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Medical record controller.
 */
@RestController
@Log4j2
public class MedicalRecordController {
	
	private final MedicalRecordsManager medicalRecordsManager;

	/**
	 * Instantiates a new Medical record controller.
	 *
	 * @param medicalRecordsManager the medical records manager
	 */
	MedicalRecordController(MedicalRecordsManager medicalRecordsManager) {
		this.medicalRecordsManager = medicalRecordsManager;
	}

	/**
	 * Add medical record response entity.
	 *
	 * @param medicalRecord the medical record
	 * @return the response entity
	 */
	@PostMapping("/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Add a medical record");
		medicalRecordsManager.addMedicalRecord(medicalRecord);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update medical record response entity.
	 *
	 * @param medicalRecord the medical record
	 * @return the response entity
	 */
	@PutMapping("/medicalRecord")
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Update a medical record");
		medicalRecordsManager.updateMedicalRecord(medicalRecord);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete medical record response entity.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @return the response entity
	 */
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		log.debug("Delete a medical record");
		medicalRecordsManager.deleteMedicalRecord(firstName, lastName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
