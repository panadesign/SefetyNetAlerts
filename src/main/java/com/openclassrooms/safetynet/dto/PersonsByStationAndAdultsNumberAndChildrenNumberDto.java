package com.openclassrooms.safetynet.dto;

import java.util.List;

public class PersonsByStationAndAdultsNumberAndChildrenNumberDto {
	private List<PersonsByStationDto> personsByStation;
	private int numberOfAdult;
	private int numberOfChildren;

	public PersonsByStationAndAdultsNumberAndChildrenNumberDto(List<PersonsByStationDto>personsByStation, int numberOfAdult, int numberOfChildren) {
		this.personsByStation = personsByStation;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChildren = numberOfChildren;
	}

	public List<PersonsByStationDto> getPersonsByStation() {
		return personsByStation;
	}

	public void setPersonsByStation(List<PersonsByStationDto> personsByStation) {
		this.personsByStation = personsByStation;
	}

	public int getNumberOfAdult() {
		return numberOfAdult;
	}

	public void setNumberOfAdult(int numberOfAdult) {
		this.numberOfAdult = numberOfAdult;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
}
