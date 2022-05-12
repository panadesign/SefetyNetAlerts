package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class ChildListAndFamilyListDto {
	
	private List<ChildrenByAddressDto> childByAddress;
	private List<AdultsByAddressDto> adultsByAddress;
	
	public ChildListAndFamilyListDto(List<ChildrenByAddressDto> childrenByAddressDto, List<AdultsByAddressDto> adultsByAddressDto) {
		this.childByAddress = childrenByAddressDto;
		this.adultsByAddress = adultsByAddressDto;
	}
	
	@JsonGetter("child")
	public List<ChildrenByAddressDto> getGetChildrenByAddressDto() {
		return childByAddress;
	}
	
	@JsonGetter("adults")
	public List<AdultsByAddressDto> getGetAdultsByAddressDto() {
		return adultsByAddress;
	}
	
	public void setGetChildrenByAddressDto(List<ChildrenByAddressDto> childrenByAddressDto) {
		this.childByAddress = childrenByAddressDto;
	}
	
	public void setGetAdultsByAddressDto(List<AdultsByAddressDto> adultsByAddressDto) {
		this.adultsByAddress = adultsByAddressDto;
	}
}
