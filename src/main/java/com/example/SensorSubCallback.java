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


        // Jerder Sensor besitzt eigenes Topic. Wenn ein Sensor einen Imput übermittelt wird die passende Funktion aufgerufen
        switch (s){
            case "sensor/sen01":

                break;
            case "sensor/sen02":
                break;
            case "sensor/sen03":
                break;
            case "sensor":
                break;

        }
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
