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

public class Home extends AppCompatActivity {
    private OkHttpClient httpClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Button pumpON, pumpOFF, diffuserOn, diffuserOff, lightOn, lightOff, brightnessUp, brightnessDown;
    private Button wifiButton,bluetoothButton;
    private String deviceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pumpON =(Button) findViewById(R.id.pumpON);
        pumpOFF =(Button) findViewById(R.id.pumpOff);
        diffuserOn =(Button) findViewById(R.id.diffuserON);
        diffuserOff =(Button) findViewById(R.id.diffuserOff);
        lightOn =(Button) findViewById(R.id.lightOn);
        lightOff =(Button) findViewById(R.id.lightOff);
        brightnessDown =(Button) findViewById(R.id.brightnessDown);
        brightnessUp =(Button) findViewById(R.id.brightnessUp);
        wifiButton=(Button) findViewById(R.id.toWifi);
        bluetoothButton=(Button) findViewById(R.id.toBlueTooth);
        pumpON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="pumpOn";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR PUMP IS NOT ON!";
                String successMessage="PUMP IS ON!";
                checkPins(pass,errorMessage,successMessage);
            }
        });
        pumpOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="pumpOff";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR PUMP IS STILL WORKING!";
                String successMessage="PUMP IS OFF!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        diffuserOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="diffuserOn";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR DIFFUSER IS NOT ON!";
                String successMessage="DIFFUSER IS ON!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        diffuserOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="diffuserOff";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR DIFFUSER IS STILL WORKING!";
                String successMessage="PUMP IS OFF!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        lightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="lightOn";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR LIGHTS ARE NOT ON!";
                String successMessage="LIGHTS ARE ON!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        lightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="lightOff";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR LIGHTS ARE STILL ON!";
                String successMessage="LIGHTS ARE OFF!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        brightnessUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="brightnessUp";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR BRIGHTNESS DIDNT GO UP!";
                String successMessage="BRIGHTNESS WENT UP!";
                checkPins(pass,errorMessage,successMessage);

            }
        });
        brightnessDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass="brightnessDown";
                try {
                    sendPasswordToServer(pass);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String errorMessage="ERROR BRIGHTNESS DIDNT GO DOWN!";
                String successMessage="BRIGHTNESS WENT DOWN!";
                checkPins(pass,errorMessage,successMessage);
            }
        });
        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToWifi();
            }
        });
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToBluetooth();
            }
        });

    }
    private void switchToWifi(){
        Intent intentWiFi=new Intent(Home.this, WiFi.class);
        startActivity(intentWiFi);
    }
    private void switchToBluetooth(){
        Intent intentBlueTooth=new Intent(Home.this, BlueTooth.class);
        startActivity(intentBlueTooth);
    }

    private void sendPasswordToServer(String password) throws JSONException {
        // Create a JSON string containing the password
        JSONObject json=new JSONObject();
        json.put("password",password);
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
                        Toast.makeText(Home.this, "Failed to send password", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Home.this, "Password sent successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Handle an unsuccessful response from the server
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home.this, "Failed to send password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void retrievePasswordFromServer(final PasswordCallback callback) {
        // Define the HTTP request to retrieve the JSON data
        Request request = new Request.Builder()
                .url("your_server_url_here")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home.this, "Failed to retrieve password", Toast.LENGTH_SHORT).show();
                    }
                });

                // Invoke the callback with null when there's an error
                callback.onPasswordRetrieved(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        // Parse the JSON response
                        String responseData = response.body().string();
                        JSONObject json = new JSONObject(responseData);

                        // Extract the password from the JSON
                        String password = json.getString("password");

                        // Now you have the password in the 'password' variable
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Home.this, "Retrieved Password: " + password, Toast.LENGTH_SHORT).show();
                            }
                        });

                        // Invoke the callback with the retrieved password
                        callback.onPasswordRetrieved(password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        // Handle JSON parsing error here

                        // Invoke the callback with null when there's a JSON parsing error
                        callback.onPasswordRetrieved(null);
                    }
                } else {
                    // Handle an unsuccessful response from the server
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home.this, "Failed to retrieve password", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Invoke the callback with null when the response is not successful
                    callback.onPasswordRetrieved(null);
                }
            }
        });
    }

    // Define a callback interface
    interface PasswordCallback {
        void onPasswordRetrieved(String password);
    }


    private void checkPins(String pass,String errorMessage,String successMessage){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                retrievePasswordFromServer(new PasswordCallback() {
                    @Override
                    public void onPasswordRetrieved(String password) {
                        if (password != null) {
                            deviceState = password;
                            if(deviceState.equals(pass)){
                                System.out.println(successMessage);
                            }else{
                                System.out.println(errorMessage);
                            }
                        }
                    }
                });
            }
        }, 500);
    }
}