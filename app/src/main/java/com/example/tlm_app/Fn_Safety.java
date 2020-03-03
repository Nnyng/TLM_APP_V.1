package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Fn_Safety extends AppCompatActivity {

    private Toolbar toolbar;

    FirebaseAuth auth;
    ProgressBar pbbar;
    FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety);

        ImageView im_back_arrow = (ImageView)findViewById(R.id.im_back_arrow);
//        RelativeLayout safetylocation = (RelativeLayout) findViewById(R.id.locatsafety);
        RelativeLayout safety1 = (RelativeLayout)findViewById(R.id.safety1);
        RelativeLayout safety2 = (RelativeLayout)findViewById(R.id.safety2);
        RelativeLayout safety3 = (RelativeLayout)findViewById(R.id.safety3);
        RelativeLayout safety4 = (RelativeLayout)findViewById(R.id.safety4);
        RelativeLayout safety5 = (RelativeLayout)findViewById(R.id.safety5);
        RelativeLayout safety6 = (RelativeLayout)findViewById(R.id.safety6);
        RelativeLayout safety7 = (RelativeLayout)findViewById(R.id.safety7);
        RelativeLayout safety8 = (RelativeLayout)findViewById(R.id.safety8);
        RelativeLayout safety9 = (RelativeLayout)findViewById(R.id.safety9);
        RelativeLayout safety10 = (RelativeLayout)findViewById(R.id.safety10);
        RelativeLayout safety11 = (RelativeLayout)findViewById(R.id.safety11);
        RelativeLayout safety12 = (RelativeLayout)findViewById(R.id.safety12);
        RelativeLayout logOut = (RelativeLayout)findViewById(R.id.logOut);

        auth = FirebaseAuth.getInstance();

        im_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this, Main_Menu.class);
                startActivity(intent);
                finish();
            }
        });

//        safetylocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Fn_Safety.this,FnSafety_Location.class);
//                startActivity(intent);
//            }
//        });

        safety1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_1.class);
                startActivity(intent);
            }
        });

        safety2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,Fn_Safety_2.class);
                startActivity(intent);
            }
        });

        safety3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_3.class);
                startActivity(intent);
            }
        });

        safety4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_4.class);
                startActivity(intent);
            }
        });

        safety5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_5.class);
                startActivity(intent);
            }
        });

        safety6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_6.class);
                startActivity(intent);
            }
        });

        safety7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_7.class);
                startActivity(intent);
            }
        });

        safety8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_8.class);
                startActivity(intent);
            }
        });

        safety9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_9.class);
                startActivity(intent);
            }
        });

        safety10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_10.class);
                startActivity(intent);
            }
        });

        safety11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,Safety_11.class);
                startActivity(intent);
            }
        });

        safety12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety.this,FnSafety_12.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fn_Safety.this,"Logged out!", Toast.LENGTH_LONG).show();
                signOut();
            }
        });
    }

    public void signOut(){
        auth.signOut();
        Intent intent = new Intent(Fn_Safety.this, User_Login.class);
        startActivity(intent);
        finish();
    }
}
