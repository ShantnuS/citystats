package main;


import java.io.IOException;
import javax.swing.SwingUtilities;
import org.eclipse.paho.client.mqttv3.MqttException;

import controller.Manager;
import controller.RIKGetter;

public class Main {
	
	public static void main(String[] args) throws MqttException, Exception{

		//Initial Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					Main.run();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });

	}
	
	public static void run() throws IOException {
        String region = RIKGetter.getRegion();
        String appId = RIKGetter.getId();
        String accessKey = RIKGetter.getKey();
        
        Manager controller = Manager.getInstance();
        controller.setRIK(region, appId, accessKey);
        controller.startClient();
	}
	
}