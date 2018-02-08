# main.py -- put your code here!
print("Hello there!")

import myTTN as myTTN

myTTN.getDeviceEUI

myEUI = binascii.unhexlify('70B3D57ED0008034')
myKey = binascii.unhexlify('47FA75005398C64CFAE2387C7A94E5E3')

myTTN.connect(myEUI,myKey)
myTTN.sendData
