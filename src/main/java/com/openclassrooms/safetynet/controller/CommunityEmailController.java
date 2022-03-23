package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommunityEmailController {

	@Autowired
	ParseJSON parseJSON;

	@GetMapping("/communityEmail")
	public List<String> findEmailByCity(@RequestParam String city) {

		Data data = parseJSON.getData();
		
		List<String> emails = data.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(p -> p.getEmail())
				.collect(Collectors.toList());

		return emails;
	}
}
