/**
 *  Netgear Router
 *
 *  Copyright 2017 ilker Aktuna
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
//import groovy.json.JsonSlurper  

metadata {
	definition (name: "Netgear Router", namespace: "ilkeraktuna", author: "ilker Aktuna") {
		capability "Switch"
        capability "refresh"
        capability "polling"

		attribute "ip", "string"
		attribute "port", "string"
		attribute "username", "string"
		attribute "password", "string"
        attribute "GuestWifi2Ghz", "string"
        attribute "GuestWifi5Ghz", "string"
        attribute "5ghz", "string"
        attribute "2ghz", "string"
        attribute "Wifi2Ghz", "string"
        attribute "Wifi5Ghz", "string"
        attribute "w5ghz", "string"
        attribute "w2ghz", "string"
//        attribute "gad", "string"
        attribute "reboot", "string"

		command "WirelessOn"
		command "WirelessOff"
        command "WirelessOn5"
		command "WirelessOff5"
        command "GuestWirelessOn"
		command "GuestWirelessOff"
        command "GuestWirelessOn5"
		command "GuestWirelessOff5"
        command "GetAll"
        command "GetAttached"
        command "Reboot"
	}

    preferences {
    	section {
        input title: "", description: "Netgear Router Control", displayDuringSetup: true, type: "paragraph", element: "paragraph"
        input("ip", "string", title:"LAN IP address", description: "LAN IP address", required: true, displayDuringSetup: true)
        input("port", "string", title:"LAN Port", description: "LAN Port", required: true, displayDuringSetup: true)
        input("username", "string", title:"Administrator username (case sensitive)", description: "Administrative rights username (case sensitive)", required: true, displayDuringSetup: true)
        input("password", "password", title:"password (case sensitive)", description: "password (case sensitive)", required: false, displayDuringSetup: true)
        input("ren", "bool", title:"Enable this if you want reboot button?", description: "Reboot Enable", required: false, displayDuringSetup: true)
        }
	}
    
	simulator {
		// TODO: define status and reply messages here
	}

	// UI tile definitions
	tiles(scale: 2) {
		standardTile("GuestWifi5Ghz","device.GuestWifi5Ghz", decoration: "flat", width: 2, height: 1, canChangeIcon: true){
				state "on", label: 'GuestWifi5Ghz\n${name}', action: "GuestWirelessOff5", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "off", label: 'GuestWifi5Ghz\n${name}', action: "GuestWirelessOn5", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
				state "turningOn", label: 'GuestWifi5Ghz\n${name}', action: "GuestWirelessOff5", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "turningOff", label: 'GuestWifi5Ghz\n${name}', action: "GuestWirelessOn5", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
		}
		standardTile("GuestWifi2Ghz","device.GuestWifi2Ghz", decoration: "flat", width: 2, height: 1){
				state "on", label: 'GuestWifi2Ghz\n${name}', action: "GuestWirelessOff", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "off", label: 'GuestWifi2Ghz\n${name}', action: "GuestWirelessOn", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
				state "turningOn", label: 'GuestWifi2Ghz\n${name}', action: "GuestWirelessOff", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "turningOff", label: 'GuestWifi2Ghz\n${name}', action: "GuestWirelessOn", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
		}
		standardTile("Wifi5Ghz","device.Wifi5Ghz", decoration: "flat", width: 2, height: 1, canChangeIcon: true){
				state "on", label: 'Wifi5Ghz\n${name}', action: "WirelessOff5", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "off", label: 'Wifi5Ghz\n${name}', action: "WirelessOn5", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
				state "turningOn", label: 'Wifi5Ghz\n${name}', action: "WirelessOff5", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "turningOff", label: 'Wifi5Ghz\n${name}', action: "WirelessOn5", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
		}
		standardTile("Wifi2Ghz","device.Wifi2Ghz", decoration: "flat", width: 2, height: 1){
				state "on", label: 'Wifi2Ghz\n${name}', action: "WirelessOff", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "off", label: 'Wifi2Ghz\n${name}', action: "WirelessOn", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
				state "turningOn", label: 'Wifi2Ghz\n${name}', action: "WirelessOff", icon: "st.Kids.kids15", backgroundColor: "#79b821", nextState: "turningOff"
				state "turningOff", label: 'Wifi2Ghz\n${name}', action: "WirelessOn", icon: "st.Kids.kids15", backgroundColor: "#ffffff", nextState: "turningOn"
		}
		standardTile( "refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
        valueTile("5ghz", "device.5ghz", decoration: "flat", width: 2, height: 1) {
			state ("default", label:'${currentValue}')
		}
        valueTile("2ghz", "device.2ghz", decoration: "flat", width: 2, height: 1) {
			state ("default", label:'${currentValue}')
		}
        valueTile("w5ghz", "device.w5ghz", decoration: "flat", width: 2, height: 1) {
			state ("default", label:'${currentValue}')
		}
        valueTile("w2ghz", "device.w2ghz", decoration: "flat", width: 2, height: 1) {
			state ("default", label:'${currentValue}')
		}
		standardTile( "refreshall", "device.power", decoration: "flat", width: 2, height: 1) {
			state "default", label:'refresh', action: "GetAll", icon:"st.secondary.refresh", nextState: "default"
		}
		standardTile( "attached", "device.attached", decoration: "flat", width: 4, height: 1) {
			state "default", label:'Get Attached Devices', action: "GetAttached", icon:"st.secondary.refresh", nextState: "default"
		}
//        standardTile("gad", "device.gad", decoration: "flat", width: 4, height: 4) {
//			state ("default", label:'${currentValue}')
//		}
standardTile("gadd1", "device.gadd1", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad1", "device.gad1", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade1", "device.gade1", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf1", "device.gadf1", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd2", "device.gadd2", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad2", "device.gad2", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade2", "device.gade2", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf2", "device.gadf2", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd3", "device.gadd3", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad3", "device.gad3", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade3", "device.gade3", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf3", "device.gadf3", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd4", "device.gadd4", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad4", "device.gad4", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade4", "device.gade4", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf4", "device.gadf4", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd5", "device.gadd5", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad5", "device.gad5", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade5", "device.gade5", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf5", "device.gadf5", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd6", "device.gadd6", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad6", "device.gad6", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade6", "device.gade6", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf6", "device.gadf6", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd7", "device.gadd7", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad7", "device.gad7", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade7", "device.gade7", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf7", "device.gadf7", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd8", "device.gadd8", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad8", "device.gad8", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade8", "device.gade8", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf8", "device.gadf8", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd9", "device.gadd9", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad9", "device.gad9", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade9", "device.gade9", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf9", "device.gadf9", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd10", "device.gadd10", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad10", "device.gad10", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade10", "device.gade10", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf10", "device.gadf10", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd11", "device.gadd11", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad11", "device.gad11", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade11", "device.gade11", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf11", "device.gadf11", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd12", "device.gadd12", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad12", "device.gad12", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade12", "device.gade12", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf12", "device.gadf12", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd13", "device.gadd13", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad13", "device.gad13", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade13", "device.gade13", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf13", "device.gadf13", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd14", "device.gadd14", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad14", "device.gad14", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade14", "device.gade14", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf14", "device.gadf14", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd15", "device.gadd15", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad15", "device.gad15", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade15", "device.gade15", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf15", "device.gadf15", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd16", "device.gadd16", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad16", "device.gad16", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade16", "device.gade16", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf16", "device.gadf16", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd17", "device.gadd17", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad17", "device.gad17", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade17", "device.gade17", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf17", "device.gadf17", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd18", "device.gadd18", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad18", "device.gad18", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade18", "device.gade18", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf18", "device.gadf18", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd19", "device.gadd19", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad19", "device.gad19", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade19", "device.gade19", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf19", "device.gadf19", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd20", "device.gadd20", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad20", "device.gad20", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade20", "device.gade20", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf20", "device.gadf20", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd21", "device.gadd21", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad21", "device.gad21", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade21", "device.gade21", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf21", "device.gadf21", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd22", "device.gadd22", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad22", "device.gad22", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade22", "device.gade22", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf22", "device.gadf22", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd23", "device.gadd23", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad23", "device.gad23", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade23", "device.gade23", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf23", "device.gadf23", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd24", "device.gadd24", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad24", "device.gad24", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade24", "device.gade24", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf24", "device.gadf24", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd25", "device.gadd25", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad25", "device.gad25", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade25", "device.gade25", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf25", "device.gadf25", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd26", "device.gadd26", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad26", "device.gad26", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade26", "device.gade26", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf26", "device.gadf26", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd27", "device.gadd27", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad27", "device.gad27", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade27", "device.gade27", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf27", "device.gadf27", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd28", "device.gadd28", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad28", "device.gad28", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade28", "device.gade28", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf28", "device.gadf28", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd29", "device.gadd29", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad29", "device.gad29", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade29", "device.gade29", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf29", "device.gadf29", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd30", "device.gadd30", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad30", "device.gad30", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade30", "device.gade30", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf30", "device.gadf30", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd31", "device.gadd31", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad31", "device.gad31", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade31", "device.gade31", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf31", "device.gadf31", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd32", "device.gadd32", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad32", "device.gad32", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade32", "device.gade32", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf32", "device.gadf32", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd33", "device.gadd33", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad33", "device.gad33", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade33", "device.gade33", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf33", "device.gadf33", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd34", "device.gadd34", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad34", "device.gad34", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade34", "device.gade34", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf34", "device.gadf34", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd35", "device.gadd35", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad35", "device.gad35", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade35", "device.gade35", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf35", "device.gadf35", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd36", "device.gadd36", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad36", "device.gad36", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade36", "device.gade36", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf36", "device.gadf36", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd37", "device.gadd37", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad37", "device.gad37", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade37", "device.gade37", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf37", "device.gadf37", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd38", "device.gadd38", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad38", "device.gad38", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade38", "device.gade38", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf38", "device.gadf38", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd39", "device.gadd39", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad39", "device.gad39", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade39", "device.gade39", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf39", "device.gadf39", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd40", "device.gadd40", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad40", "device.gad40", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade40", "device.gade40", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf40", "device.gadf40", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd41", "device.gadd41", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad41", "device.gad41", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade41", "device.gade41", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf41", "device.gadf41", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd42", "device.gadd42", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad42", "device.gad42", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade42", "device.gade42", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf42", "device.gadf42", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd43", "device.gadd43", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad43", "device.gad43", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade43", "device.gade43", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf43", "device.gadf43", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd44", "device.gadd44", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad44", "device.gad44", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade44", "device.gade44", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf44", "device.gadf44", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd45", "device.gadd45", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad45", "device.gad45", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade45", "device.gade45", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf45", "device.gadf45", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd46", "device.gadd46", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad46", "device.gad46", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade46", "device.gade46", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf46", "device.gadf46", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd47", "device.gadd47", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad47", "device.gad47", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade47", "device.gade47", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf47", "device.gadf47", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd48", "device.gadd48", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad48", "device.gad48", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade48", "device.gade48", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf48", "device.gadf48", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd49", "device.gadd49", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad49", "device.gad49", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade49", "device.gade49", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf49", "device.gadf49", width: 1, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadd50", "device.gadd50", width: 1, height: 1) {
state ("default", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ffffff") 
state ("wiredok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#79b821") 
state ("wirelessok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#79b821") 
state ("wirednok", label:'', icon: "st.Electronics.electronics6", backgroundColor: "#ff0000") 
state ("wirelessnok", label:'', icon: "st.Entertainment.entertainment15", backgroundColor: "#ff0000") 
}
standardTile("gad50", "device.gad50", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gade50", "device.gade50", width: 2, height: 1) {state ("default", label:'${currentValue}')}
standardTile("gadf50", "device.gadf50", width: 1, height: 1) {state ("default", label:'${currentValue}')}

        standardTile( "reboot", "device.reboot", inactiveLabel: false, decoration: "flat", width: 2, height: 2, canChangeIcon: true) {
        	state "enabled", label: 'Reboot', action: "Reboot", icon: "st.samsung.da.RC_ic_power", backgroundColor: "#79b821"
			state "disabled", label: 'Reboot\n(disabled)', action: "", icon: "st.samsung.da.RC_ic_power", backgroundColor: "#ffffff"
		}
        
		main "GuestWifi2Ghz"
		details(["GuestWifi5Ghz","GuestWifi2Ghz","refresh","5ghz","2ghz","Wifi5Ghz","Wifi2Ghz","reboot","w5ghz","w2ghz","refreshall","attached","gadd1","gad1","gade1","gadf1","gadd2","gad2","gade2","gadf2","gadd3","gad3","gade3","gadf3","gadd4","gad4","gade4","gadf4","gadd5","gad5","gade5","gadf5","gadd6","gad6","gade6","gadf6","gadd7","gad7","gade7","gadf7","gadd8","gad8","gade8","gadf8","gadd9","gad9","gade9","gadf9","gadd10","gad10","gade10","gadf10","gadd11","gad11","gade11","gadf11","gadd12","gad12","gade12","gadf12","gadd13","gad13","gade13","gadf13","gadd14","gad14","gade14","gadf14","gadd15","gad15","gade15","gadf15","gadd16","gad16","gade16","gadf16","gadd17","gad17","gade17","gadf17","gadd18","gad18","gade18","gadf18","gadd19","gad19","gade19","gadf19","gadd20","gad20","gade20","gadf20","gadd21","gad21","gade21","gadf21","gadd22","gad22","gade22","gadf22","gadd23","gad23","gade23","gadf23","gadd24","gad24","gade24","gadf24","gadd25","gad25","gade25","gadf25","gadd26","gad26","gade26","gadf26","gadd27","gad27","gade27","gadf27","gadd28","gad28","gade28","gadf28","gadd29","gad29","gade29","gadf29","gadd30","gad30","gade30","gadf30","gadd31","gad31","gade31","gadf31","gadd32","gad32","gade32","gadf32","gadd33","gad33","gade33","gadf33","gadd34","gad34","gade34","gadf34","gadd35","gad35","gade35","gadf35","gadd36","gad36","gade36","gadf36","gadd37","gad37","gade37","gadf37","gadd38","gad38","gade38","gadf38","gadd39","gad39","gade39","gadf39","gadd40","gad40","gade40","gadf40","gadd41","gad41","gade41","gadf41","gadd42","gad42","gade42","gadf42","gadd43","gad43","gade43","gadf43","gadd44","gad44","gade44","gadf44","gadd45","gad45","gade45","gadf45","gadd46","gad46","gade46","gadf46","gadd47","gad47","gade47","gadf47","gadd48","gad48","gade48","gadf48","gadd49","gad49","gade49","gadf49","gadd50","gad50","gade50","gadf50"])
        
	}
}

def installed() {
    initialize()
}

def updated() {
//    unsubscribe()
    initialize()
}

def poll(){
    //refreshCmd()
    refresh()
}

def initialize() {
    //refreshCmd()
    refresh()
}

// parse events into attributes
def parse(String description) {
	def events = []
    def descMap = parseDescriptionAsMap(description)

	def body = new String(descMap["body"].decodeBase64())
    //log.debug body
    def xmlt = new groovy.util.XmlParser().parseText(body)

	//log.debug xmlt.'*'.'*'.NewGuestAccessEnabled.text()
    if (xmlt.'*'.'m:GetGuestAccessEnabledResponse'.NewGuestAccessEnabled.text() == "1") {sendEvent(name: "GuestWifi2Ghz", value: "on", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:GetGuestAccessEnabledResponse'.NewGuestAccessEnabled.text() == "0") {sendEvent(name: "GuestWifi2Ghz", value: "off", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:Get5GGuestAccessEnabledResponse'.NewGuestAccessEnabled.text() == "1") {sendEvent(name: "GuestWifi5Ghz", value: "on", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:Get5GGuestAccessEnabledResponse'.NewGuestAccessEnabled.text() == "0") {sendEvent(name: "GuestWifi5Ghz", value: "off", isStateChange: true, displayed: false)}

	if (xmlt.'*'.'m:GetInfoResponse'.NewEnable.text() == "1") {sendEvent(name: "Wifi2Ghz", value: "on", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:GetInfoResponse'.NewEnable.text() == "0") {sendEvent(name: "Wifi2Ghz", value: "off", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:Get5GInfoResponse'.NewEnable.text() == "1") {sendEvent(name: "Wifi5Ghz", value: "on", isStateChange: true, displayed: false)}
    if (xmlt.'*'.'m:Get5GInfoResponse'.NewEnable.text() == "0") {sendEvent(name: "Wifi5Ghz", value: "off", isStateChange: true, displayed: false)}
    
	if (xmlt.'*'.'m:Get5GInfoResponse'.NewSSID.text() != null && xmlt.'*'.'m:Get5GInfoResponse'.NewSSID.text() != "") {
    state.wssid5 = xmlt.'*'.'m:Get5GInfoResponse'.NewSSID.text()
    sendEvent(name: "w5ghz", value: "$state.wssid5", isStateChange: true, displayed: false)
    }
    if (xmlt.'*'.'m:GetInfoResponse'.NewSSID.text() != null && xmlt.'*'.'m:GetInfoResponse'.NewSSID.text() != "") {
    state.wssid2 = xmlt.'*'.'m:GetInfoResponse'.NewSSID.text()
    sendEvent(name: "w2ghz", value: "$state.wssid2", isStateChange: true, displayed: false)
    }    
    
	if (xmlt.'*'.'m:Get5GGuestAccessNetworkInfoResponse'.NewSSID.text() != null && xmlt.'*'.'m:Get5GGuestAccessNetworkInfoResponse'.NewSSID.text() != "") {
    state.ssid5 = xmlt.'*'.'m:Get5GGuestAccessNetworkInfoResponse'.NewSSID.text() + "\n" + xmlt.'*'.'m:Get5GGuestAccessNetworkInfoResponse'.NewKey.text()
    sendEvent(name: "5ghz", value: "$state.ssid5", isStateChange: true, displayed: false)
    }
    if (xmlt.'*'.'m:GetGuestAccessNetworkInfoResponse'.NewSSID.text() != null && xmlt.'*'.'m:GetGuestAccessNetworkInfoResponse'.NewSSID.text() != "") {
    state.ssid2 = xmlt.'*'.'m:GetGuestAccessNetworkInfoResponse'.NewSSID.text() + "\n" + xmlt.'*'.'m:GetGuestAccessNetworkInfoResponse'.NewKey.text()
    sendEvent(name: "2ghz", value: "$state.ssid2", isStateChange: true, displayed: false)
    }
    if (xmlt.'*'.'m:GetAttachDeviceResponse'.NewAttachDevice.text() != null && xmlt.'*'.'m:GetAttachDeviceResponse'.NewAttachDevice.text() != "") {
    state.attacheddev = xmlt.'*'.'m:GetAttachDeviceResponse'.NewAttachDevice.text()
    parsegad(state.attacheddev)
    //sendEvent(name: "2ghz", value: "$state.ssid2", isStateChange: true, displayed: false)
    //log.debug "attached: $state.attacheddev"
    }
 
}

private parsegad(rororo) {
	def devlines = []
    //def devicelist
    //def tmpdev
    devlines = rororo.split('@')
    //devicelist="Name\tIPADDR\tMACADDR\tConnected\tAccess\n"
	for (int i = 1; i <  devlines.length; i++){
    def linetmp = []
    linetmp = devlines[i].split(';')
    //devicelist=devicelist + linetmp[2] + "\t" + linetmp[1] + "\t" + linetmp[3] + "\t" + linetmp[4] + "\t" + linetmp[7] + "\n"
    //tmpdev=linetmp[2] + "\t" + linetmp[1] + "\t" + linetmp[3] + "\n"
    sendEvent(name: "gad$i", value: "${linetmp[2]}", isStateChange: true, displayed: false)
    sendEvent(name: "gade$i", value: "${linetmp[1]}", isStateChange: true, displayed: false)
    sendEvent(name: "gadf$i", value: "${linetmp[3]}", isStateChange: true, displayed: false)
    if (linetmp[4] == "wired" && linetmp[7] == "Allow") {sendEvent(name: "gadd$i", value: "wiredok", isStateChange: true, displayed: false) }
    if (linetmp[4] == "wireless" && linetmp[7] == "Allow") {sendEvent(name: "gadd$i", value: "wirelessok", isStateChange: true, displayed: false) }
    if (linetmp[4] == "wired" && linetmp[7] != "Allow") {sendEvent(name: "gadd$i", value: "wirednok", isStateChange: true, displayed: false) }
    if (linetmp[4] == "wireless" && linetmp[7] != "Allow") {sendEvent(name: "gadd$i", value: "wirelessnok", isStateChange: true, displayed: false) }
    }
    //sendEvent(name: "gad", value: "$devicelist", isStateChange: true, displayed: false)
}

def parseDescriptionAsMap(description) {
	description.split(",").inject([:]) { map, param ->
		def nameAndValue = param.split(":")
        
        if (nameAndValue.length == 2) map += [(nameAndValue[0].trim()):nameAndValue[1].trim()]
        else map += [(nameAndValue[0].trim()):""]
	}
}

// handle commands
def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}

def WirelessOn5() {
	//log.debug "Executing 'GuestWirelessOn 5Ghz'"
return [authrouter(), delayAction(9000), configStarted(), delayAction(9000), wifi5enable(), delayAction(9000), configFinished(), delayAction(9000), wifi5stat()]
}

def WirelessOff5() {
	//log.debug "Executing 'GuestWirelessOff 5Ghz'"
return [authrouter(), delayAction(9800), configStarted(), delayAction(9800), wifi5disable(), delayAction(9800), configFinished(), delayAction(9000), wifi5stat()]
}

def WirelessOn() {
	//log.debug "Executing 'GuestWirelessOn 2Ghz'"
return [authrouter(), delayAction(9000), configStarted(), delayAction(9000), wifi2enable(), delayAction(9000), configFinished(), delayAction(9000), wifi2stat()]
}

def WirelessOff() {
	//log.debug "Executing 'GuestWirelessOff 2Ghz'"
return [authrouter(), delayAction(9800), configStarted(), delayAction(9800), wifi2disable(), delayAction(9800), configFinished(), delayAction(9000), wifi2stat()]
}

def GuestWirelessOn5() {
	//log.debug "Executing 'GuestWirelessOn 5Ghz'"
return [authrouter(), delayAction(9000), configStarted(), delayAction(9000), gwon5(), delayAction(9000), configFinished(), delayAction(9000), gwget5(), delayAction(9000), gwinfo5()]
}

def GuestWirelessOff5() {
	//log.debug "Executing 'GuestWirelessOff 5Ghz'"
return [authrouter(), delayAction(9800), configStarted(), delayAction(9800), gwoff5(), delayAction(9800), configFinished(), delayAction(9000), gwget5()]
}

def GuestWirelessOn() {
	//log.debug "Executing 'GuestWirelessOn 2Ghz'"
return [authrouter(), delayAction(9000), configStarted(), delayAction(9000), gwon(), delayAction(9000), configFinished(), delayAction(9000), gwget(), delayAction(9000), gwinfo()]
}

def GuestWirelessOff() {
	//log.debug "Executing 'GuestWirelessOff 2Ghz'"
return [authrouter(), delayAction(9800), configStarted(), delayAction(9800), gwoff(), delayAction(9800), configFinished(), delayAction(9000), gwget()]
}

def GetAttached() {
return [authrouter(), delayAction(9800), getattacheddev()]
}

def Reboot() {
return [authrouter(), delayAction(9800), configStarted(), delayAction(9800), rebootoff(), delayAction(9800), configFinished()]
}

def refresh() {
	//log.debug "Executing refreshCmd"
	
    if (ren == true) {
    sendEvent(name: "reboot", value: "enabled", isStateChange: true, displayed: false)
    }
    if (ren == false) {
    sendEvent(name: "reboot", value: "disabled", isStateChange: true, displayed: false)
    }
    def host = ip 
    def port = port
    def hosthex = convertIPtoHex(host)
    def porthex = convertPortToHex(port)
    //log.debug "The device id before update is: $device.deviceNetworkId"
    device.deviceNetworkId = "$hosthex:$porthex"
    //log.debug "The device id configured is: $device.deviceNetworkId"
    
    //return gwgetall()
    //return infoall
    //return GetAll()
    return [gwget(), delayAction(1000), gwget5(), delayAction(1000), gwinfo(), delayAction(1000), gwinfo5(), delayAction(1000), gwinfo5(), delayAction(1000), wifi2stat(), delayAction(1000), wifi5stat()]
    
}

def GetAll() {
	state.lastcmd = "getall"
    return [authrouter(), delayAction(1000), gwget(), delayAction(1000), gwget5(), delayAction(1000), gwinfo(), delayAction(1000), gwinfo5(), delayAction(1000), wifi2stat(), delayAction(1000), wifi5stat()]
}

private gwgetall() {
	state.lastcmd = "gwgetall"
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:GetGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:GetGuestAccessEnabled>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
    
    new physicalgraph.device.HubAction("delay 2000")
    
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Get5GGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:Get5GGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:Get5GGuestAccessEnabled>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private getattacheddev() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:DeviceInfo:1#GetAttachDevice")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="utf-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:GetAttachDevice xmlns:M1="urn:NETGEAR-ROUTER:service:DeviceInfo:1">
</M1:GetAttachDevice>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private wifi2stat() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:GetInfo xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
</M1:GetInfo>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private wifi5stat() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Get5GInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:Get5GInfo xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
</M1:Get5GInfo>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private wifi2enable() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#SetEnable")
    //headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:SetEnable xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewEnable>1</NewEnable>
</M1:SetEnable>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private wifi5enable() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Set5GEnable")
    //headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:Set5GEnable xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewEnable>1</NewEnable>
</M1:Set5GEnable>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}


private wifi2disable() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#SetEnable")
    //headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:SetEnable xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewEnable>0</NewEnable>
</M1:SetEnable>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private wifi5disable() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Set5GEnable")
    //headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:Set5GEnable xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewEnable>0</NewEnable>
</M1:Set5GEnable>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private rebootoff() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:DeviceConfig:1#Reboot")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="utf-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:Reboot xmlns:M1="urn:NETGEAR-ROUTER:service:DeviceConfig:1"></M1:Reboot>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwoff() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#SetGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:SetGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewGuestAccessEnabled>0</NewGuestAccessEnabled>
</M1:SetGuestAccessEnabled>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private gwon() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#SetGuestAccessEnabled2")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:SetGuestAccessEnabled2 xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewGuestAccessEnabled>1</NewGuestAccessEnabled>
</M1:SetGuestAccessEnabled2>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwget() {
	state.lastcmd = "gwget"
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:GetGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:GetGuestAccessEnabled>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwinfo() {
	state.lastcmd = "gwget"
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#GetGuestAccessNetworkInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:GetGuestAccessNetworkInfo xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:GetGuestAccessNetworkInfo>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwoff5() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Set5GGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?><SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header>
<SOAP-ENV:Body>
<M1:Set5GGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewGuestAccessEnabled>0</NewGuestAccessEnabled>
</M1:Set5GGuestAccessEnabled>
</SOAP-ENV:Body>
</SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}

private gwon5() {
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Set5GGuestAccessEnabled2")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:Set5GGuestAccessEnabled2 xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1">
<NewGuestAccessEnabled>1</NewGuestAccessEnabled>
</M1:Set5GGuestAccessEnabled2>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwget5() {
	state.lastcmd = "gwget5"
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Get5GGuestAccessEnabled")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:Get5GGuestAccessEnabled xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:Get5GGuestAccessEnabled>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private gwinfo5() {
	state.lastcmd = "gwget5"
	def host = "$ip:$port"
    def method = "POST"
    def path = "/soap/server_sa/"   
    def headers = [:] 
    headers.put("HOST", "$host")
    headers.put("SOAPAction", "urn:NETGEAR-ROUTER:service:WLANConfiguration:1#Get5GGuestAccessNetworkInfo")
	headers.put("content-type", "text/xml;charset=utf-8")
    try {
    def body="""<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<SOAP-ENV:Envelope xmlns:SOAPSDK1="http://www.w3.org/2001/XMLSchema" xmlns:SOAPSDK2="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAPSDK3="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header>
<SessionID>58DEE6006A88A967E89A</SessionID>
</SOAP-ENV:Header><SOAP-ENV:Body>
<M1:Get5GGuestAccessNetworkInfo xmlns:M1="urn:NETGEAR-ROUTER:service:WLANConfiguration:1"></M1:Get5GGuestAccessNetworkInfo>
</SOAP-ENV:Body></SOAP-ENV:Envelope>"""

    def hubAction = new physicalgraph.device.HubAction(
    	method: method,
    	path: path,
    	headers: headers,
        body: body
        )
    hubAction
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
}

private authrouter() {
def hubaction
    def host = "$ip:$port"
        	try {
    hubaction = new physicalgraph.device.HubSoapAction(
	path:    "/soap/server_sa/",
	urn:     'urn:NETGEAR-ROUTER:service:ParentalControl:1',
	action:  "Authenticate",
	body:    ["NewPassword":"$password", "NewUsername":"$username" ],
	headers: [Host:"$host", CONNECTION: "close"]
    )
    }
    catch (Exception e) {
        log.debug e
    }
    return hubaction
}

private configStarted() {
state.lastcmd = "configStarted"
def hubaction
    def host = "$ip:$port"
            try {
   	hubaction = new physicalgraph.device.HubSoapAction(
	path:    "/soap/server_sa/",
	urn:     'urn:NETGEAR-ROUTER:service:DeviceConfig:1',
	action:  "ConfigurationStarted",
	body:    ["NewSessionID":"58DEE6006A88A967E89A" ],
	headers: [Host:"$host", CONNECTION: "keep-alive"]
    )
    }
    catch (Exception e) {
        log.debug e
    }
    return hubaction
}

private configFinished() {
state.lastcmd = "configFinished"
def hubaction
    def host = "$ip:$port"
            try {
   	hubaction = new physicalgraph.device.HubSoapAction(
	path:    "/soap/server_sa/",
	urn:     'urn:NETGEAR-ROUTER:service:DeviceConfig:1',
	action:  "ConfigurationFinished",
	body:    ["NewStatus":"ChangesApplied" ],
	headers: [Host:"$host", CONNECTION: "keep-alive"]
    )
    }
    catch (Exception e) {
        log.debug e
    }
    return hubaction
}

private delayAction(long time) {
	new physicalgraph.device.HubAction("delay $time")
}

private String convertIPtoHex(ipAddress) { 
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
    //log.debug "IP address entered is $ipAddress and the converted hex code is $hex"
    return hex

}

private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
    //log.debug hexport
    return hexport
}


//bunlar lazım mı

// gets the address of the Hub
private getCallBackAddress() {
    return device.hub.getDataValue("localIP") + ":" + device.hub.getDataValue("localSrvPortTCP")
}

// gets the address of the device
private getHostAddress() {
    def ip = getDataValue("ip")
    def port = getDataValue("port")

    if (!ip || !port) {
        def parts = device.deviceNetworkId.split(":")
        if (parts.length == 2) {
            ip = parts[0]
            port = parts[1]
        } else {
            log.warn "Can't figure out ip and port for device: ${device.id}"
        }
    }

    //log.debug "Using IP: $ip and port: $port for device: ${device.id}"
    return convertHexToIP(ip) + ":" + convertHexToInt(port)
}

private Integer convertHexToInt(hex) {
    return Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {
    return [convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}