package com.openclassrooms.safetynet.model;

public class FireStation {

	private int station;
	private String address;

	public FireStation() {
	}

	public FireStation(int station, String address) {

		this.station = station;
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
