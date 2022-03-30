/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robotino.mqtttest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *https://github.com/SolaceSamples/solace-samples-mqtt/blob/master/src/main/java/com/solace/samples/BasicReplier.java
 * @author Kevin Zahn
 */
public class ReceiveMqtt {// implements MqttCallback
    
    String subscribeTopic;
    

    public ReceiveMqtt(IMqttClient client, String subscribeTopic) throws MqttException, InterruptedException {
        
        CountDownLatch receivedSignal = new CountDownLatch(10);
        client.subscribe(subscribeTopic, (topic, msg) -> {
            
            
            byte[] payload = msg.getPayload();
            
            ReceivedMessage msgSave = new ReceivedMessage(payload);
            ReceivedMessage.addMsg(msgSave);
            ReceivedMessage.print();
            
            receivedSignal.countDown();
        });    
        receivedSignal.await(1, TimeUnit.MINUTES);
    }
}
