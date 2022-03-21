package com.openclassrooms.safetynet;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SafetyNetApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(SafetyNetApplication.class, args);
		ParseJSON parseJSON = new ParseJSON();
		parseJSON.readPersons();

	}


}
