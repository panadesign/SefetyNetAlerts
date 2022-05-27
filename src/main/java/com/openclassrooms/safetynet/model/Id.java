package com.openclassrooms.safetynet.model;

/**
 * The type Id.
 */
public class Id {
	
	private final String firstName;
	private final String lastName;

	/**
	 * Instantiates a new Id.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 */
	public Id(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Id id = (Id) o;
		
		if (!firstName.equals(id.firstName)) return false;
		return lastName.equals(id.lastName);
	}
	
	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		return result;
	}
}
