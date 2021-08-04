package com.example;

import org.eclipse.paho.client.mqttv3.*;


public class MqttTest_01 {
    public void main(String[] args) throws Exception{

        String publisherId = "LaptopDavid";
        IMqttClient publisher = new MqttClient("tcp://iot.eclipse.org:1883",publisherId);

    }
}
