package com.openclassrooms.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

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
}