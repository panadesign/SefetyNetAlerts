package com.openclassrooms.safetynet.dto;

import java.util.List;

public class GetPersonsByStationAndAdultsNumberAndChildrenNumberDto {
	private List<GetPersonsByStationDto> personsByStation;
	private int numberOfAdult;
	private int numberOfChildren;

	public GetPersonsByStationAndAdultsNumberAndChildrenNumberDto(List<GetPersonsByStationDto>personsByStation, int numberOfAdult, int numberOfChildren) {
		this.personsByStation = personsByStation;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChildren = numberOfChildren;
	}

	public List<GetPersonsByStationDto> getPersonsByStation() {
		return personsByStation;
	}

	public void setPersonsByStation(List<GetPersonsByStationDto> personsByStation) {
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
