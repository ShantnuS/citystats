# main.py -- put your code here!
print("Hello there!")

#General Imports
from network import LoRa
import time
import binascii
import pycom
import struct
import socket

#Pysense Imports
from pysense import Pysense
from LIS2HH12 import LIS2HH12
from SI7006A20 import SI7006A20
from LTR329ALS01 import LTR329ALS01
from MPL3115A2 import MPL3115A2,ALTITUDE,PRESSURE

py = Pysense()
mpa = MPL3115A2(py,mode=ALTITUDE)
mpp = MPL3115A2(py,mode=PRESSURE)
si = SI7006A20(py)
lt = LTR329ALS01(py)
li = LIS2HH12(py)

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
    temp = si.temperature()
    return temp

def readLght():
    lght = lt.light() #Red channel lux
    return lght[1]

def readHumi():
    humi = si.humidity()
    return humi

def readPrsr():
    prsr = mpp.pressure()
    return prsr

def readAlti():
    alti = mpa.altitude()
    return alti

def readTilt():
    tilt = li.roll()
    return tilt

def readVolt():
    volt = py.read_battery_voltage()
    return volt

## Returns the percent difference. If old value was 0 but new isn't then returns 100
def percentChange(newValue, oldValue):
    try:
        answer = (abs(newValue - oldValue))/oldValue
        answer *= 100
        return answer
    except ZeroDivisionError:
        if newValue != 0:
            return 100
        else:
            return 0

#Returns true if there is thrshold difference between new and old
def isSigDiff(newValue, oldValue, threshold):
    percent = percentChange(newValue, oldValue)
    # 10 percent change or greater
    if percent >= threshold:
        return True
    else:
        return False

###############################################################################

#LED in the beginning set to red
'''
pycom.heartbeat(False)
pycom.rgbled(0x7f0000) # red
time.sleep(1)
'''
#Set app eui and key
#This is needed for OTAA
app_eui = binascii.unhexlify('70B3D57ED0008813')
app_key = binascii.unhexlify('FDFDA4AB9CB96B494BEDC19591B6746F')

#This is needed for ABP (but is prone to changing)
dAdd = binascii.unhexlify('26012338')
netSKey = binascii.unhexlify('B6E642E16BE7FB9CAEC868D34912A918')
appSKey = binascii.unhexlify('0C8FB72F0851D3DEE8BD07095B701A30')

#Measure temperature, humidity, light, pressure, altitude and tilt
temp = otemp = 0 #Called "t"
humi = ohumi = 0 #Called "h"
lght = olght = 0 #Called "l"
prsr = oprsr = 0 #Called "p"
alti = oalti = 0 #Called "a"
tilt = otilt = 0 #Called "i"
volt = 0 #Called "v"

### CITY STATS' COOL LOOP ###
while True:
    output = ""
    #time.sleep(1) ### SLEEPY TIME ###
    #Temperature
    temp = readTemp()
    if isSigDiff(temp, otemp, 10.0):
        output += "t" + str(temp) + ":"
    #Humidity
    humi = readHumi()
    if isSigDiff(humi, ohumi, 10.0):
        output += "h" + str(humi) + ":"
    #Light
    lght = readLght()
    if isSigDiff(lght, olght, 10.0):
        output += "l" + str(lght) + ":"
    #Pressure
    prsr = readPrsr()
    if isSigDiff(prsr, oprsr, 10.0):
        output += "p" + str(prsr) + ":"
    #Altitude
    alti = readAlti()
    if isSigDiff(alti, oalti, 10.0):
        output += "a" + str(alti) + ":"
    #Tilt
    tilt = readTilt()
    if isSigDiff(tilt, otilt, 10.0):
        output += "i" + str(tilt) + ":"
    #Send over output if not empty
    if output != "":
        volt = readVolt()
        output += "v" + str(volt)
        data = output
        #connectOTAA(app_eui, app_key)
        #sendData(data)
        print(data)
        #Set old variables to ones which were sent
        otemp = temp
        ohumi = humi
        olght = lght
        oprsr = prsr
        oalti = alti
        otilt = tilt
    else:
        print("No change...")

#LEGACY HELP CODE
'''
#connectABP(dAdd,netSKey,appSKey)
output = "t" + str(temp) + ":" + "h" + str(humi) + ":"
#data = "t1.54;h59;l32.4;p54.5;r32.434;s54"
data = output
'''
