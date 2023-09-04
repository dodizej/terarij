package com.example.primjer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;



public class aktivnost2 extends AppCompatActivity {
private Button pumpaON,pumpaOFF,difuzorOn,difuzorOff,SvjetloOn,SvjetloOff,SvjetlinaGore,SvjetlinaDolje;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivnost2_layout);
        pumpaON=(Button) findViewById(R.id.pumpaON);
        pumpaOFF=(Button) findViewById(R.id.pumpaOff);
        difuzorOn=(Button) findViewById(R.id.difuzorON);
        difuzorOff=(Button) findViewById(R.id.difuzorOff);
        SvjetloOn=(Button) findViewById(R.id.SvjetloON);
        SvjetloOff=(Button) findViewById(R.id.SvjetloOFF);
        SvjetlinaDolje=(Button) findViewById(R.id.SvjetlinaDolje);
        SvjetlinaGore=(Button) findViewById(R.id.SvjetlinaGore);
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








    }


}