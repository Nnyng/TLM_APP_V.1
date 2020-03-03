package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivityTestDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_dialog);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.myContent,new MainFragment()).commit();
        }

    }
}
