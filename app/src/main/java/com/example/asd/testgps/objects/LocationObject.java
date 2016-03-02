package com.example.asd.testgps.objects;

import java.io.Serializable;

public class LocationObject implements Serializable {
	

	private double latitude = 0.0; 
	
	private double longitude = 0.0;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
