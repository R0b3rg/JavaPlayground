package com.example;

import org.eclipse.paho.client.mqttv3.*;


public class SensorSub{
    public void main(String[] args) throws MqttException {

        String subID = "RaspberryPi";
        MqttAsyncClient sub = new MqttAsyncClient("tcp://192.168.2.140:1883",subID);

        SensorSubCallback mycallback = new SensorSubCallback();
        sub.setCallback(mycallback);

        IMqttToken token = sub.connect();
        token.waitForCompletion();

        sub.subscribe("/sensor",0);
        sub.subscribe("/sensor/sen01",0);



    }
}
