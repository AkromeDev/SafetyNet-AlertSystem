package com.safetynet.alertsystem.model;

public class FireStations {
	
	private String address;
	private String station;
	private int id;
	
	public FireStations(String address, String station, int id) {
		super();
		this.address = address;
		this.station = station;
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
