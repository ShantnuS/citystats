package main;

import org.eclipse.paho.client.mqttv3.MqttException;

import ttn.TTNTest;

public class Main {
	
	public static void main(String[] args) throws MqttException, Exception{
		System.out.println("Main method ;)");
		
        String region = "eu";
        String appId = "iot-lopy-test";
        String accessKey = "ttn-account-v2.ok-ag3RbxKAfkCN_7ylsUe-uM1LqL-ezxOURW6jcDWo";
        
        TTNTest test = new TTNTest();
        test.init(region, appId, accessKey);
	}
	
}
