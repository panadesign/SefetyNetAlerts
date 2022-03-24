package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.repository.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FirestationManagement implements IFirestation {

	@Autowired
	ParseJSON parseJSON;

	public void addFirestation() {
	}

	public void updateFirestation() {
	}

	public void deleteFirestation() {
	}

	public List<String> getPeopleByFirestationNumber(int station) {
		Data data = parseJSON.getData();

		List<String> firestationAdress = data.getFirestations()
				.stream()
				.filter(p -> p.getStation() == (station))
				.map(p -> p.getAddress())
				.collect(Collectors.toList());

		return data.getPersons()
				.stream()
				.filter(p -> firestationAdress.contains(p.getAddress()))
				.map(p -> p.getFirstName() + p.getLastName() + p.getAddress() + p.getPhone())
				.collect(Collectors.toList());
	}

}
