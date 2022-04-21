package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.MedicalRecord;

public interface MedicalRecordsManager {
	void addMedicalRecord(MedicalRecord medicalRecord);
	void updateMedicalRecords();
	void deleteMedicalRecords();
}
