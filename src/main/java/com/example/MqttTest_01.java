package com.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import org.eclipse.paho.client.mqttv3.*;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class MqttTest_01 {

    private static Integer LED_PIN = 24;
    private int BUTTON_PIN = 25;



    public void main(String[] args) throws Exception{


        String publisherId = "LaptopDavid";
        MqttAsyncClient publisher = new MqttAsyncClient("tcp://192.168.2.140:1883",publisherId);

        MyMqttCallback mycallback = new MyMqttCallback();
        publisher.setCallback(mycallback);

       IMqttToken token = publisher.connect();
       token.waitForCompletion();

        publisher.publish("/test/java","Hallo pi".getBytes(),0,true);

        publisher.subscribe("/leds/pi",0);



    }
    public static void ledswap(MqttMessage msg) throws InterruptedException {
        var pi4j = Pi4J.newAutoContext();

        var ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED Flasher")
                .address(24)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");

        var led = pi4j.create(ledConfig);

        led.high();
        Thread.sleep(500);
        led.low();
        if (Arrays.equals(msg.getPayload(), "TOGGLE".getBytes())){


            led.high();
            Thread.sleep(500);
            led.low();


        }

    }
}
