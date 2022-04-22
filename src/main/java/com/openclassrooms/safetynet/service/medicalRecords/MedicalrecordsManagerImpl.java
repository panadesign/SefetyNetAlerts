package com.openclassrooms.safetynet.service.medicalRecords;

import com.openclassrooms.safetynet.dto.MedicalrecordDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.Optional;

@Component
public class MedicalrecordsManagerImpl implements MedicalrecordsManager {
	
	private DataStorage dataStorage;
	
	@Autowired
	public MedicalrecordsManagerImpl(DataStorage dataStorage) {this.dataStorage = dataStorage;}
	
	public void addMedicalRecord(Medicalrecord medicalrecord) {
		
		Logger.debug("Add a medical record" + medicalrecord);
		
		Optional<Medicalrecord> optionalMedicalrecord =
				dataStorage
						.getData()
						.getMedicalrecords()
						.stream()
						.filter(m -> medicalrecord.getLastName().equals(medicalrecord.getLastName()) && medicalrecord.getFirstName().equals(medicalrecord.getFirstName()))
						.map(m -> new Medicalrecord(medicalrecord))
						.findFirst();
		
		if (optionalMedicalrecord.isPresent()) {
			throw new RuntimeException("This medical record exist already");
		}
		
		dataStorage
				.getData()
				.getMedicalrecords()
				.add(medicalrecord);
	}
	
	public void updateMedicalRecord(Medicalrecord medicalRecord) {
		Logger.debug("Update a medical record" + medicalRecord);
		
		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecord()
						.filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
						.findFirst();
		
		
		
		if (optionalMedicalRecord.isPresent()) {
			int indexOfMedicalRecord = dataStorage.getData().getMedicalrecords().indexOf(optionalMedicalRecord.get());
			
			dataStorage
					.getData()
					.getMedicalrecords()
					.set(indexOfMedicalRecord, new Medicalrecord(medicalRecord));
			
		} else {
			throw new RuntimeException("This medical record doesn't exist");
		}
	}
	
	public void deleteMedicalRecord(Medicalrecord medicalRecord) {
		Logger.debug("Delete a medical record" + medicalRecord);
		
		Optional<Medicalrecord> optionalMedicalRecord =
				dataStorage
						.getMedicalRecord()
						.filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
						.findFirst();
		
		
		
		if (optionalMedicalRecord.isPresent()) {
			int indexOfMedicalRecord = dataStorage.getData().getMedicalrecords().indexOf(optionalMedicalRecord.get());
			
			dataStorage
					.getData()
					.getMedicalrecords()
					.remove(indexOfMedicalRecord);
			
		} else {
			throw new RuntimeException("This medical record doesn't exist");
		}
	}
	
}
