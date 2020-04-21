package com.example.bottledispencer;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BottleDispenser {
    private int bottles;
    private double money;
    private String receipt;
    private ArrayList<Bottle> storage;

    private static BottleDispenser bd = new BottleDispenser();


    public static BottleDispenser getInstance(){
        return bd;
    }

    private BottleDispenser() {

        bottles = 5;

        money = 0;



        /* Initialize the array */

        storage = new ArrayList <Bottle>();

        // Add Bottle-objects to the array
        /* legacy method
        for(int i = 0;i<bottles;i++) {

            // Use the default constructor to create new Bottles
        	Bottle temp = new Bottle("Pepsi Max","Pepsi", 0.3,1.8,0.5);
            storage.add(temp) ;
        }*/

        //new storage
        Bottle temp = new Bottle("Pepsi Max","Pepsi", 0.3,1.8,0.5);
        storage.add(temp) ;

        temp = new Bottle("Pepsi Max","Pepsi", 0.3,2.2,1.5);
        storage.add(temp) ;

        temp = new Bottle("Coca-Cola Zero","Coca-Cola", 0,2.0,0.5);
        storage.add(temp) ;

        temp = new Bottle("Coca-Cola Zero","Coca-Cola", 0,2.5,1.5);
        storage.add(temp) ;

        temp = new Bottle("Fanta Zero","Fanta", 0,1.95,0.5);
        storage.add(temp) ;

    }





    public String addMoney(double m) {
        String s="";
        money += m;
        if(m>0){
        s = "Klink! Added more money!";}
        return s;
    }

    public String getMoney(){

        String s;
        NumberFormat formatter = NumberFormat.getInstance(new Locale("fi","FI"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        s = (formatter.format(money)  +" €");
        return s;

    }

    public ArrayList<String> getStorage(){

        ArrayList<String> bottles = new ArrayList<String>();

        String temp ="";
        for(Bottle bottle : this.storage){
            temp = temp.concat(bottle.getName()+" ");
            temp = temp.concat(Double.toString(bottle.getSize()));
            System.out.println(temp);
            bottles.add(temp);
            temp  = "";
        }
        System.out.print(bottles);
        return bottles;
    }


    public String buyBottle(int decission) {

        String s;



        if (money >= storage.get(decission).getPrice()  && bottles > 0) {
            bottles -= 1;
            money -=storage.get(decission).getPrice();
            s=("KACHUNK! "+ storage.get(decission).getName() +" came out of the dispenser!");
            this.makeReceipt(decission);
            storage.remove(decission);
        }
        else{
            s = "Add money first!";}

        return s;
    }



    public String returnMoney() {
        String s;
        NumberFormat formatter = NumberFormat.getInstance(new Locale("fi","FI"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        s = ("Klink klink. Money came out! You got "+formatter.format(money)  +"€ back");
        money = 0;
        return s;
    }

    public String listStorage() {
        int i = 1;
        String s = "";
        for(Bottle bottle : storage) {
            s = s.concat(i+". Name: "+bottle.getName()+"\n");
            s = s.concat("\tSize: "+bottle.getSize()+"\tPrice: "+bottle.getPrice()+"\n");
            i++;
        }
        if (i==1){s ="Machine is empty";}
        return s;
    }
    private void makeReceipt(int i){
        receipt = "";
        receipt = receipt.concat(". Name: "+ storage.get(i).getName()+"\n");
        receipt = receipt.concat("\tSize: "+storage.get(i).getSize()+"\tPrice: "+storage.get(i).getPrice()+"\n");
        System.out.println(receipt);
    }

    public String getReceipt(){
        return receipt;
    }

}
