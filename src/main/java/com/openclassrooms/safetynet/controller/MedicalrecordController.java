package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.MedicalrecordDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalrecordsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalrecordController {
	
	@Autowired
	private MedicalrecordsManager medicalRecordsManager;
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		medicalRecordsManager.addMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/medicalRecord")
	public ResponseEntity<Void> updateMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		medicalRecordsManager.updateMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		medicalRecordsManager.deleteMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
