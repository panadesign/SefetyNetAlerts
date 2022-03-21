package com.openclassrooms.safetynet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

@Component
public class ParseJSON {
	public JSONArray readPersons() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\elbar\\git\\SafetyNetAlerts\\src\\main\\java\\com\\openclassrooms\\safetynet\\resources\\data.json");

		Object obj = parser.parse(reader);
		JSONObject persons = (JSONObject) obj;
		JSONArray arrayPersons = (JSONArray) persons.get("persons");
		System.out.println(arrayPersons);
		/*for (int i = 0; i < arrayPersons.size(); i++) {
			JSONObject personsArray = (JSONObject) arrayPersons.get(i);
			String allMails = (String) personsArray.get("email");
			System.out.println(allMails);

		}*/
		return arrayPersons;
	}

	public void readFireStation() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\elbar\\git\\SafetyNetAlerts\\src\\main\\java\\com\\openclassrooms\\safetynet\\resources\\data.json");

		Object obj = parser.parse(reader);
		JSONObject persons = (JSONObject) obj;
		JSONArray arrayFirestation = (JSONArray) persons.get("firestations");

		System.out.println(arrayFirestation);
	}

	public void readMedicalrecords() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\elbar\\git\\SafetyNetAlerts\\src\\main\\java\\com\\openclassrooms\\safetynet\\resources\\data.json");

		Object obj = parser.parse(reader);
		JSONObject persons = (JSONObject) obj;
		JSONArray arrayMedicalrecords = (JSONArray) persons.get("medicalrecords");

		System.out.println(arrayMedicalrecords);
	}
}