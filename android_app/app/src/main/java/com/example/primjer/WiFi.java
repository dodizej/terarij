package com.example.primjer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WiFi extends AppCompatActivity {

    private OkHttpClient httpClient;
    private void switchToBluetooth(){
        Intent intentBlueTooth=new Intent(WiFi.this, BlueTooth.class);
        startActivity(intentBlueTooth);
    }
    private void switchToHome(){
        Intent intentBlueTooth=new Intent(WiFi.this, Home.class);
        startActivity(intentBlueTooth);
    }
    private Button homeButton,bluetoothbutton,connectButton;
    private EditText password,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        homeButton=(Button) findViewById(R.id.Home);
        bluetoothbutton=(Button) findViewById(R.id.goToBluetooth);
        connectButton=(Button) findViewById(R.id.connectToWiFi);
        password=findViewById(R.id.passwordEditText);
        name=findViewById(R.id.wifiEditText);

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
            String nameWiFi=name.getText().toString();
            String passwordWiFi=password.getText().toString();
                try {
                    sendPasswordToServer(passwordWiFi,nameWiFi);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    private void sendPasswordToServer(String password, String name) throws JSONException {
        // Create a JSON string containing the password
        JSONObject json=new JSONObject();
        json.put("passwordWiFi",password);
        json.put("nameWiFi",name);
        // Set the content type to JSON
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // Create a request body with the JSON data
        RequestBody requestBody = RequestBody.create(String.valueOf(json), JSON);
        // Define the HTTP request
        Request request = new Request.Builder()
                .url("your_server_url_here")
                .post(requestBody)
                .build();
        // Execute the request asynchronously
        httpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WiFi.this, "Failed to send password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Handle a successful response from the server
                    String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WiFi.this, "Password sent successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Handle an unsuccessful response from the server
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WiFi.this, "Failed to send password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
