package model;

import java.util.ArrayList;

public class TTNDevice {

	String deviceID;
	String longitude;
	String latitude;
	ArrayList<TTNData> allData;
	TTNData latestData;
	
	public TTNDevice(String deviceID, String latitude, String longitude) {
		this.deviceID = deviceID;
		this.longitude = longitude;
		this.latitude = latitude;
		allData = new ArrayList<TTNData>();
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
	
	public void setLatestData(TTNData latestData) {
		this.latestData = latestData;
		this.addData(latestData);
	}
	
	public void addData(TTNData data) {
		allData.add(data);
	}
	
	public TTNData getLatestData() {
		return this.latestData;
	}
	
	public ArrayList<TTNData> getAllData(){
		return this.allData;
	}
	
}
