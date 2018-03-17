package model;

//CS stands for city stats. This data object is only necessary for this application 

public class CSData {

	TTNDevice device;
	String raw;
	String formatted;
	String date;
	String temperature;
	String humidity;
	String light;
	String pressure;
	String rain;
	String sound;
	
	public CSData(TTNDevice device, String raw){
		this.device = device;
		this.raw = raw;
	}
	
	public TTNDevice getDevice() {
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
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	
}
