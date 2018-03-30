package model;

//CS stands for city stats. This data object is only necessary for this application 

public class CSData {

	String device;
	String raw;
	String formatted;
	String date;
	
	String temperature;
	String humidity;
	String light;
	String pressure;
	String altitude;
	String tilt;
	String voltage;

	
	public CSData(String device, String raw){
		this.device = device;
		this.raw = raw;
		this.date = "none";
		this.temperature = "none";
		this.humidity = "none";
		this.light = "none";
		this.pressure = "none";
		this.altitude = "none";
		this.tilt = "none";
		this.voltage = "none";
	}
	
	public String getDevice() {
		return device;
	}
	public String getRaw(){
		System.out.println("inside getraw" + raw);
		return this.raw;
	}
	public String getFormatted(){
		return this.formatted;
	}
	public void setFormatted(String formatted){
		this.formatted = formatted;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getTilt() {
		return tilt;
	}

	public void setTilt(String tilt) {
		this.tilt = tilt;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}	
}
