package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class MedicalRecordController {
	
	@Autowired
	private MedicalRecordsManager medicalRecordsManager;
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Add a medical record");
		medicalRecordsManager.addMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/medicalRecord")
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Update a medical record");
		medicalRecordsManager.updateMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		log.debug("Delete a medical record");
		medicalRecordsManager.deleteMedicalRecord(firstName, lastName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
