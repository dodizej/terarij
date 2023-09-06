package com.example.primjer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlueToothActivity extends AppCompatActivity {
    private void prebaciNaHome(){
        Intent intentBlueTooth=new Intent(BlueToothActivity.this,MainActivity2.class);
        startActivity(intentBlueTooth);
    }
    private void prebacinawifi(){
        Intent intentWiFi=new Intent(BlueToothActivity.this, WiFiActivity.class);
        startActivity(intentWiFi);
    }
    private Button homeActivityButton,wiFiButton,connectButton,searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        homeActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prebaciNaHome();
            }
        });
        wiFiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            prebacinawifi();
            }
        });
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}