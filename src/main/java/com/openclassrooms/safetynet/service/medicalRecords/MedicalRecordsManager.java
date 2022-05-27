package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;

import java.util.Optional;

/**
 * The interface Medical records manager.
 */
public interface MedicalRecordsManager {
	/**
	 * Used to add a new medicalRecord(firstName and lastName must be non-existent)
	 *
	 * @param medicalRecord is defined by a firstName and a lastName
	 */
	void addMedicalRecord(Medicalrecord medicalRecord);

	/**
	 * Method used to update a medicalRecord(to find the medicalRecord to update, this method use firstName + lastName)
	 *
	 * @param medicalRecord is defined by a firstName and a lastName
	 */
	void updateMedicalRecord(Medicalrecord medicalRecord);

	/**
	 * Method used to delete a medicalRecord(to find the medicalRecord to update, this method use firstName + lastName)
	 *
	 * @param firstName is the firstName
	 * @param lastName  is the lastName
	 */
	void deleteMedicalRecord(String firstName, String lastName);

	/**
	 * Method used to get medical record by person using his id
	 *
	 * @param id is defined by firstName + lastName
	 * @return a medical record using id
	 */
	Optional<Medicalrecord> getMedicalRecordByPersonId(Id id);
}
