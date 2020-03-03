package com.example.tlm_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentQR, new QrFragment())
                    .commit();
        }
    }
}
