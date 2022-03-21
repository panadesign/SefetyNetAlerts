package com.openclassrooms.safetynet.service.person;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.ParseJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class PersonManagement implements IPerson{
	@Autowired
	ParseJSON parseJSON;

	public void addPerson() {
	}

	public void updatePerson() {
	}

	public void deletePerson() {
	}

}
