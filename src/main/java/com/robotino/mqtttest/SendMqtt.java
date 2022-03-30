/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robotino.mqtttest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

// https://www.baeldung.com/java-org-json

/**
 *
 * @author Kevin Zahn
 */
public class SendMqtt implements Callable<Void> {

    IMqttClient client;

    //byte[] payload = "Hallo".getBytes();
    //byte[] payload = "{robo:{ speed: 1000, posX: 5000, posY: 6000 }}".getBytes();
    //byte[] payload = "{robo:{speed: 1000}}".getBytes();
    String topic;

    public SendMqtt(IMqttClient client, String topic) {
        this.client = client;
        this.topic = topic;
    }

    @Override
    public Void call() throws Exception {
        if (!client.isConnected()) {
            System.err.println("can not connect for send data");
            return null;
        }
        byte[] payload = createJsonMsg();

        MqttMessage msg = new MqttMessage(payload);
        msg.setQos(0);
        msg.setRetained(true);

        String s = new String(payload);
        System.out.println("publish: " + s);

        client.publish(topic, msg);
        return null;
    }

    public byte [] createJsonMsg() {
        byte [] payload;

        JSONObject robo = new JSONObject();
        robo.put("speed", new Integer(1000));
        robo.put("xPos", new Integer(5000));
        robo.put("yPos", new Integer(6000));

        JSONObject codesys = new JSONObject();
        codesys.put("griperClose", new Boolean(true));
        codesys.put("xPos", new Integer(3000));
        codesys.put("yPos", new Integer(4000));

        JSONObject mainObj = new JSONObject();
        mainObj.put("robo", robo);
        mainObj.put("codesys", codesys);

        payload = mainObj.toString().getBytes();

        System.out.println(payload);
        return payload;
    }
}
    /* Exampel JSON generate JSON Message
    public void createJson1(){
        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        
        payload = obj.toJSONString().getBytes();

        System.out.println(payload);
    }
    
    public void createJson2(){
        JSONObject jo = new JSONObject();
        jo.put("firstName", "John");
        jo.put("lastName", "Doe");
        
        JSONObject jo1 = new JSONObject();
        jo1.put("firstName", "Kein");
        jo1.put("lastName", "Zahn");

        JSONArray ja = new JSONArray();
        ja.add(jo);
        ja.add(jo1);

        JSONObject mainObj = new JSONObject();
        mainObj.put("employees", ja);
           
        
        payload = mainObj.toJSONString().getBytes();

        System.out.println(payload);
    }
    

    
    public void createJson4(){
        JSONObject jo = new JSONObject();
        jo.put("firstName", "John");
        jo.put("lastName", "Doe");
        
        JSONObject jo1 = new JSONObject();
        jo1.put("firstName", "Kein");
        jo1.put("lastName", "Zahn");
        
        Map<String, JSONObject> map = new HashMap<>();
        
        map.put("robo", jo);
        map.put("codesys", jo1);

        //JSONArray ja = new JSONArray();
        //ja.add(map);

        //JSONObject mainObj = new JSONObject();
        //mainObj.put("employees", map);
        
        //JSONObject mainObj = new JSONObject(map);
           
        
        //payload = mainObj.toJSONString().getBytes();

        //System.out.println(payload);
    }
}
*/

