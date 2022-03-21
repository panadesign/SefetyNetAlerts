package com.openclassrooms.safetynet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityEmailController {

	@GetMapping("/communityEmail?city=<city>")
	public String getEmailAdress() {
		return "test@rer";
	}

}
