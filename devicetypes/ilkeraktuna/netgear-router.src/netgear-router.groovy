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
        attribute "gad", "string"
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
		standardTile( "refreshall", "device.power", decoration: "flat", width: 2, height: 2) {
			state "default", label:'refresh', action: "GetAll", icon:"st.secondary.refresh", nextState: "default"
		}
		standardTile( "attached", "device.attached", decoration: "flat", width: 2, height: 2) {
			state "default", label:'Get Attached Devices', action: "GetAttached", icon:"st.secondary.refresh", nextState: "default"
		}
        standardTile("gad", "device.gad", decoration: "flat", width: 4, height: 6) {
			state ("default", label:'${currentValue}')
		}
        standardTile( "reboot", "device.reboot", inactiveLabel: false, decoration: "flat", width: 2, height: 2, canChangeIcon: true) {
        	state "enabled", label: 'Reboot', action: "Reboot", icon: "st.samsung.da.RC_ic_power", backgroundColor: "#79b821"
			state "disabled", label: 'Reboot\n(disabled)', action: "", icon: "st.samsung.da.RC_ic_power", backgroundColor: "#ffffff"
		}
		main "GuestWifi2Ghz"
		details(["GuestWifi5Ghz","GuestWifi2Ghz","refresh","5ghz","2ghz","Wifi5Ghz","Wifi2Ghz","refreshall","w5ghz","w2ghz","attached","gad","reboot"])
        
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
    def devicelist
    devlines = rororo.split('@')
    devicelist="Name\tIPADDR\tMACADDR\tConnected\tAccess\n"
	for (int i = 1; i <  devlines.length; i++){
    def linetmp = []
    linetmp = devlines[i].split(';')
    devicelist=devicelist + linetmp[2] + "\t" + linetmp[1] + "\t" + linetmp[3] + "\t" + linetmp[4] + "\t" + linetmp[7] + "\n"
    }
    sendEvent(name: "gad", value: "$devicelist", isStateChange: true, displayed: false)
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