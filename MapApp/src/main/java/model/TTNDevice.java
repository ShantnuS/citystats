package model;

import java.util.ArrayList;

import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Marker;

public class TTNDevice {

	String deviceID;
	String longitude;
	String latitude;
	ArrayList<TTNData> allTTNData; //Legacy
	TTNData latestTTNData; //Legacy
	ArrayList<CSData> allData;
	CSData latestData;
	Marker marker;
	InfoWindow infoWindow;
	
	public TTNDevice(String deviceID, String latitude, String longitude) {
		this.deviceID = deviceID;
		this.longitude = longitude;
		this.latitude = latitude;
		allTTNData = new ArrayList<TTNData>();
		allData = new ArrayList<CSData>();
	}
	
	public void setLatestData(CSData latestData) {
		this.latestData = latestData;
		this.addData(latestData);
	}
	
	public void addData(CSData data) {
		allData.add(data);
	}
	
	public CSData getLatestData() {
		return this.latestData;
	}
	
	public ArrayList<CSData> getAllData(){
		return this.allData;
	}
	
	public Marker getMarker(){
		return this.marker;
	}
	
	public InfoWindow getInfoWindow(){
		return this.infoWindow;
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
	
	public void setLatestTTNData(TTNData latestData) {
		this.latestTTNData = latestData;
		this.addTTNData(latestData);
	}
	
	public void addTTNData(TTNData data) {
		allTTNData.add(data);
	}
	
	public TTNData getLatestTTNData() {
		return this.latestTTNData;
	}
	
	public ArrayList<TTNData> getAllTTNData(){
		return this.allTTNData;
	}
	
	public void setMarker(Marker marker){
		this.marker = marker; 
	}
	
	public void setInfoWindow(InfoWindow infoWindow){
		this.infoWindow = infoWindow;
	}
	
	public CSData getOneBeforeLatestData(){
		if(allData.size()>=2)
			return this.allData.get(allData.size()-2);
		else
			return null;
	}
}
