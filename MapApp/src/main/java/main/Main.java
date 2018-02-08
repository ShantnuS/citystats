package main;

import org.eclipse.paho.client.mqttv3.MqttException;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) throws MqttException, Exception{
		//TODO: Change this to new app on TTN
        String region = "eu";
        String appId = "iot-lopy-test";
        String accessKey = "ttn-account-v2.ok-ag3RbxKAfkCN_7ylsUe-uM1LqL-ezxOURW6jcDWo";
        
        Controller controller = Controller.getInstance();
        controller.setRIK(region, appId, accessKey);
        controller.startClient();
	}
	
}
