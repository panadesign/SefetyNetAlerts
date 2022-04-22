package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class GetChildrenByAddressDto {
	private String firstName;
	private String lastName;
	private String phone;
	private Integer age;
	

	
	private List<Person> adults;

	public GetChildrenByAddressDto(Person person, Medicalrecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.phone = person.getPhone();
		this.age=medicalRecord.getAge();
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonGetter("phone")
	public String getPhone() {
		return phone;
	}
	
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
	
	@JsonGetter("isMinor")
	public boolean isMinor() {
		return age <= 18;
	}
	
	public List<Person> getAdults() {
		return adults;
	}
}
