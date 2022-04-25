package com.openclassrooms.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;
import java.util.Set;

public class GetChildListAndFamilyListDto {
	
	private List<GetChildrenByAddressDto> childByAddress;
	private List<GetAdultsByAddressDto> adultsByAddress;
	
	public GetChildListAndFamilyListDto(List<GetChildrenByAddressDto> getChildrenByAddressDto, List<GetAdultsByAddressDto> getAdultsByAddressDto) {
		this.childByAddress = getChildrenByAddressDto;
		this.adultsByAddress = getAdultsByAddressDto;
	}
	
	@JsonGetter("child")
	public List<GetChildrenByAddressDto> getGetChildrenByAddressDto() {
		return childByAddress;
	}
	
	@JsonGetter("adults")
	public List<GetAdultsByAddressDto> getGetAdultsByAddressDto() {
		return adultsByAddress;
	}
	
	public void setGetChildrenByAddressDto(List<GetChildrenByAddressDto> getChildrenByAddressDto) {
		this.childByAddress = getChildrenByAddressDto;
	}
	
	public void setGetAdultsByAddressDto(List<GetAdultsByAddressDto> getAdultsByAddressDto) {
		this.adultsByAddress = getAdultsByAddressDto;
	}
}
