package model;

import java.net.URISyntaxException;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.Metadata;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;


public class TTNClient {

	Client client; 

	public TTNClient(String region, String appId, String accessKey) {
		
		//Changing keep alive time of the client 
		MqttConnectOptions mcopts = new  MqttConnectOptions();
		mcopts.setKeepAliveInterval(Integer.MAX_VALUE);
		
		
		try {
			client = new Client(region, appId, accessKey, mcopts);
		} catch (URISyntaxException e) {
			System.err.println("Could not create client!");
			e.printStackTrace();
		}
		
        client.onError((Throwable _error) -> TTNClient.passError(_error));
        client.onActivation((String _devId, ActivationMessage _data) -> TTNClient.passActivation(_devId, _data));
        client.onConnected((Connection _client) -> TTNClient.passConnection(_client)); 
        client.onMessage((String devId, DataMessage data) -> TTNClient.passMessage(devId, data));
        
        try {
			client.start();
		} catch (MqttException e) {
			System.err.println("Could not start client! - mqtt error");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Could not start client! - unknown error");
			e.printStackTrace();
		}
	}
	
	public static void passMessage(String devId, DataMessage data) {
		UplinkMessage message = (UplinkMessage) data;
		
    	String appID = message.getAppId();
    	String devID = message.getDevId();
    	String payload = Arrays.toString(message.getPayloadRaw());
    	Metadata metaData = message.getMetadata();
    	
    	//TODO: call some method here that stores data onto sql server
    	
	}
	
	public static void passConnection(Connection connection) {
		System.out.println("Connected!");
	}
	
	public static void passError(Throwable error) {
		System.err.println(error);
	}
	
	public static void passActivation(String devId, ActivationMessage data) {
		System.out.println("Activated: " + devId + " with data: " + data);
	}
	
}
