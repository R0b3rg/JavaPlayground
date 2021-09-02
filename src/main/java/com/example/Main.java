/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Main {

     static List<Sensor> sensorList = new ArrayList<Sensor>();


    private static final int PIN_LED = 22; // PIN 15 = BCM 22
    private static final Console console = new Console();

    /**
     * @param args the command line arguments
     */
    public void main(String[] args) throws Exception {

        Sensor sensor01 = new Sensor("sensor01", new String[]{"akt01", "akt02"});

        sensorList.add(sensor01);

        //new MqttTest_01().main(args);
        new SensorSub().main(args);
    }

    public static int getListSize(){
        return sensorList.size();
    }

    public static String getSensorname(int position){
        return sensorList.get(position).name;
    }


    //Todo: Kann man generics nutzen um den msg in form von verschiedenen Datentypen zu übermitteln alternativ die Funktion überladen,oder ein Format für Sensorantworten festlegen
    public static void sensorActivate (String path, int msg){
        //path splitten sensor/sensorname
        String sensorname = path.substring(6);
        for(int i = 0; i < sensorList.size();i++){
            if(sensorList.get(i).name.equals(sensorname)){
                sensorList.get(i).toggle(msg);
            }
        }
    }


}
