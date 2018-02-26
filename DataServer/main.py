import time
import ttn

app_id = ""
access_key = ""

def uplink_callback(msg, client):
  print("Received uplink from ", msg.dev_id)
  print(msg)

ttn.MQTTClient(app_id, access_key, mqtt_address="", discovery_address="discovery.thethings.network:1900")

#handler = ttn.HandlerClient(app_id, access_key)

# using mqtt client
#mqtt_client = handler.data()
#mqtt_client.set_uplink_callback(uplink_callback)
#mqtt_client.connect()
#time.sleep(60)
#mqtt_client.close()
