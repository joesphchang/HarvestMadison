package com.joeychang.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpoonacularRecipe{

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("image")
	private String image;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("extendedIngredients")
	private List<ExtendedIngredientsItem> extendedIngredients;

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setExtendedIngredients(List<ExtendedIngredientsItem> extendedIngredients){
		this.extendedIngredients = extendedIngredients;
	}

	public List<ExtendedIngredientsItem> getExtendedIngredients(){
		return extendedIngredients;
	}

	@Override
 	public String toString(){
		return 
			"SpoonacularRecipe{" + 
			"summary = '" + summary + '\'' + 
			",image = '" + image + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",extendedIngredients = '" + extendedIngredients + '\'' + 
			"}";
		}
}