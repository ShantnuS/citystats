package controller;

import model.TTNClient;

public class Manager {

	TTNClient client;
	String region;
	String appId;
	String accessKey;
	
	private Manager() {
		
	}
	
	private static Manager instance;
	
	public static Manager getInstance() {
		if(instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	
	public void startClient() {
		if(region != null && appId != null && accessKey != null) {
			client = new TTNClient(region, appId, accessKey);
		}
		else {
			System.err.println("Please set Region, ID and Key using setRIK()");
		}
	}
	
	//Set Region, ID, and Key. Do this before starting the client
	public void setRIK(String region, String appId, String accessKey) {
		this.region = region;
		this.appId = appId;
		this.accessKey = accessKey;
	}
	
}
