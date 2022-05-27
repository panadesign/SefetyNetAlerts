package com.openclassrooms.safetynet.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


/**
 * The type Firestation.
 */
public class Firestation {

	@Getter
	@Setter
	@NonNull
	private int station;
	
	@Getter
	@Setter
	@NonNull
	private String address;

	/**
	 * Instantiates a new Firestation.
	 */
	public Firestation() {
	}

	/**
	 * Instantiates a new Firestation.
	 *
	 * @param station the station
	 * @param address the address
	 */
	public Firestation(int station, String address) {

		this.station = station;
		this.address = address;
	}

	@Override
	public boolean equals(Object obj){

		if(obj == null) return false;
		if (this.getClass() != obj.getClass())
			return false;

		Firestation firestation = (Firestation) obj;
		return station == (firestation.getStation()) && address.equals(firestation.getAddress());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
