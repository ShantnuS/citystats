package controller;

import model.CSData;
import model.TTNDevice;

public class ETCHelper {

	
	public static String getTempFormatted(String deviceID){
		String temp = "<h2><b>Device: " + deviceID + "</b></h3>"
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
	
	public static double getValueFromName(TTNDevice device, String variable){
		double value =0;
		switch(variable){
		case "Temperature": value=Double.parseDouble(device.getLatestData().getTemperature().replaceAll("\\*", "")); 
		break;
		case "Humidity": value=Double.parseDouble(device.getLatestData().getHumidity().replaceAll("\\*", "")); 
		break;
		case "Light": value=Double.parseDouble(device.getLatestData().getLight().replaceAll("\\*", "")); 
		break;
		case "Pressure": value=Double.parseDouble(device.getLatestData().getPressure().replaceAll("\\*", "")); 
		break;
		case "Altitude": value=Double.parseDouble(device.getLatestData().getAltitude().replaceAll("\\*", "")); 
		break;
		case "Tilt": value=Double.parseDouble(device.getLatestData().getTilt().replaceAll("\\*", "")); 
		break;
		case "Voltage": value=Double.parseDouble(device.getLatestData().getVoltage().replaceAll("\\*", "")); 
		break;
		}
		
		return value;
	}
	
	public static double getAverageValue(TTNDevice device, String variable){
		double value = 0;
		int count = 0;
		switch(variable){
		case "Temperature": for(CSData d: device.getAllData()){
			if(!d.getTemperature().equals("none")){
				value+=Double.parseDouble(d.getTemperature());
				count++;
			}
		}
		break;
		case "Humidity": for(CSData d: device.getAllData()){
			if(!d.getHumidity().equals("none")){
				value+=Double.parseDouble(d.getHumidity());
				count++;
			}
		}
		break;
		case "Light": for(CSData d: device.getAllData()){
			if(!d.getLight().equals("none")){
				value+=Double.parseDouble(d.getLight());
				count++;
			}
		}
		break;
		case "Pressure": for(CSData d: device.getAllData()){
			if(!d.getPressure().equals("none")){
				value+=Double.parseDouble(d.getPressure());
				count++;
			}
		}
		break;
		case "Altitude": for(CSData d: device.getAllData()){
			if(!d.getAltitude().equals("none")){
				value+=Double.parseDouble(d.getAltitude());
				count++;
			}
		}
		break;
		case "Tilt": for(CSData d: device.getAllData()){
			if(!d.getTilt().equals("none")){
				value+=Double.parseDouble(d.getTilt());
				count++;
			}
		}
		break;
		case "Voltage": for(CSData d: device.getAllData()){
			if(!d.getVoltage().equals("none")){
				value+=Double.parseDouble(d.getVoltage());
				count++;
			}
		}
		break;
		}
		return value/count;
	}
}
