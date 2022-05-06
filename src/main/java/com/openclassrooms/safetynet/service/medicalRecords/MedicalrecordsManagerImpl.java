package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Optional;

@Component
@Log4j2
public class MedicalrecordsManagerImpl implements MedicalrecordsManager {

	@Autowired
	private DataStorage dataStorage;

	@Autowired
	public MedicalrecordsManagerImpl(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public void addMedicalRecord(Medicalrecord medicalrecord) {

		log.info("Add a medical record" + medicalrecord);

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
		log.info("Update a medical record" + medicalRecord);

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
		log.info("Delete a medical record" + medicalRecord);

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
