package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Safety_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11);

        ImageView im_back_arrowfn11 = (ImageView) findViewById(R.id.im_back_arrowfn11);
        LinearLayout Fn_FireAlarm1 = (LinearLayout) findViewById(R.id.Fn_FireAlarm1);
        LinearLayout Fn_FireAlarm2 = (LinearLayout) findViewById(R.id.Fn_FireAlarm2);
        LinearLayout Fn_FireAlarm3 = (LinearLayout) findViewById(R.id.Fn_FireAlarm3);
        LinearLayout Fn_FireAlarm4 = (LinearLayout) findViewById(R.id.Fn_FireAlarm4);
        LinearLayout Fn_FireAlarm5 = (LinearLayout) findViewById(R.id.Fn_FireAlarm5);


        im_back_arrowfn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        Fn_FireAlarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this, Safety_11_1.class);
                startActivity(intent);
            }
        });

        Fn_FireAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this, Safety_11_2.class);
                startActivity(intent);
            }
        });

        Fn_FireAlarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this, Safety_11_3.class);
                startActivity(intent);
            }
        });

        Fn_FireAlarm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this, Safety_11_4.class);
                startActivity(intent);
            }
        });

        Fn_FireAlarm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11.this, Safety_11_5.class);
                startActivity(intent);
            }
        });
    }
}
