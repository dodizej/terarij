package com.example.primjer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button pumpaON,pumpaOFF,difuzorOn,difuzorOff,SvjetloOn,SvjetloOff,SvjetlinaGore,SvjetlinaDolje;
    private Button wifiButton,bluetoothButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pumpaON=(Button) findViewById(R.id.pumpaON);
        pumpaOFF=(Button) findViewById(R.id.pumpaOff);
        difuzorOn=(Button) findViewById(R.id.difuzorON);
        difuzorOff=(Button) findViewById(R.id.difuzorOff);
        SvjetloOn=(Button) findViewById(R.id.SvjetloON);
        SvjetloOff=(Button) findViewById(R.id.SvjetloOFF);
        SvjetlinaDolje=(Button) findViewById(R.id.SvjetlinaDolje);
        SvjetlinaGore=(Button) findViewById(R.id.SvjetlinaGore);
        wifiButton=(Button) findViewById(R.id.toWifi);
        bluetoothButton=(Button) findViewById(R.id.toBlueTooth);

        pumpaON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        pumpaOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        difuzorOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        difuzorOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SvjetloOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SvjetloOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SvjetlinaGore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SvjetlinaDolje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prebacinawifi();
            }
        });
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prebacinabluetooth();
            }
        });

    }
    private void prebacinawifi(){
        Intent intentWiFi=new Intent(MainActivity2.this, WiFiActivity.class);
        startActivity(intentWiFi);
    }
    private void prebacinabluetooth(){
        Intent intentBlueTooth=new Intent(MainActivity2.this,BlueToothActivity.class);
        startActivity(intentBlueTooth);
    }
}
