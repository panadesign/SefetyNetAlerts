package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Medicalrecord;

public interface MedicalrecordsManager {
	void addMedicalRecord(Medicalrecord medicalRecord);
	void updateMedicalRecord(Medicalrecord medicalRecord);
	void deleteMedicalRecord(Medicalrecord medicalRecord);
}
