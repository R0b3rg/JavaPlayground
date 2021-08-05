package com.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInput;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import org.eclipse.paho.client.mqttv3.*;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class MqttTest_01 {


    /**********************Klassenvariablen  GPIO's definiren*********************/
    private static Integer PIN_LED = 24;
    private static DigitalState LED_STATE = DigitalState.LOW;;
    private static Integer PIN_Button = 23;

    /**Context für RaspberryPi, AutoContext ggf. ersetzen***/
    private static Context cont = Pi4J.newAutoContext();

    /**LED am PI **/
    private static DigitalOutputConfigBuilder ledConfig = DigitalOutput.newConfigBuilder(cont)
            .id("led")
            .name("LED Flasher")
            .address(PIN_LED)
            .shutdown(DigitalState.LOW)
            .initial(LED_STATE)
            .provider("pigpio-digital-output");

    private static DigitalOutput led = cont.create(ledConfig);

    /**Button am PI**/
    private static DigitalInputConfigBuilder config = DigitalInput.newConfigBuilder(cont)
            //.id("my-digital-input")
            .address(PIN_Button)
            .pull(PullResistance.PULL_DOWN);
   private static DigitalInputProvider digitalInputProvider = cont.provider("pigpio-digital-input");

    private static DigitalInput input = digitalInputProvider.create(config);





    public void main(String[] args) throws Exception{



            // get a Digital Input I/O provider from the Pi4J context


        //var input2 =digitalInputProvider.create(buttonConfig);


        String publisherId = "LaptopDavid";
        MqttAsyncClient publisher = new MqttAsyncClient("tcp://192.168.2.140:1883",publisherId);

        MyMqttCallback mycallback = new MyMqttCallback();
        publisher.setCallback(mycallback);

       IMqttToken token = publisher.connect();
       token.waitForCompletion();


        publisher.subscribe("/leds/pi",0);

        input.addListener(event -> {
            System.out.println("Button pressed!");
            try {
                publisher.publish("/leds/esp8266","TOGGLE".getBytes(),0,true);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

    }


    public static void ledswap(MqttMessage msg) throws InterruptedException {

            if(led.equals(DigitalState.HIGH)){
                led.low();
            }else{
                led.high();
            }

    }
}
