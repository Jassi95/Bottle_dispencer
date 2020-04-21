package com.example.bottledispencer;

public class Bottle {
    private String name;

    private String manufacturer;

    private double total_energy;

    private double price;
    private double size;

    public Bottle(){}

    public Bottle(String x, String manuf, double totE, double p, double sz){
        name = x;
        manufacturer = manuf;
        total_energy=totE;
        price = p;
        size = sz;
    }

    public String getName(){return name;}

    public String getManufacturer(){return manufacturer;}

    public double getEnergy(){return total_energy;}

    public double getSize() {return size;}

    public double getPrice() {return price;}
}
