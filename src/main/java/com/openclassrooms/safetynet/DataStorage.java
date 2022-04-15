package com.openclassrooms.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Data;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataStorage {
	private Data data;

	public DataStorage() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new ClassPathResource("data.json").getFile();
		this.data = objectMapper.readValue(file, Data.class);
	}

	public Data getData() {
		return data;
	}

	//Récupérer toutes les personnes par adresse
	public List<Person> getPersonsByAddress(String address) {
		List<Person> allPersons = getData().getPersons();
		List<Person> getPersonsByAddress = new ArrayList<>();

		allPersons
				.stream()
				.filter(person -> person.getAddress().equals(address))
				.map(person -> new Person())
				.collect(Collectors.toList());

		getPersonsByAddress.addAll(allPersons);
		return getPersonsByAddress;
	}
}