package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.MedicalrecordDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class MedicalrecordController {
	
	@Autowired
	private MedicalrecordsManager medicalRecordsManager;
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Add a medicalrecord");
		medicalRecordsManager.addMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/medicalRecord")
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Update a medicalrecord");
		medicalRecordsManager.updateMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		log.debug("Delete a medicalrecord");
		medicalRecordsManager.deleteMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
