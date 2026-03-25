package com.joeychang.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Spoonacular recipe.
 */
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

	/**
	 * Set summary.
	 *
	 * @param summary the summary
	 */
	public void setSummary(String summary){
		this.summary = summary;
	}

	/**
	 * Get summary string.
	 *
	 * @return the string
	 */
	public String getSummary(){
		return summary;
	}

	/**
	 * Set image.
	 *
	 * @param image the image
	 */
	public void setImage(String image){
		this.image = image;
	}

	/**
	 * Get image string.
	 *
	 * @return the string
	 */
	public String getImage(){
		return image;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id){
		this.id = id;
	}

	/**
	 * Get id integer.
	 *
	 * @return the integer
	 */
	public Integer getId(){
		return id;
	}

	/**
	 * Set title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * Get title string.
	 *
	 * @return the string
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Set extended ingredients.
	 *
	 * @param extendedIngredients the extended ingredients
	 */
	public void setExtendedIngredients(List<ExtendedIngredientsItem> extendedIngredients){
		this.extendedIngredients = extendedIngredients;
	}

	/**
	 * Get extended ingredients list.
	 *
	 * @return the list
	 */
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