package com.example.bottledispencer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        TextView screen,mscreen,cmscreen;
        Spinner spinner;
        SeekBar sb;
        BottleDispenser machine = BottleDispenser.getInstance(); // singleton!
        ArrayList<String> list = new ArrayList<String>();
        Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //spinner things
        context = MainActivity.this;
        spinner = (Spinner)findViewById(R.id.SodaSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list = machine.getStorage());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        screen = findViewById(R.id.Screen);
        mscreen = findViewById(R.id.MoneyView);
        cmscreen = findViewById(R.id.MoneyChooser);
        //Seekbar
        sb = (SeekBar) findViewById(R.id.MoneySeekbar);
        sb.setMax(30);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                String s = Integer.toString(progress);
                s = s.concat(" €");
                cmscreen.setText(s);
            }
        });
    }

    public void listStorage(View v){
        String s = machine.listStorage();
        screen.setText(s);
    }

    public void updatePurchase(View v){// updates after purchase

        //updates spinner
        Spinner spinner = (Spinner)findViewById(R.id.SodaSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list = machine.getStorage());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        mscreen.setText(machine.getMoney());
    }

    public void purchase(View v){
        int index;
        String s;
        index = spinner.getSelectedItemPosition();
        if(index != -1)
        {System.out.println(index);

        s = machine.buyBottle(index);
        screen.setText(s);
        mscreen.setText(machine.getMoney());
        updatePurchase(null);}
        else
            screen.setText("Machine empty");
    }

    public void addMoney(View v){ //TODO
        String s;
        s = machine.addMoney(sb.getProgress());
        screen.setText(s);
        mscreen.setText(machine.getMoney());
        sb.setProgress(0);

    }

    public void returnMoney(View V){
        String s;
        s = machine.returnMoney();
        screen.setText(s);
    }

    public void writeFile(View v){
        String fileName = "";
        // Assign filename
        fileName = "Receipt.txt";
        System.out.println(fileName);

        String s= machine.getReceipt();
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE)); //tähän nimi
            ows.write(s);
            ows.close();
            System.out.println(s);
        }
        catch (IOException e) {
            Log.e("IOException","Wrong input");
        } finally {
            System.out.println("Write successful");
        }

    }

}

