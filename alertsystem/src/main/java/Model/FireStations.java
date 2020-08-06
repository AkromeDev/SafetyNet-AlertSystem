package Model;

public class FireStations {
	
	private String adress;
	private String station;
	private int id;
	
	public FireStations(String adress, String station, int id) {
		super();
		this.adress = adress;
		this.station = station;
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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
