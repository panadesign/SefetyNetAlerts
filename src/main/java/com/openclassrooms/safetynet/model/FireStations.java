package com.openclassrooms.safetynet.model;

public class FireStations {

	private int id;
	private int station;
	private String address;

	public FireStations() {
	}

	public FireStations(int id, int station, String address) {
		this.id = id;
		this.station = station;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
