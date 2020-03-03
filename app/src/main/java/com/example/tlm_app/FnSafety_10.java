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

public class FnSafety_10 extends AppCompatActivity {
    private EditText  notation10,Sign10,posit_Sign10,ed_Sign10,posit_ed_Sign10,notatFn10_1,notatFn10_2,notatFn10_3,notatFn10_4,notatFn10_5,notatFn10_6,notatFn10_7;
    private Spinner  spinnernum,spinnerlocat,spinnergenervalve1,spinnergenervalve2,spinnergenervalve3,spinnergenervalve4,spinnergenervalve5,spinnergenerKeyValve1,spinnergenerKeyValve2;
    public static TextView date,nameDevicefn10,ReadResultFn10;
    private Button btn_save_fb10,btnQRScannerFn10;
    private ImageView im_back_arrowfn10;
    private List<ValveFire> valveFireList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_10);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn10);
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

        date = (TextView) findViewById(R.id.datefn10);
//        spinnernum = (Spinner) findViewById(R.id.spinner_fnsafety10_1);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety10_2);
       ReadResultFn10 = (TextView) findViewById(R.id.tv_ReadResultFn10) ;
        spinnergenervalve1 = (Spinner) findViewById(R.id.spinner_fnsafety10_3);
        notatFn10_1 = (EditText) findViewById(R.id.notationFn10_1) ;
        spinnergenervalve2 = (Spinner) findViewById(R.id.spinner_fnsafety10_4);
        notatFn10_2 = (EditText) findViewById(R.id.notationFn10_2) ;
        spinnergenervalve3 = (Spinner) findViewById(R.id.spinner_fnsafety10_5);
        notatFn10_3 = (EditText) findViewById(R.id.notationFn10_3) ;
        spinnergenervalve4 = (Spinner) findViewById(R.id.spinner_fnsafety10_6);
        notatFn10_4 = (EditText) findViewById(R.id.notationFn10_4) ;
        spinnergenervalve5 = (Spinner) findViewById(R.id.spinner_fnsafety10_7);
        notatFn10_5 = (EditText) findViewById(R.id.notationFn10_5) ;
        spinnergenerKeyValve1 = (Spinner) findViewById(R.id.spinner_fnsafety10_8);
        notatFn10_6 = (EditText) findViewById(R.id.notationFn10_6) ;
        spinnergenerKeyValve2 = (Spinner) findViewById(R.id.spinner_fnsafety10_9);
        notatFn10_7 = (EditText) findViewById(R.id.notationFn10_7) ;
       // nameDevicefn10 = (TextView) findViewById(R.id.nameDevicefn10);
//        notation10 = (EditText) findViewById(R.id.notationFn10);
        //Sign10 = (EditText) findViewById(R.id.Signaturefn10);
        //posit_Sign10 = (EditText) findViewById(R.id.position_SignatureFn10);
       // ed_Sign10 = (EditText) findViewById(R.id.ed_Signinspectorfn10);
        //posit_ed_Sign10 = (EditText) findViewById(R.id.position_ed_Signinspectorfn10);
        btnQRScannerFn10 = (Button) findViewById(R.id.btnQRScannerFn10);
        im_back_arrowfn10 = (ImageView) findViewById(R.id.im_back_arrowfn10);
        im_back_arrowfn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_10.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb10 = (Button) findViewById(R.id.btn_save_fb10);
        btn_save_fb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               String num = spinnernum.getSelectedItem().toString();
//               String locat = spinnerlocat.getSelectedItem().toString();
               String genervalve1 = spinnergenervalve1.getSelectedItem().toString();
               String genervalve2 = spinnergenervalve2.getSelectedItem().toString();
               String genervalve3 = spinnergenervalve3.getSelectedItem().toString();
               String genervalve4 = spinnergenervalve4.getSelectedItem().toString();
               String genervalve5 = spinnergenervalve5.getSelectedItem().toString();
               String generKeyvalve1 = spinnergenerKeyValve1.getSelectedItem().toString();
               String generKeyvalve2 = spinnergenerKeyValve2.getSelectedItem().toString();

