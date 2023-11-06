package com.example.terarij.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.terarij.States;
import com.example.terarij.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        // TODO - Use Switch Compat

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch difusorPower = binding.difusorPower;
        difusorPower.setOnCheckedChangeListener(buildListener(States.DIFUSOR_POWER));

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch pumpPower = binding.pumpPower;
        pumpPower.setOnCheckedChangeListener(buildListener(States.PUMP_POWER));

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch ledPower = binding.ledStripPower;
        ledPower.setOnCheckedChangeListener(buildListener(States.LED_POWER));

        final SeekBar ledInten = binding.ledInten;

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public CompoundButton.OnCheckedChangeListener buildListener(String stateName) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendHTTPPost(buttonView, isChecked, stateName);
            }
        };
    }

    public void sendHTTPPost(View view, boolean isChecked, String stateName) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(2000, TimeUnit.MILLISECONDS)
                        .build();

                String json = String.format("{ \"%s\" : %s }"
                        , stateName
                        , Boolean.toString(isChecked));

                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

                Request request = new Request.Builder()
                        .post(body)
                        .url("http://192.168.8.117:80/api")
                        .build();

                String bodyStr = "";
                try (Response response = client.newCall(request).execute()) {

                    if (response.body() != null) {
                        bodyStr = response.body().byteStream().toString();
                    } else {
                        bodyStr = "Empty body received.";
                    }

                } catch (Exception e) {
                    bodyStr = "HTTP request failed. " + e.getMessage();
                } finally {
                    Snackbar.make(view, bodyStr, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        thread.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}