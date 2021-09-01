package com.example;

import org.eclipse.paho.client.mqttv3.*;


public class SensorSub{
    static MqttAsyncClient sub;

    static {
        try {
            sub = new MqttAsyncClient("tcp://192.168.2.140:1883","RaspberryPi");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public SensorSub() throws MqttException {
    }

    public void main(String[] args) throws MqttException {

        SensorSubCallback mycallback = new SensorSubCallback();
        sub.setCallback(mycallback);

        IMqttToken token = sub.connect();
        token.waitForCompletion();

        sub.subscribe("/sensor",0);
        sub.subscribe("/sensor/sen01",0);



    }
    public static void publishToAkteur (String akteur, String toggle){
        try{
            sub.publish("/akteur" + akteur,toggle.getBytes(),0,true);
        } catch (MqttException e) {
        e.printStackTrace();
    }

    }
}
