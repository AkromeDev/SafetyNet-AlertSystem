package com.safetynet.alertsystem.model;

public class FireStations {
	
	private String address;
	private Integer station;
	private Integer id;
	
	public FireStations(String address, Integer station, Integer id) {
		super();
		this.address = address;
		this.station = station;
		this.id = id;
	}
	
	public FireStations() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
