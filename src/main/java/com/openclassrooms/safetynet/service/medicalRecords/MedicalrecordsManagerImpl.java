package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.dataStorage.DataStorageImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Optional;

@Component
public class MedicalrecordsManagerImpl implements MedicalrecordsManager {

	private DataStorageImpl dataStorage;

	Logger logger = LoggerFactory.getLogger(MedicalrecordsManagerImpl.class);

	@Autowired
	public MedicalrecordsManagerImpl(DataStorageImpl dataStorage) {
		this.dataStorage = dataStorage;
	}

	public void addMedicalRecord(Medicalrecord medicalrecord) {

		logger.debug("Add a medical record" + medicalrecord);

		Optional<Medicalrecord> optionalMedicalrecord =
				dataStorage
						.getMedicalRecordById(medicalrecord.getId());

		if(optionalMedicalrecord.isPresent()) {
			throw new RuntimeException("This medical record exist already");
		}

		dataStorage
				.getData()
				.getMedicalrecords()
				.add(medicalrecord);
	}

	public void updateMedicalRecord(Medicalrecord medicalRecord) {
		logger.debug("Update a medical record" + medicalRecord);

		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecordById(medicalRecord.getId());

		if(optionalMedicalRecord.isPresent()) {
			int indexOfMedicalRecord = dataStorage.getData().getMedicalrecords().indexOf(optionalMedicalRecord.get());

			dataStorage
					.getData()
					.getMedicalrecords()
					.set(indexOfMedicalRecord, medicalRecord);

		} else {
			throw new RuntimeException("This medical record doesn't exist");
		}
	}

	public void deleteMedicalRecord(Medicalrecord medicalRecord) {
		logger.debug("Delete a medical record" + medicalRecord);

		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecordById(medicalRecord.getId());

		if(optionalMedicalRecord.isPresent()) {
			int indexOfMedicalRecord = dataStorage.getData().getMedicalrecords().indexOf(optionalMedicalRecord.get());

			dataStorage
					.getData()
					.getMedicalrecords()
					.remove(indexOfMedicalRecord);

		} else {
			throw new RuntimeException("This medical record doesn't exist");
		}
	}

	public Optional<Medicalrecord> getMedicalRecordByPersonId(Id id) {
		return dataStorage
				.getData()
				.getMedicalrecords()
				.stream()
				.filter(m -> m.getId().equals(id))
				.findFirst();
	}

}
