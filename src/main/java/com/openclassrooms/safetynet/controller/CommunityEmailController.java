package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CommunityEmailController {
	@Autowired
	ParseJSON parseJSON;

	public String getAllEmailAddressByCity() throws IOException, ParseException {

		JSONObject persons = (JSONObject) parseJSON.readPersons();
		JSONArray arrayPersons = (JSONArray) persons.get("persons");

		String allMails = "";
		for (int i = 0; i < arrayPersons.size(); i++) {
			JSONObject personsArray = (JSONObject) arrayPersons.get(i);
			allMails = (String) personsArray.get("email");
		}

		return allMails;

	}
}
