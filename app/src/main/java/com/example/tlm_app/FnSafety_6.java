package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

public class FnSafety_6 extends AppCompatActivity {
    private EditText ed_Sign, Sign,notatat6_1,notatat6_2,notatat6_3,notatat6_4,notatat6_5;
    private Spinner spinner_locat, spinner_TypeDevice, spinner_generat1, spinner_generat2, spinner_generat3, spinner_generat4, spinner_generat5;
    public static TextView datetime, nameDevicefn6,ReadResultFn6;
    private Button btn_save6,btnQRScannerFn6;
    private List<EyeShower> eyeShowers;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn6;
    String DeviceModel, DeviceName;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_6);

        initInstances();
        initFirebase();
        showData();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.datefn6);
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



        datetime = (TextView)findViewById(R.id.datefn6);
//        spinner_locat = (Spinner) findViewById(R.id.spinner_fnsafety6_1);
//        spinner_TypeDevice = (Spinner) findViewById(R.id.spinner_fnsafety6_2);
        ReadResultFn6 = (TextView) findViewById(R.id.tv_ReadResultFn6);
        spinner_generat1 = (Spinner) findViewById(R.id.spinner_fnsafety6_3);
        notatat6_1= (EditText) findViewById(R.id.notationFn6_1);
        notatat6_2 = (EditText) findViewById(R.id.notationFn6_2);
        notatat6_3 = (EditText) findViewById(R.id.notationFn6_3);
        notatat6_4 = (EditText) findViewById(R.id.notationFn6_4);
        notatat6_5 = (EditText) findViewById(R.id.notationFn6_5);
        spinner_generat2 = (Spinner) findViewById(R.id.spinner_fnsafety6_4);
        spinner_generat3 = (Spinner) findViewById(R.id.spinner_fnsafety6_5);
        spinner_generat4 = (Spinner) findViewById(R.id.spinner_fnsafety6_6);
        spinner_generat5 = (Spinner) findViewById(R.id.spinner_fnsafety6_7);
        //nameDevicefn6 = (TextView) findViewById(R.id.nameDevicefn6);
        //Sign = (EditText) findViewById(R.id.signature_fn6);
        //ed_Sign = (EditText) findViewById(R.id.ed_signspector_fn6);
        btnQRScannerFn6 = (Button) findViewById(R.id.btnQRScannerFn6);
        im_back_arrowfn6 = (ImageView) findViewById(R.id.im_back_arrowfn6);
        im_back_arrowfn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_6.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save6 = (Button) findViewById(R.id.btn_save_fb6);
        btn_save6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String location = spinner_locat.getSelectedItem().toString();
//                String device = spinner_TypeDevice.getSelectedItem().toString();
                String generality1 = spinner_generat1.getSelectedItem().toString();
                String generality2 = spinner_generat2.getSelectedItem().toString();
                String generality3 = spinner_generat3.getSelectedItem().toString();
                String generality4 = spinner_generat4.getSelectedItem().toString();
                String generality5 = spinner_generat5.getSelectedItem().toString();

//                if(TextUtils.isEmpty(location)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(device)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(generality1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generality2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generality3)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generality4)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(generality5)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if  (v == btn_save6) {
                    addDataFn6();
                    Intent intent = new Intent(FnSafety_6.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        btnQRScannerFn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_6.this,ScanQRFn_6.class));
            }
        });

        eyeShowers = new ArrayList<>();
    }


    private void  addDataFn6(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn6.setText(DeviceName);

        String date = datetime.getText().toString();
//        String locat = spinner_locat.getSelectedItem().toString();
//        String typeDevice = spinner_TypeDevice.getSelectedItem().toString();
        String result6 = ReadResultFn6.getText().toString();
        String generat1 = spinner_generat1.getSelectedItem().toString();
        String note1 = notatat6_1.getText().toString();
        String generat2 = spinner_generat2.getSelectedItem().toString();
        String note2 = notatat6_2.getText().toString();
        String generat3 = spinner_generat3.getSelectedItem().toString();
        String note3 = notatat6_3.getText().toString();
        String generat4 = spinner_generat4.getSelectedItem().toString();
        String note4 = notatat6_4.getText().toString();
        String generat5 = spinner_generat5.getSelectedItem().toString();
        String note5 = notatat6_5.getText().toString();
       // String nameDevice = nameDevicefn6.getText().toString();
        //String Signature = Sign.getText().toString();
        //String Ed_signspector = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(generat1)){
            String id = firebaseReference.child("CheckEyeShowerFn6").push().getKey();
            EyeShower eye6 = new EyeShower();

            eye6.setId_fn6(id);
            eye6.setDate_fn6(date);
            eye6.setResult_fn6(result6);
           // eye6.setDevicetype_fn6(typeDevice);
            eye6.setGeneralityfn6_1(generat1);
            eye6.setNotation1(note1);
            eye6.setGeneralityfn6_2(generat2);
            eye6.setNotation2(note2);
            eye6.setGeneralityfn6_3(generat3);
            eye6.setNotation3(note3);
            eye6.setGeneralityfn6_4(generat4);
            eye6.setNotation4(note4);
            eye6.setGeneralityfn6_5(generat5);
            eye6.setNotation5(note5);
           // eye6.setManufacturer_fn6(nameDevice);
           // eye6.setSignature_fn6(Signature);
           // eye6.setEd_signspector_fn6(Ed_signspector);

            firebaseReference.child("CheckEyeShowerFn6").child(id).setValue(eye6);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckEyeShowerFn6");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eyeShowers.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    EyeShower eye6 = postSnapshot.getValue(EyeShower.class);
                    eyeShowers.add(eye6);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
