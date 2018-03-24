package controller;

import model.CSData;
import model.TTNDevice;

public class DataParser {

	//Use this to turn payload into normalised data type that can be used.
	//Might even parse entire TTNData object to create something better
	public static CSData parseData(TTNDevice device, String payload){
		CSData data = new CSData(device, payload);
		//data.setTemperature(payload);
		return data;
	}
}
