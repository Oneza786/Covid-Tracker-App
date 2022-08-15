package com.java.covidtracker.models;

import java.time.LocalDateTime;

public class LocationStats {
	private String state;
	private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;
    private String lat;
    private String longi;
//    private LocalDateTime dateTime;
    
    
//    public LocalDateTime getDateTime() {
//		return dateTime;
//	}

//	public void setDateTime(LocalDateTime dateTime) {
//		this.dateTime = dateTime;
//	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public LocationStats() {
    	
    }
    
	public LocationStats(String state, String country, int latestTotalCases, int diffFromPrevDay) {
		super();
		this.state = state;
		this.country = country;
		this.latestTotalCases = latestTotalCases;
		this.diffFromPrevDay = diffFromPrevDay;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
				+ ", diffFromPrevDay=" + diffFromPrevDay + ", lat=" + lat + ", longi=" + longi + "]";
	}

	
	
	
	
	

}
