package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

public class FnSafety_9 extends AppCompatActivity {
    private EditText notation9, ed_Sign9, Sign9,notationFn9_1,notationFn9_2,notationFn9_3,notationFn9_4,notationFn9_5;
    public static TextView datetime9,nameDevicefn9,ReadResultFn9;
    private Spinner spinnerdevicefn9,spinnerlocatfn9,spinnergener1fn9,spinnergener2fn9,spinnergener3fn9,spinnergener4fn9,spinnergener5fn9;
    private Button btn_save_fb9,btnQRScannerFn9;
    private List<AutomaticFireCO2> automaticFireCO2List;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn9;
    String DeviceModel, DeviceName;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_9);


        initInstances();
        initFirebase();
        showData();

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.datefn9);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }


    private void initFirebase() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();
    }


    private void initInstances() {

        datetime9 = (TextView) findViewById(R.id.datefn9);
//        spinnerdevicefn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_1);
//        spinnerlocatfn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_2);
        ReadResultFn9 = (TextView) findViewById(R.id.tv_ReadResultFn9) ;
        spinnergener1fn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_3);
        notationFn9_1 = (EditText) findViewById(R.id.notationFnSafety9_1) ;
        spinnergener2fn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_4);
        notationFn9_2 = (EditText) findViewById(R.id.notationFnSafety9_2) ;
        spinnergener3fn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_5);
        notationFn9_3 = (EditText) findViewById(R.id.notationFnSafety9_3) ;
        spinnergener4fn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_6);
        notationFn9_4 = (EditText) findViewById(R.id.notationFnSafety9_4) ;
        spinnergener5fn9 = (Spinner) findViewById(R.id.spinner_fnsafety9_7);
        notationFn9_5 = (EditText) findViewById(R.id.notationFnSafety9_5) ;
        //nameDevicefn9 = (TextView) findViewById(R.id.nameDevicefn9);
        btnQRScannerFn9 =(Button) findViewById(R.id.btnQRScannerFn9);
        //Sign9 = (EditText) findViewById(R.id.Signaturefn9);
        //ed_Sign9 = (EditText) findViewById(R.id.ed_Signinspectorfn9);
        im_back_arrowfn9 = (ImageView) findViewById(R.id.im_back_arrowfn9);
        im_back_arrowfn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_9.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb9 = (Button) findViewById(R.id.btn_save_fb9);
        btn_save_fb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String devicetype = spinnerdevicefn9.getSelectedItem().toString();
//                String locat = spinnerlocatfn9.getSelectedItem().toString();
                String generat1 = spinnergener1fn9.getSelectedItem().toString();
                String generat2 = spinnergener2fn9.getSelectedItem().toString();
                String generat3 = spinnergener3fn9.getSelectedItem().toString();
                String generat4 = spinnergener4fn9.getSelectedItem().toString();
                String generat5 = spinnergener5fn9.getSelectedItem().toString();

//                if(TextUtils.isEmpty(devicetype)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(locat)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(generat1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generat2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generat3)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generat4)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generat5)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(v == btn_save_fb9){
                    addDataFn9();
                    Intent intent = new Intent(FnSafety_9.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_9.this,ScanQRFn_9.class));
            }
        });

        automaticFireCO2List = new ArrayList<>();

    }

    private void addDataFn9(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn9.setText(DeviceName);

        String date = datetime9.getText().toString();
//        String Dtype = spinnerdevicefn9.getSelectedItem().toString();
//        String locat = spinnerlocatfn9.getSelectedItem().toString();
        String result9 = ReadResultFn9.getText().toString();
        String gener1 = spinnergener1fn9.getSelectedItem().toString();
        String note1 = notationFn9_1.getText().toString();
        String gener2 = spinnergener2fn9.getSelectedItem().toString();
        String note2 = notationFn9_2.getText().toString();
        String gener3 = spinnergener3fn9.getSelectedItem().toString();
        String note3 = notationFn9_3.getText().toString();
        String gener4 = spinnergener4fn9.getSelectedItem().toString();
        String note4 = notationFn9_4.getText().toString();
        String gener5 = spinnergener5fn9.getSelectedItem().toString();
        String note5 = notationFn9_5.getText().toString();
        //String nameDevice = nameDevicefn9.getText().toString();
        String note = notation9.getText().toString();
        //String Signfn9 = Sign9.getText().toString();
        //String ed_Signfn9 = ed_Sign9.getText().toString();

        if(!TextUtils.isEmpty(gener1)){
            String id = firebaseReference.child("CheckAutomaticFireCO2Fn9").push().getKey();
            AutomaticFireCO2 fire9 = new AutomaticFireCO2();

            fire9.setId_fn9(id);
            fire9.setDate_fn9(date);
//            fire9.setDeviceType_fn9(Dtype);
//            fire9.setLocat_fn9(locat);
            fire9.setResult_fn9(result9);
            fire9.setGeneralityfn9_1(gener1);
            fire9.setNotation9_1(note1);
            fire9.setGeneralityfn9_2(gener2);
            fire9.setNotation9_2(note2);
            fire9.setGeneralityfn9_3(gener3);
            fire9.setNotation9_3(note3);
            fire9.setGeneralityfn9_4(gener4);
            fire9.setNotation9_4(note4);
            fire9.setGeneralityfn9_5(gener5);
            fire9.setNotation9_5(note5);
            //fire9.setManufacturer_fn9(nameDevice);

            //fire9.setSignature_fn9(Signfn9);
            //fire9.setEd_signspector_fn9(ed_Signfn9);

            firebaseReference.child("CheckAutomaticFireCO2Fn9").child(id).setValue(fire9);
            Toast.makeText(this, "Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();

        }

    }

    private void showData(){
        Query query = firebaseReference.child("CheckAutomaticFireCO2Fn9");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                automaticFireCO2List.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    AutomaticFireCO2 fire9 = postSnapshot.getValue(AutomaticFireCO2.class);
                    automaticFireCO2List.add(fire9);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}











