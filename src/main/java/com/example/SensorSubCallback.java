package com.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallback;

public class SensorSubCallback implements MqttCallback{
    public static void main(String[] args) {

    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        if(s.equals("sensor/sen01")){
            if(mqttMessage.equals("akt"))
            System.out.println(mqttMessage);
        }if(s.equals("sensor/")){

        }else{
            System.out.println(s + mqttMessage);
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
