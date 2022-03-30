/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robotino.mqtttest;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Kevin Zahn
 */
public class ReceivedMessage {
    
    public static ArrayList<ReceivedMessage> msgs = new ArrayList<>();
    

    private String msg;
    private static int msgNr = 0;
    private LocalTime time;
    
    public ReceivedMessage(byte [] msg){
       this.msg = new String(msg);
       this.time = LocalTime.now();
       this.msgNr++;
    }
    
    public static void addMsg(ReceivedMessage msgObj){
        msgs.add(msgObj);
    }
    
    public static void print(){
        System.out.println(msgs);
    }


    @Override
    public String toString() {
        return "ReceivedMessage{" +
                "msg='" + msg + '\'' +
                ", time=" + time + '\'' +
                "msgNr='" + msgNr + '\'' +
                '}';
    }
}
