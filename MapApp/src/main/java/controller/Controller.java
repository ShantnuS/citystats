package controller;

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
}