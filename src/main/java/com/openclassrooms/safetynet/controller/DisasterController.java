package com.openclassrooms.safetynet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisasterController {

	@GetMapping("/fire/address/<address>")
	public void fire(@PathVariable String address) {

	}

	@GetMapping("/flood/stations/<>")
	public void flood() {

	}
}
