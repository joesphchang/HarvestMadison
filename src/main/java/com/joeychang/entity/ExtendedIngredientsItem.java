package com.joeychang.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtendedIngredientsItem{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("name")
	private String name;

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public Integer getAmount(){
		return amount;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"ExtendedIngredientsItem{" + 
			"amount = '" + amount + '\'' + 
			",unit = '" + unit + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}