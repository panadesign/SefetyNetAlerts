package com.openclassrooms.safetynet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

	@PostMapping("/medicalRecord")
	public String addMedicalRecord() {
		return "ajouter un dossier médical";
	}

	@PutMapping("/medicalRecord")
	public String updateMedicalRecord() {
		return "mise à jour du dossier médical";
	}

	@DeleteMapping("/medicalRecord")
	public String deleteMedicalRecord() {
		return "supprimer le dossier médical";
	}

}