/* Node-Red Module
[
    {
        "id": "c6028e6b5d470cab",
        "type": "tab",
        "label": "Flow 4",
        "disabled": false,
        "info": "",
        "env": []
    },
    {
        "id": "02d2753b090d7721",
        "type": "mqtt out",
        "z": "c6028e6b5d470cab",
        "name": "",
        "topic": "robotino/kevin",
        "qos": "",
        "retain": "",
        "respTopic": "",
        "contentType": "",
        "userProps": "",
        "correl": "",
        "expiry": "",
        "broker": "8dbb677884b3d82a",
        "x": 520,
        "y": 400,
        "wires": []
    },
    {
        "id": "940bfca84a800d27",
        "type": "inject",
        "z": "c6028e6b5d470cab",
        "name": "",
        "props": [
            {
                "p": "payload"
            },
            {
                "p": "topic",
                "vt": "str"
            }
        ],
        "repeat": "",
        "crontab": "",
        "once": false,
        "onceDelay": 0.1,
        "topic": "",
        "payload": "10",
        "payloadType": "num",
        "x": 210,
        "y": 400,
        "wires": [
            [
                "02d2753b090d7721"
            ]
        ]
    },
    {
        "id": "35ee58a108551ca7",
        "type": "mqtt in",
        "z": "c6028e6b5d470cab",
        "name": "",
        "topic": "robotino/fromJava",
        "qos": "2",
        "datatype": "auto",
        "broker": "407a01e4.6b637",
        "nl": false,
        "rap": true,
        "rh": 0,
        "inputs": 0,
        "x": 230,
        "y": 460,
        "wires": [
            [
                "c11704dd40491354",
                "f01320901d4fc2a1"
            ]
        ]
    },
    {
        "id": "c11704dd40491354",
        "type": "debug",
        "z": "c6028e6b5d470cab",
        "name": "",
        "active": false,
        "tosidebar": true,
        "console": false,
        "tostatus": false,
        "complete": "payload",
        "targetType": "msg",
        "statusVal": "",
        "statusType": "auto",
        "x": 530,
        "y": 460,
        "wires": []
    },
    {
        "id": "f01320901d4fc2a1",
        "type": "json",
        "z": "c6028e6b5d470cab",
        "name": "",
        "property": "payload",
        "action": "",
        "pretty": false,
        "x": 390,
        "y": 560,
        "wires": [
            [
                "51d12d8bb7919768",
                "29b34b195572af67",
                "b7832ca6530d7ab0"
            ]
        ]
    },
    {
        "id": "51d12d8bb7919768",
        "type": "debug",
        "z": "c6028e6b5d470cab",
        "name": "",
        "active": true,
        "tosidebar": true,
        "console": false,
        "tostatus": false,
        "complete": "payload",
        "targetType": "msg",
        "statusVal": "",
        "statusType": "auto",
        "x": 590,
        "y": 520,
        "wires": []
    },
    {
        "id": "29b34b195572af67",
        "type": "debug",
        "z": "c6028e6b5d470cab",
        "name": "",
        "active": true,
        "tosidebar": true,
        "console": false,
        "tostatus": false,
        "complete": "payload.codesys",
        "targetType": "msg",
        "statusVal": "",
        "statusType": "auto",
        "x": 600,
        "y": 580,
        "wires": []
    },
    {
        "id": "b7832ca6530d7ab0",
        "type": "debug",
        "z": "c6028e6b5d470cab",
        "name": "",
        "active": true,
        "tosidebar": true,
        "console": false,
        "tostatus": false,
        "complete": "payload.robo",
        "targetType": "msg",
        "statusVal": "",
        "statusType": "auto",
        "x": 590,
        "y": 640,
        "wires": []
    },
    {
        "id": "8dbb677884b3d82a",
        "type": "mqtt-broker",
        "name": "",
        "broker": "mqtt://broker.hivemq.com",
        "port": "1883",
        "clientid": "",
        "autoConnect": true,
        "usetls": false,
        "protocolVersion": "4",
        "keepalive": "60",
        "cleansession": true,
        "birthTopic": "",
        "birthQos": "0",
        "birthPayload": "",
        "birthMsg": {},
        "closeTopic": "",
        "closeQos": "0",
        "closePayload": "",
        "closeMsg": {},
        "willTopic": "",
        "willQos": "0",
        "willPayload": "",
        "willMsg": {},
        "sessionExpiry": ""
    },
    {
        "id": "407a01e4.6b637",
        "type": "mqtt-broker",
        "name": "",
        "broker": "mqtt://broker.hivemq.com",
        "port": "1883",
        "clientid": "",
        "autoConnect": true,
        "usetls": false,
        "protocolVersion": "4",
        "keepalive": "60",
        "cleansession": true,
        "birthTopic": "",
        "birthQos": "0",
        "birthPayload": "",
        "birthMsg": {},
        "closeTopic": "",
        "closePayload": "",
        "closeMsg": {},
        "willTopic": "",
        "willQos": "0",
        "willPayload": "",
        "willMsg": {},
        "sessionExpiry": ""
    }
]
*/