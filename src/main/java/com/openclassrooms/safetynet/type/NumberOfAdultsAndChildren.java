package com.openclassrooms.safetynet.type;

public class NumberOfAdultsAndChildren {

	private int numberChildren;
	private int numberAdults;

	public NumberOfAdultsAndChildren(int numberChildren, int numberAdults) {
		this.numberChildren = numberChildren;
		this.numberAdults = numberAdults;
	}

	public int getNumberChildren() {
		return numberChildren;
	}

	public int getNumberAdults() {
		return numberAdults;
	}
}
