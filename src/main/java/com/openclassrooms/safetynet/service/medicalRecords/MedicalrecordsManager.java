package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Optional;

public interface MedicalrecordsManager {
	/**
	 * Used to add a new medicalRecord(firstName and lastName must been non-existent)
	 * @param medicalRecord is defined by a firstName and a lastName
	 */
	void addMedicalRecord(Medicalrecord medicalRecord);
	
	/**
	 * Method used to update a medicalRecord(to find the medicalRecord to update, this method use firstName + lastName)
	 * @param medicalRecord is defined by a firstName and a lastName
	 */
	void updateMedicalRecord(Medicalrecord medicalRecord);
	
	/**
	 * Method used to delete a medicalRecord(to find the medicalRecord to update, this method use firstName + lastName)
	 * @param medicalRecord is defined by a firstName and a lastName
	 */
	void deleteMedicalRecord(Medicalrecord medicalRecord);
	
	/**
	 * Method used to get medicalrecord by person using his id
	 * @param id is defined by firstName + lastName
	 * @return a medicalrecord using id
	 */
	Optional<Medicalrecord> getMedicalRecordByPersonId(Id id);
}
