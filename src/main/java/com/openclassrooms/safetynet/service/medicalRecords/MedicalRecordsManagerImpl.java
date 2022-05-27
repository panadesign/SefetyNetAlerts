package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.exception.BadRequestExceptions;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Medical records manager.
 */
@Component
@Log4j2
public class MedicalRecordsManagerImpl implements MedicalRecordsManager {

	private DataStorage dataStorage;

	/**
	 * Instantiates a new Medical records manager.
	 *
	 * @param dataStorage the data storage
	 */
	@Autowired
	public MedicalRecordsManagerImpl(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	/**
	 * Instantiates a new Medical records manager.
	 */
	public MedicalRecordsManagerImpl() {
	}
	
	public void addMedicalRecord(Medicalrecord medicalrecord) {
		log.info("Add a medical record" + medicalrecord);

		Optional<Medicalrecord> optionalMedicalrecord =
				dataStorage
						.getMedicalRecordById(medicalrecord.getId());

		if(optionalMedicalrecord.isPresent()) {
			log.error("Medical present cannot be created, existing already");
			throw new BadRequestExceptions("This medical record exist already");
		}

		dataStorage
				.getMedicalrecords()
				.add(medicalrecord);
		log.info("Medicalrecord created");
	}

	public void updateMedicalRecord(Medicalrecord medicalRecord) {
		log.info("Update a medical record" + medicalRecord);

		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecordById(medicalRecord.getId());

		if(optionalMedicalRecord.isPresent()) {
			int indexOfMedicalRecord = dataStorage.getMedicalrecords().indexOf(optionalMedicalRecord.get());

			dataStorage
					.getMedicalrecords()
					.set(indexOfMedicalRecord, medicalRecord);
			log.info("Medicalrecord updated");

		} else {
			log.error("Cannot update, medicalrecord doesn't exist");
			throw new BadRequestExceptions("This medical record doesn't exist");
		}
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		log.info("Delete a medical record " + firstName + " " + lastName);

		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecordById(new Id(firstName, lastName));

		if(optionalMedicalRecord.isPresent()) {
			
			dataStorage
					.getMedicalrecords()
					.remove(optionalMedicalRecord.get());
			log.info("Medicalrecord deleted");

		} else {
			log.error("Cannot delete, medicalrecord doesn't exist");
			throw new BadRequestExceptions("This medical record doesn't exist");
		}
	}

	public Optional<Medicalrecord> getMedicalRecordByPersonId(Id id) {
		log.debug("get medicalrecord using person id");
		return dataStorage
				.getMedicalrecords()
				.stream()
				.filter(m -> m.getId().equals(id))
				.findFirst();
	}

}
