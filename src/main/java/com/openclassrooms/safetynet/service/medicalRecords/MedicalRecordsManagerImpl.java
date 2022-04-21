package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;
import java.util.Optional;

@Component
public class MedicalRecordsManagerImpl implements MedicalRecordsManager {
	
	private final DataStorage dataStorage;
	
	@Autowired
	public MedicalRecordsManagerImpl(DataStorage dataStorage) {this.dataStorage = dataStorage;}
	
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		
		Logger.debug("Add a medical record" + medicalRecord);
		
		Optional<MedicalRecord> optionalMedicalRecord = dataStorage.getMedicalRecord()
				.filter(m -> medicalRecord.getLastName().equals(m.getLastName()) && medicalRecord.getFirstName().equals(m.getFirstName()))
				.findFirst();
		
		if (optionalMedicalRecord.isPresent()) {
			throw new RuntimeException("This medical record exist already");
		}
		
		dataStorage
				.getData()
				.getMedicalrecords()
				.add(medicalRecord);
	}

	public void updateMedicalRecords() {
	}

	public void deleteMedicalRecords() {
	}

}
