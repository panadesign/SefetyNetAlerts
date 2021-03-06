package com.openclassrooms.safetynet.service.firestation;

import com.openclassrooms.safetynet.dto.PersonsByAddressDto;
import com.openclassrooms.safetynet.dto.PersonsByStationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.dto.NumberOfAdultsAndChildrenDto;

import java.util.List;
import java.util.Set;

/**
 * The interface Firestation manager.
 */
public interface FirestationManager {
	/**
	 * Used to add a new fire station
	 *
	 * @param firestation is defined by a station and an address
	 */
	void addFirestation(Firestation firestation);

	/**
	 * Used to update a fire station
	 *
	 * @param firestation is defined by a station and an address
	 */
	void updateFirestation(Firestation firestation);

	/**
	 * Used to delete a fire station
	 *
	 * @param firestation is defined by a station and an address
	 */
	void deleteFirestation(Firestation firestation);

	/**
	 * Used to get all persons served by a station
	 *
	 * @param stationNumber is the number of the fire station
	 * @return a list of persons served by this station Return for each person his firstName, lastName, address and phone. Return the number of child and the number of adult
	 */
	List<PersonsByStationDto> getPersonsByStation(int stationNumber);

	/**
	 * Method used to return phone number
	 *
	 * @param station is the station number
	 * @return a list of phone number without duplicate serve by this station
	 */
	Set<String> getPhoneByFirestationNumber(int station);

	/**
	 * Used to return persons by address
	 *
	 * @param address is the address served by the fire station
	 * @return a list of persons served at this address return firstName, lastName, age, list of medications, list of allergies and fire station number
	 */
	List<PersonsByAddressDto> getPersonsByAddress(String address);

	/**
	 * Return number of adults and children
	 *
	 * @param station is the number of the fire station
	 * @return the number of adults and child served by the fire station by station in parameters
	 */
	NumberOfAdultsAndChildrenDto getNumbersOfChildrenAndAdultsByStation(int station);
}
