package com.example;


import org.eclipse.paho.client.mqttv3.MqttException;

public class Sensor {
    public String name;
    public String[] akteure;

    public Sensor(String name,String[] akteure){
        name = this.name;
        akteure =this.akteure;


    }



    //aktuelles AkteurArray wird durch ein neuesArray der größe +1 erstellt mit dem Inhalt der alten Liste + dem neuen Eintrag
    public void addAkteur(String aktID){
        int i = this.akteure.length;

        String[] neuAakteur = new String[i+1];

        for(int j = 0; j<i;i++){
            neuAakteur[j] = this.akteure[j];
        }
        neuAakteur[i] = aktID;

        this.akteure = neuAakteur;
    }

    //Ersetzt die AKteurliste durch eine Lehre Liste
    public void resetAkteur(){
        this.akteure = new String[] {};
    }
    public static void main(String[] args) {

    }

    public void Toggle(){
        int i = this.akteure.length;
        for (int j = 0; j<=i;j++){
            SensorSub.publishToAkteur(this.akteure[j],"toogle");
        }
    }
    public void Toggle(int zahl){
        //Toggle Funktion für unterschiedliche Sensoren überladen
    }

}
