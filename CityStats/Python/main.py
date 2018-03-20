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
    temp = 0
    #Read temperature here
    return temp

def readLght():
    lght = 0
    #Read light here
    return lght

def readHumi():
    humi = 0
    #Read humidity here
    return humi

def readPrsr():
    prsr = 0
    #Read pressure here
    return prsr

def readAlti():
    alti = 0
    #Read altitude here
    return alti

def readTilt():
    tilt = 0
    #Read tilt here
    return tilt

def percentChange(newValue, oldValue):
    if newValue == oldValue:
        return 100.0
    try:
       return (abs(newValue - oldValue))/oldValue)*100.0
    except ZeroDivisionError:
        return 0

def isSigDiff(newValue, oldValue):
    percent = percentChange(newValue, oldValue)
    # 10 percent change or greater
    if percent >= 110.0 or percent <= 90.0:
        return True
    else:
        return False

###############################################################################

#LED in the beginning set to red
pycom.heartbeat(False)
pycom.rgbled(0x7f0000) # red
time.sleep(1)

#Set app eui and key
#This is needed for OTAA
app_eui = binascii.unhexlify('70B3D57ED0008813')
app_key = binascii.unhexlify('FDFDA4AB9CB96B494BEDC19591B6746F')

#This is needed for ABP (but is prone to changing)
dAdd = binascii.unhexlify('26012338')
netSKey = binascii.unhexlify('B6E642E16BE7FB9CAEC868D34912A918')
appSKey = binascii.unhexlify('0C8FB72F0851D3DEE8BD07095B701A30')

#Measure temperature, humidity, light, pressure, altitude and tilt
temp = 0 #Called "t"
humi = 0 #Called "h"
lght = 0 #Called "l"
prsr = 0 #Called "p"
alti = 0 #Called "a"
tilt = 0 #Called "i"

### CITY STATS' COOL LOOP ###
while True:
    output = ""
    time.sleep(1) ### SLEEPY TIME ###
    #Temperature
    old = temp
    temp = readTemp()
    if isSigDiff(temp, old):
        output += "t" + str(temp) + ":"
    #Humidity
    old = humi
    humi = readHumi()
    if isSigDiff(humi, old):
        output += "h" + str(humi) + ":"
    #Light
    old = lght
    lght = readLght()
    if isSigDiff(lght, old):
        output += "l" + str(lght) + ":"
    #Pressure
    old = prsr
    prsr = readPrsr()
    if isSigDiff(prsr, old):
        output += "p" + str(prsr) + ":"
    #Altitude
    old = alti
    alti = readAlti()
    if isSigDiff(alti, old):
        output += "a" + str(alti) + ":"
    #Tilt
    old = tilt
    tilt = readTilt()
    if isSigDiff(tilt, old):
        output += "i" + str(tilt) + ":"
    #Send over output if not empty
    if output != "":
        data = output
        connectOTAA(app_eui, app_key)
        sendData(data)

#LEGACY HELP CODE
'''
#connectABP(dAdd,netSKey,appSKey)
output = "t" + str(temp) + ":" + "h" + str(humi) + ":"
#data = "t1.54;h59;l32.4;p54.5;r32.434;s54"
data = output
'''
