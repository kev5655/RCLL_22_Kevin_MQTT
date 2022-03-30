/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robotino.mqtttest;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author Kevin Zahn
 */
public class main {
    

    
    public static void main(String[]arg ) throws MqttException{
        String topicToSend = "robotino/fromJava";
        String topicToReceive = "robotino/kevin";
        
        //System.out.println("Hallo Welt");
        
        String publisherId = UUID.randomUUID().toString();
        IMqttClient client = new MqttClient("tcp://broker.hivemq.com:1883",publisherId);
        client.connect();
        
        SendMqtt sendMqtt = new SendMqtt(client, topicToSend);
        
        try {
            sendMqtt.call(); // Sending data
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ReceiveMqtt receiveMqtt = new ReceiveMqtt(client, topicToReceive);
        } catch (InterruptedException ex) { // Empfange von Daten not JSON
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }
    
}
