package com.openclassrooms.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Data;

import java.io.File;
import java.io.IOException;


public class ParseJSON {
	private Data data;

	public void DataStorage() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		data = objectMapper.readValue(new File("data.json"), Data.class);
	}

	public Data getData() {
		return data;
	}
}