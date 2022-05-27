package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.Data;

/**
 * The type Person.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;
	private String address;
	private String city;
	private Integer zip;
	private String phone;
	private String email;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	@JsonIgnore
	public Id getId() {
		return  new Id(firstName, lastName);
	}
}
