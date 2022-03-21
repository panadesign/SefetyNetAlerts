package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CommunityEmailController {

	@GetMapping("/communityEmail")
	public String getCommunityEmail() throws IOException, ParseException {
		return getAllEmailAddressByCity();
	}

	@Autowired
	ParseJSON parseJSON;

	public String getAllEmailAddressByCity() throws IOException, ParseException {

		JSONObject persons = (JSONObject) parseJSON.readPersons();
		JSONArray arrayPersons = (JSONArray) persons.get("persons");

		String allMails = null;
		for (int i = 0; i < arrayPersons.size(); i++) {
			JSONObject personsArray = (JSONObject) arrayPersons.get(i);
			allMails = (String) personsArray.get("email");
		}

		return allMails;

	}
}
