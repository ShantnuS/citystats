# main.py -- put your code here!
print("Hello there!")
from network import LoRa
import time
import binascii
import pycom

#Functions to make it easier to redo certain things############################
def getDeviceEUI():
    lora = LoRa(mode=LoRa.LORAWAN)
    result = binascii.hexlify(lora.mac()).upper().decode('utf-8')
    print(result)
    return result

def connect(myEUI,myKey):
    lora = LoRa(mode=LoRa.LORAWAN)

    lora.join(activation=LoRa.OTAA, auth=(myEUI, myKey), timeout=0)

    # wait until the module has joined the network
    while not lora.has_joined():
        pycom.rgbled(0x7f7f00) # yellow
        time.sleep(1)
        pycom.heartbeat(False)
        time.sleep(1)
        print('Connection: retrying...')

    print('Connection: connected!')


def sendData():
    import socket
    s = socket.socket(socket.AF_LORA, socket.SOCK_RAW)
    s.setsockopt(socket.SOL_LORA, socket.SO_DR, 5)
    s.setblocking(False)
    s.send(bytes([1,2,3,4,5,6,7,8,9,10]))
    #Set LED to green at the end
    pycom.rgbled(0x007f00) # green
    time.sleep(2)
    pycom.heartbeat(False)
###############################################################################

#LED in the beginning set to red
pycom.heartbeat(False)
pycom.rgbled(0x7f0000) # red
time.sleep(1)

#Set app eui and key
app_eui = binascii.unhexlify('70B3D57ED0008813')
app_key = binascii.unhexlify('FDFDA4AB9CB96B494BEDC19591B6746F')

connect(app_eui, app_key)
sendData()
