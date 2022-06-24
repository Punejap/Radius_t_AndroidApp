package com.example.Radius_t_AndroidApp;


public class Thermometer {
    String name;

    public Thermometer(String name){
        this.name = name;
    }

    public String getName(){
        if(name==null){name = "default";}
        return name;
    }
}
