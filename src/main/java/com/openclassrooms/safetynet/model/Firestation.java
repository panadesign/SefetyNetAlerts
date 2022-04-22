package com.openclassrooms.safetynet.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Firestation {
	
	@Getter
	@Setter
	@NonNull
	private int station;
	
	@Getter
	@Setter
	@NonNull
	private String address;

	public Firestation() {
	}

	public Firestation(int station, String address) {

		this.station = station;
		this.address = address;
	}
	
	public Firestation(Firestation firestation) {}
}
