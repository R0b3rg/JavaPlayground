package com.example;

import com.diozero.devices.*;
import com.pi4j.io.spi.*;

//import  com.diozero.*;
public class DiozeroTestRC522 {

    public static void main(String[] args) {



        MFRC522 chip = new MFRC522(1,25);


                chip.setAntennaOn(true);
    }
}
