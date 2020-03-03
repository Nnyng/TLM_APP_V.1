package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_Login extends AppCompatActivity {

    private EditText input_email;
    private EditText input_Password;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button Signup;
    private Button Login;
    private Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(User_Login.this, Main_Menu.class));
            finish();
        }


        input_email = (EditText) findViewById(R.id.email);
        input_Password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.pbbar);
        Signup = (Button) findViewById(R.id.btn_signup);
        Login = (Button) findViewById(R.id.btn_login);
        Reset = (Button) findViewById(R.id.btn_reset_password);

        auth = FirebaseAuth.getInstance();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Login.this, SignUpActivity.class));
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Login.this, Reset_Password.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                final String password = input_Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

           auth.signInWithEmailAndPassword(email, password) .addOnCompleteListener(User_Login.this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   // If sign in fails, display a message to the user. If sign in succeeds
                   // the auth state listener will be notified and logic to handle the
                   // signed in user can be handled in the listener.
                   progressBar.setVisibility(View.GONE);
                   if (!task.isSuccessful()) {
                       // there was an error
                       if (password.length() < 6) {
                           input_Password.setError(getString(R.string.password_length));
                       } else {
                           Toast.makeText( User_Login.this, getString(R.string.failed_auth), Toast.LENGTH_LONG).show();
                       }
                   } else {
                       Intent intent = new Intent(User_Login.this, Fn_Safety.class);
                       startActivity(intent);
                       finish();
                   }
               }
           });

            }
        });
    }
}