//                if(TextUtils.isEmpty(num)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(locat)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(genervalve1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(genervalve2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(genervalve3)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(genervalve4)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(genervalve5)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(generKeyvalve1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(generKeyvalve2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(v == btn_save_fb10){
                    addDataFn10();
                    Intent intent = new Intent(FnSafety_10.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_10.this,ScanQRFn_10.class));
            }
        });

        valveFireList = new ArrayList<>();
    }

    private void addDataFn10(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn10.setText(DeviceName);


        String datetime = date.getText().toString();
//        String num = spinnernum.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String result10 = ReadResultFn10.getText().toString();
        String genervalve1 = spinnergenervalve1.getSelectedItem().toString();
        String note1 = notatFn10_1.getText().toString();
        String genervalve2 = spinnergenervalve2.getSelectedItem().toString();
        String note2 = notatFn10_2.getText().toString();
        String genervalve3 = spinnergenervalve3.getSelectedItem().toString();
        String note3 = notatFn10_3.getText().toString();
        String genervalve4 = spinnergenervalve4.getSelectedItem().toString();
        String note4 = notatFn10_4.getText().toString();
        String genervalve5 = spinnergenervalve5.getSelectedItem().toString();
        String note5 = notatFn10_5.getText().toString();
        String generKeyvalve1 = spinnergenerKeyValve1.getSelectedItem().toString();
        String note6 = notatFn10_6.getText().toString();
        String generKeyvalve2 = spinnergenerKeyValve2.getSelectedItem().toString();
        String note7 = notatFn10_7.getText().toString();
        //String nameDevice = nameDevicefn10.getText().toString();
        String note = notation10.getText().toString();
       // String sign = Sign10.getText().toString();
       // String positSign = posit_Sign10.getText().toString();
       // String ed_Sign = ed_Sign10.getText().toString();
       // String posited_Sign = posit_ed_Sign10.getText().toString();


        if(!TextUtils.isEmpty(genervalve1)){
            String id = firebaseReference.child("CheckValveFireFn10").push().getKey();
            ValveFire fire10 = new ValveFire();

            fire10.setId_fn10(id);
            fire10.setDate_fn10(datetime);
//            fire10.setNum_fn10(num);
//            fire10.setLocat_fn10(locat);
            fire10.setResult_fn10(result10);
            fire10.setGeneralityValve_1(genervalve1);
            fire10.setNotation_1(note1);
            fire10.setGeneralityValve_2(genervalve2);
            fire10.setNotation_2(note2);
            fire10.setGeneralityValve_3(genervalve3);
            fire10.setNotation_3(note3);
            fire10.setGeneralityValve_4(genervalve4);
            fire10.setNotation_4(note4);
            fire10.setGeneralityValve_5(genervalve5);
            fire10.setNotation_5(note5);
            fire10.setGeneralityKey_Valve1(generKeyvalve1);
            fire10.setNotation_6(note6);
            fire10.setGeneralityKey_Valve2(generKeyvalve2);
            fire10.setNotation_7(note7);
            //fire10.setManufacturer_fn10(nameDevice);
            fire10.setNotation_fn10(note);
           // fire10.setSignature_fn10(sign);
            //fire10.setPosition_signature_fn10(positSign);
            //fire10.setEd_Signspector_fn10(ed_Sign);
            //fire10.setGetPosition_ed_Signspector_fn10(posited_Sign);

            firebaseReference.child("CheckValveFireFn10").child(id).setValue(fire10);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }

    }

    private  void  showData(){
        Query query = firebaseReference.child("CheckValveFireFn10");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                valveFireList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ValveFire fire10 = postSnapshot.getValue(ValveFire.class);
                    valveFireList.add(fire10);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
