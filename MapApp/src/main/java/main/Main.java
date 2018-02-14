package main;


import org.eclipse.paho.client.mqttv3.MqttException;

import controller.Controller;
import controller.RIKGetter;

public class Main {
	
	public static void main(String[] args) throws MqttException, Exception{
		//TODO: Change this to new app on TTN
        String region = RIKGetter.getRegion();
        String appId = RIKGetter.getId();
        String accessKey = RIKGetter.getKey();
        
        
        Controller controller = Controller.getInstance();
        controller.setRIK(region, appId, accessKey);
        controller.startClient();
     
	}
	
}
