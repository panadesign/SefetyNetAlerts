package com.openclassrooms.safetynet.service.firestation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openclassrooms.safetynet.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FirestationManagement implements IFirestation {

	@Autowired
	DataStorage dataStorage;

	public void addFirestation() {
	}

	public void updateFirestation() {
	}

	public void deleteFirestation() {
	}
	public List<Person> getPeopleByFirestationNumber(int station) {

		List<String> firestationAdress = dataStorage.getData().getFirestations()
				.stream()
				.filter(p -> p.getStation() == (station))
				.map(p -> p.getAddress())
				.collect(Collectors.toList());

		return dataStorage.getData().getPersons()
				.stream()
				.filter(p -> firestationAdress.contains(p.getAddress()))
				.map(p -> new Person(p.getLastName(), p.getFirstName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());
	}

}
