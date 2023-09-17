package com.example.primjer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WiFiActivity extends AppCompatActivity {


    private void switchToBluetooth(){
        Intent intentBlueTooth=new Intent(WiFiActivity.this,BlueToothActivity.class);
        startActivity(intentBlueTooth);
    }
    private void switchToHome(){
        Intent intentBlueTooth=new Intent(WiFiActivity.this,MainActivity2.class);
        startActivity(intentBlueTooth);
    }
    private Button homeButton,bluetoothbutton,connectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToHome();

            }
        });
        bluetoothbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToBluetooth();
            }
        });
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}