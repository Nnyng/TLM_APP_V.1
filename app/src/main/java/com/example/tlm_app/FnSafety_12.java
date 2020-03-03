package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FnSafety_12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_12);

        ImageView im_back_arrowfn12 = (ImageView) findViewById(R.id.im_back_arrowfn12);
        LinearLayout Fn_DAILY_CHECK_1 = (LinearLayout) findViewById(R.id.Fn_DAILY_CHECK_1);
        LinearLayout Fn_DAILY_CHECK_2 = (LinearLayout) findViewById(R.id.Fn_DAILY_CHECK_2);
        LinearLayout Fn_DAILY_CHECK_3 = (LinearLayout) findViewById(R.id.Fn_DAILY_CHECK_3);
        LinearLayout Fn_DAILY_CHECK_4 = (LinearLayout) findViewById(R.id.Fn_DAILY_CHECK_4);
        LinearLayout Fn_DAILY_CHECK_5 = (LinearLayout) findViewById(R.id.Fn_DAILY_CHECK_5);


        im_back_arrowfn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        Fn_DAILY_CHECK_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,DIESEL_FIRE_PUMP.class);
                startActivity(intent);
            }
        });

        Fn_DAILY_CHECK_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,PORTABLE_FIRE_PUMP.class);
                startActivity(intent);
            }
        });

        Fn_DAILY_CHECK_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,FIRE_PUMP.class);
                startActivity(intent);

            }
        });

        Fn_DAILY_CHECK_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,PORTABLE_GENERATOR_SAKARI.class);
                startActivity(intent);
            }
        });

        Fn_DAILY_CHECK_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_12.this,PORTABLE_GENERATOR_HONDA.class);
                startActivity(intent);
            }
        });
    }
}
