package model;

public class TTNDevice {

	String deviceID;
	String longitude;
	String latitude;
	
	public TTNDevice(String deviceID, String longitude, String latitude) {
		this.deviceID = deviceID;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
}
