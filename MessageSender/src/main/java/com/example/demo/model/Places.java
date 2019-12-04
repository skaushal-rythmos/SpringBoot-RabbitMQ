package com.example.demo.model;

public class Places {

	private String id;
	private String city;
	private String country;

	public Places(String id, String city, String country) {

		this.id = id;
		this.city = city;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
