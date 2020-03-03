package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LinearLayout linearLayout_textLogin = (LinearLayout)findViewById(R.id.linearLayout_textLogin);
        LinearLayout linearLayout_textQrLogin= (LinearLayout)findViewById(R.id.linearLayout_textQrLogin);

//        linearLayout_textLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, TestFirebase.class);
//                startActivity(intent);
//            }
//        });

//        linearLayout_textLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, TestDependentSpinner.class);
//                startActivity(intent);
//            }
//        });


        linearLayout_textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, User_Login.class);
                startActivity(intent);
            }
        });




//
//        linearLayout_textLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) {
//                Intent intent = new Intent(Login.this, Login_User.class);
//                startActivity(intent);
//            }
//        });

        linearLayout_textQrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ScanQRMLKit.class);
                startActivity(intent);
            }
        });

//                linearLayout_textQrLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,MainResultQR.class);
//                startActivity(intent);
//            }
//        });
//

    }

}
