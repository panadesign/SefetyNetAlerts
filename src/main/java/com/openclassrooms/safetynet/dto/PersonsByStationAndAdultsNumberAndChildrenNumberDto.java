package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

/**
 * The type Persons by station and adults number and children number dto.
 */
public class PersonsByStationAndAdultsNumberAndChildrenNumberDto {
	private List<PersonsByStationDto> personsByStation;
	private int numberOfAdult;
	private int numberOfChildren;

	/**
	 * Instantiates a new Persons by station and adults number and children number dto.
	 *
	 * @param personsByStation the persons by station
	 * @param numberOfAdult    the number of adult
	 * @param numberOfChildren the number of children
	 */
	public PersonsByStationAndAdultsNumberAndChildrenNumberDto(List<PersonsByStationDto>personsByStation, int numberOfAdult, int numberOfChildren) {
		this.personsByStation = personsByStation;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChildren = numberOfChildren;
	}

	/**
	 * Gets persons by station.
	 *
	 * @return the persons by station
	 */
	@JsonGetter
	public List<PersonsByStationDto> getPersonsByStation() {
		return personsByStation;
	}

	/**
	 * Sets persons by station.
	 *
	 * @param personsByStation the persons by station
	 */
	@JsonSetter
	public void setPersonsByStation(List<PersonsByStationDto> personsByStation) {
		this.personsByStation = personsByStation;
	}

	/**
	 * Gets number of adult.
	 *
	 * @return the number of adult
	 */
	@JsonGetter
	public int getNumberOfAdult() {
		return numberOfAdult;
	}

	/**
	 * Sets number of adult.
	 *
	 * @param numberOfAdult the number of adult
	 */
	@JsonSetter
	public void setNumberOfAdult(int numberOfAdult) {
		this.numberOfAdult = numberOfAdult;
	}

	/**
	 * Gets number of children.
	 *
	 * @return the number of children
	 */
	@JsonGetter
	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * Sets number of children.
	 *
	 * @param numberOfChildren the number of children
	 */
	@JsonSetter
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
}
