package com.openclassrooms.safetynet;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class ParseJSON {
	public Object readPersons() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\elbar\\git\\SafetyNetAlerts\\src\\main\\java\\com\\openclassrooms\\safetynet\\resources\\data.json");

		Object obj = parser.parse(reader);
		return obj;
	}
}