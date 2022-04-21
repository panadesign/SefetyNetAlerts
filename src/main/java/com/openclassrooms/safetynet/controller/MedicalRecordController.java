package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.medicalRecords.MedicalRecordsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {
	
	@Autowired
	private MedicalRecordsManager medicalRecordsManager;
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		medicalRecordsManager.addMedicalRecord(medicalRecord);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
