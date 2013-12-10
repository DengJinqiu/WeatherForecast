package edu.jhu.weatherforecast.model;

public class City {
	
//	public City(String name, String WOEID) {
//		this.name = name;
//		this.WOEID = WOEID;
//	}

	private String name;
	private String WOEID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWOEID() {
		return WOEID;
	}

	public void setWOEID(String WOEID) {
		this.WOEID = WOEID;
	}
}
