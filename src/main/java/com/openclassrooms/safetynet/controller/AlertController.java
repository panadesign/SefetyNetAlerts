package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

	@Autowired
	DataStorage dataStorage;


}
