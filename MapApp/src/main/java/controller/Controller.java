package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import model.TTNClient;
import model.TTNDevice;
import view.MainFrame;

public class Controller {

	TTNClient client;
	String region;
	String appId;
	String accessKey;
	MainFrame frame;
	ArrayList<TTNDevice> devices;
	
	private Controller() {
		devices = new ArrayList<TTNDevice>();
		try {
			this.createDevices();
		} catch (Exception e) {
			System.err.println("Could not create devices from file!");
			e.printStackTrace();
		}
		
		System.out.println("Controller is ready!");
	}
	
	private static Controller instance;
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void startUI() {
		frame = new MainFrame();
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
	
	public TTNDevice getDevice(String deviceID) {
		TTNDevice device = null;
		for(TTNDevice d:devices) {
			if(d.getDeviceID().equals(deviceID)) {
				device = d;
			}
		}
		return device;
	}
	
	public void createDevices() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("res\\devices.txt"));
		String line = null;  
		while ((line = br.readLine()) != null)  {  
			String[] parts = line.split(" ");
			String deviceID = parts[0];
			String latitude = parts[1];
			String longitude = parts[2];
			TTNDevice device = new TTNDevice(deviceID, latitude, longitude);
			this.addDevice(device);
		} 
		br.close();
	}
	
	public void addDevice(TTNDevice device) {
		devices.add(device);
	}
	
	public void printAllDevices() {
		for(TTNDevice d: devices) {
			System.out.println(d.getDeviceID() +  "," +  d.getLatitude() + "," + d.getLongitude());
		}
	}
	
	public void printLatestData() {
		for(TTNDevice d: devices) {
			System.out.println(d);
			System.out.println(d.getLatestData().getPayload());
		}
	}
}
