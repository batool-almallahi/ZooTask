package com.zoo.dragons.data;

public class Dragon {

	private int id;
	private String name;
	private String color;
	private String favorateFood;
	private int locationId;
	private String locationName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFavorateFood() {
		return favorateFood;
	}

	public void setFavorateFood(String favorateFood) {
		this.favorateFood = favorateFood;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | NAME: " + name + " | COLOR: " + color + " | FAVORATEFOOD: " + favorateFood
				+ " | LOCATIONID: " + locationId + " | LOCATIONNAME: " + locationName;
	}

}
