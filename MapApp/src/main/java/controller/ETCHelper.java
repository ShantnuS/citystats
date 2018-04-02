package controller;

public class ETCHelper {

	
	public static String getTempFormatted(String deviceID){
		String temp = "<h3><b>Device: " + deviceID + "</b></h3>"
				 + "<br><b>Time: </b>" + "none" ;
		
		temp += "<br><b>Temperature: </b>" + "none";
		temp += "<br><b>Light: </b>" + "none";
		temp += "<br><b>Humidity: </b>" + "none";
		temp += "<br><b>Pressure: </b>" + "none";
		temp += "<br><b>Altitude: </b>" + "none";
		temp += "<br><b>Tilt: </b>" + "none";
		temp += "<br><b>Voltage: </b>" + "none";
		
		return temp;
	}
}
