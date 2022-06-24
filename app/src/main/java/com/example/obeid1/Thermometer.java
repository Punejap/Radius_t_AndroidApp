package com.example.obeid1;


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
