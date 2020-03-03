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
import com.google.firebase.database.DatabaseReference;

import java.sql.Connection;

public class Login_User extends AppCompatActivity {

    FirebaseAuth auth;
    EditText textLogin, textPassword;
    Button btnLogin;
    ProgressBar pbbar;
    Connection connect;
    String DBUserNameStr,DBPasswordStr,db,ip,UserNameStr,PasswordStr;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        textLogin = (EditText) findViewById(R.id.textLogin);
        textPassword = (EditText) findViewById(R.id.textPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);


        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            //Toast.makeText(getApplicationContext(), "OK, you already logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login_User.this, Fn_Safety.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textLogin.getText().toString();
                final String password = textPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                pbbar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login_User.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pbbar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            if(password.length() < 6) {
                                textPassword.setError(getString(R.string.minimum_password));
                            }else{
                                Toast.makeText(Login_User.this,getString(R.string.auth_failed),Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Intent intent = new Intent(Login_User.this,Fn_Safety.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

}

//       // ip = "192.168.23.135";
//        ip = "TLMSLDB:1433";
//        db = "TLM_SL1_App";
//        DBUserNameStr = "TLMSLBASE_USER";
//        DBPasswordStr = "tlmslbaseuser";
//
////        String dbName = "AndroidDB";
////        String serverip="10.0.2.2";
////        String serverport="1433";
////        String url = "jdbc:sqlserver://"+serverip+"\\14GRAFICALI\\MSSQLSERVER2008"+serverport+";databaseName="+dbName+"";
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login_User.this,Main_Menu.class);
//                startActivity(intent);
//                finish();
//
//                UserNameStr=textLogin.getText().toString();
//                PasswordStr=textPassword.getText().toString();
//
//
//                checklogin check_Login = new checklogin();// this is the Asynctask, which is used to process in background to reduce load on app process
//                check_Login.execute(UserNameStr,PasswordStr);
//            }
//        });
//
//    }
//
//    public class checklogin extends AsyncTask<String,String,String>
//    {
//        String ConnectionResult = "";
//        Boolean isSuccess = false;
//        String UserNameStr=textLogin.getText().toString();
//        String PasswordStr=textPassword.getText().toString();
//
//        @Override
//        protected void onPreExecute()
//        {
//            pbbar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected void onPostExecute(String r)
//        {
//            pbbar.setVisibility(View.GONE);
//            Toast.makeText(Login_User.this, r, Toast.LENGTH_SHORT).show();
//            if(isSuccess)
//            {
//
//                try
//                {
//                    ConnectionStr conStr=new ConnectionStr();  // Connect to database  //
//                    // (String user, String password, String database, String server
//                    connect = conStr.connectionclasss(DBUserNameStr, DBPasswordStr, db, ip);
//                    if (connect == null)
//                    {
//                        ConnectionResult = "Check Your Internet Access!";
//                    }
//                    else
//                    {
//                        // Change below query according to your own database.
//                        String query = "select * from User_Login where id='" + UserNameStr.toString() + "' and password ='"+ PasswordStr.toString() +"'  ";
//                        Statement stmt = connect.createStatement();
//                        ResultSet rs = stmt.executeQuery(query);
//                        if(rs.next())
//                        {
//                            ConnectionResult = "Login successful";
//                            isSuccess=true;
//                            connect.close();
//                        }
//                        else
//                        {
//                            ConnectionResult = "Invalid Credentials!";
//                            isSuccess = false;
//                        }
//                    }
//                }
//                catch (Exception ex)
//                {
//                    isSuccess = false;
//                    ConnectionResult = ex.getMessage();
//                }
//                if(isSuccess=true)
//                {
//                    Toast.makeText(Login_User.this , "Login Successfull" , Toast.LENGTH_LONG).show();
//                    finish();
//                }
//                else{
//                    Toast.makeText(Login_User.this , "Not Success" , Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        }
//
//        @Override
//        protected String doInBackground(String...params)
//        {
//            String usernam = UserNameStr;
//            String passwordd =PasswordStr;
//
//            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
//                ConnectionResult = "Please enter textLogin and Password";
//            else
//            {
//                try
//                {
//                    ConnectionStr conStr=new ConnectionStr();  // Connect to database  //
//                    connect = conStr.connectionclasss(DBUserNameStr, DBPasswordStr, db, ip);
//                    if (connect == null)
//                    {
//                        ConnectionResult = "Check Your Internet Access!";
//                    }
//                    else
//                    {
//                        // Change below query according to your own database.
//                        String query = "select * from User_Login where id='" + usernam.toString() + "' and password ='"+ passwordd.toString() +"'  ";
//                        Statement stmt = connect.createStatement();
//                        ResultSet rs = stmt.executeQuery(query);
//                        if(rs.next())
//                        {
//                            ConnectionResult = "Login successful";
//                            isSuccess=true;
//                            connect.close();
//                        }
//                        else
//                        {
//                            ConnectionResult = "Invalid Credentials!";
//                            isSuccess = false;
//                        }
//                    }
//                }
//                catch (Exception ex)
//                {
//                    isSuccess = false;
//                    ConnectionResult = ex.getMessage();
//                }
//            }
//            return ConnectionResult;
//        }
//    }
















