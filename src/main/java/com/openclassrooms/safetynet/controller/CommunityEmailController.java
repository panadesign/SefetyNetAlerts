package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommunityEmailController {
	Data data;

	@GetMapping("/communityEmail")
	
	public List<String> findEmailByCity(@RequestParam String city) {

		List<String> emails = data.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.map(p -> p.getEmail())
				.collect(Collectors.toList());

		return null;
	}
}
