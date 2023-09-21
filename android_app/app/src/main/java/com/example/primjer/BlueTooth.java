package com.example.primjer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BlueTooth extends AppCompatActivity {
    private void switchToHome(){
        Intent intentBlueTooth=new Intent(BlueTooth.this, Home.class);
        startActivity(intentBlueTooth);
    }
    private void switchToWifi(){
        Intent intentWiFi=new Intent(BlueTooth.this, WiFi.class);
        startActivity(intentWiFi);
    }
    private Button homeActivityButton,wiFiButton,connectButton,searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        homeActivityButton=(Button) findViewById(R.id.Home);
        wiFiButton=(Button) findViewById(R.id.goToWifi);
        connectButton=(Button) findViewById(R.id.connectToBluetooth);
        searchButton=(Button) findViewById(R.id.scanBluetooth);


        homeActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToHome();
            }
        });
        wiFiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToWifi();
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