package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Optional;

public interface MedicalrecordsManager {
	void addMedicalRecord(Medicalrecord medicalRecord);
	void updateMedicalRecord(Medicalrecord medicalRecord);
	void deleteMedicalRecord(Medicalrecord medicalRecord);
	Optional<Medicalrecord> getMedicalRecordByPersonId(Id id);
}
