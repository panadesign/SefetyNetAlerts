package com.openclassrooms.safetynet.model;

import lombok.Getter;
import lombok.Setter;

public class FireStation {
	
	@Getter
	@Setter
	private int station;
	
	@Getter
	@Setter
	private String address;

	public FireStation() {
	}

	public FireStation(int station, String address) {

		this.station = station;
		this.address = address;
	}
	
}
