# main.py -- put your code here!
print("Hello there!")
from network import LoRa
import time
import binascii
import pycom
import struct
import socket

#Functions to make it easier to redo certain things############################
def getDeviceEUI():
    lora = LoRa(mode=LoRa.LORAWAN)
    result = binascii.hexlify(lora.mac()).upper().decode('utf-8')
    print(result)
    return result

def connectOTAA(myEUI,myKey):
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

def connectABP(devAddress,netSessKey,appSessKey):
    lora = LoRa(mode=LoRa.LORAWAN)

    dev_addr = struct.unpack(">l", devAddress)[0]

    lora.join(activation=LoRa.ABP, auth=(dev_addr, netSessKey, appSessKey))
    # The loop below is not even needed as connection is instant
    # However since it has no impact I have left it.
    while not lora.has_joined():
        time.sleep(1)
        print('Not joined yet...')
    print('Connection: connected!')

def sendData(data):
    s = socket.socket(socket.AF_LORA, socket.SOCK_RAW)
    s.setsockopt(socket.SOL_LORA, socket.SO_DR, 5)
    s.setblocking(False)
    #s.send(bytearray(data))
    #s.send(data)
    s.send(data.encode('utf-8'))
    #Set LED to green at the end
    pycom.rgbled(0x007f00) # green
    time.sleep(2)
    pycom.heartbeat(False)

def readTemp():
    temp = ""
    #Read temperature here
    return temp

def readLght():
    lght = ""
    #Read light here
    return lght

def readHumi():
    humi = ""
    #Read humidity here
    return humi

def readPrsr():
    prsr = ""
    #Read pressure there
    return prsr
###############################################################################

#LED in the beginning set to red
pycom.heartbeat(False)
pycom.rgbled(0x7f0000) # red
time.sleep(1)

#Set app eui and key
app_eui = binascii.unhexlify('70B3D57ED0008813')
app_key = binascii.unhexlify('FDFDA4AB9CB96B494BEDC19591B6746F')

dAdd = binascii.unhexlify('26012338')
netSKey = binascii.unhexlify('B6E642E16BE7FB9CAEC868D34912A918')
appSKey = binascii.unhexlify('0C8FB72F0851D3DEE8BD07095B701A30')

'''
#Put everything in a while loop
while True:
    #Everything here
'''

connectOTAA(app_eui, app_key)
#connectABP(dAdd,netSKey,appSKey)
temp = 1.54
humi = 59
lght = 34.4
prsr = 54.4
rain = 32.434
sund = 54
output = "t" + str(temp) + ";" + "h" + str(humi) + ";"
#data = "t1.54;h59;l32.4;p54.5;r32.434;s54"
data = output
sendData(data)
