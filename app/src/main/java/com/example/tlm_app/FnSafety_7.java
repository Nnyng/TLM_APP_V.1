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

public class FnSafety_7 extends AppCompatActivity {
    private EditText notation,ed_Sign, Sign, position_Signature, position_ed_Signinspector,notatFn7_1,notatFn7_2,notatFn7_3,notatFn7_4,notatFn7_5,notatFn7_6;
    public static TextView datetime,nameDevicefn7,ReadResultFn7;
    private Spinner spinnerDtype,spinnerlocat,spinnergener1,spinnergener2,spinnergener3,spinnergener4,spinnergener5,spinnergener6;
    private Button btn_save_fb7,btnQRScannerFn7;
    private List<FoamFire>foamFires;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn7;
    String DeviceModel, DeviceName;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_7);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn7);
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

        im_back_arrowfn7 = (ImageView) findViewById(R.id.im_back_arrowfn7);
        datetime = (TextView) findViewById(R.id.datefn7);
        ReadResultFn7 = (TextView) findViewById(R.id.tv_ReadResultFn7);
        //spinnerDtype = (Spinner) findViewById(R.id.spinner_fnsafety7_1);
        //spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety7_2);
        spinnergener1 = (Spinner) findViewById(R.id.spinner_fnsafety7_3);
        notatFn7_1 = (EditText) findViewById(R.id.notationFn7_1);
        spinnergener2 = (Spinner) findViewById(R.id.spinner_fnsafety7_4);
        notatFn7_2 = (EditText) findViewById(R.id.notationFn7_2);
        spinnergener3 = (Spinner) findViewById(R.id.spinner_fnsafety7_5);
        notatFn7_3 = (EditText) findViewById(R.id.notationFn7_3);
        spinnergener4 = (Spinner) findViewById(R.id.spinner_fnsafety7_6);
        notatFn7_4 = (EditText) findViewById(R.id.notationFn7_4);
        spinnergener5 = (Spinner) findViewById(R.id.spinner_fnsafety7_7);
        notatFn7_5 = (EditText) findViewById(R.id.notationFn7_5);
        spinnergener6 = (Spinner) findViewById(R.id.spinner_fnsafety7_8);
        notatFn7_6 = (EditText) findViewById(R.id.notationFn7_6);
        // nameDevicefn7 = (TextView) findViewById(R.id.nameDevicefn7);
        btnQRScannerFn7 = (Button) findViewById(R.id.btnQRScannerFn7);

        //Sign = (EditText) findViewById(R.id.signature_fn7);
       // position_Signature = (EditText)findViewById(R.id.positionSignfn7);
       // ed_Sign = (EditText) findViewById(R.id.ed_signspector_fn7);
       // position_ed_Signinspector = (EditText) findViewById(R.id.positionEd_Signfn7);
        //im_back_arrowfn7 = (ImageView) findViewById(R.id.im_back_arrowfn7);
        im_back_arrowfn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_7.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save_fb7 = (Button) findViewById(R.id.btn_save_fb7);
        btn_save_fb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String device = spinnerDtype.getSelectedItem().toString();
                //String location = spinnerlocat.getSelectedItem().toString();
                String generality1 = spinnergener1.getSelectedItem().toString();
                String generality2 = spinnergener2.getSelectedItem().toString();
                String generality3 = spinnergener3.getSelectedItem().toString();
                String generality4 = spinnergener4.getSelectedItem().toString();
                String generality5 = spinnergener5.getSelectedItem().toString();
                String generality6 = spinnergener6.getSelectedItem().toString();
                String note = notation.getText().toString();

//                if(TextUtils.isEmpty(device)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(location)){
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
                if(TextUtils.isEmpty(generality6)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if  (v == btn_save_fb7) {
                    addDataFn7();
                    Intent intent = new Intent(FnSafety_7.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        btnQRScannerFn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_7.this,ScanQRFn_7.class));
            }
        });

        foamFires = new ArrayList<>();
    }

    private void addDataFn7(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn7.setText(DeviceName);

        String date = datetime.getText().toString();
//        String devicetype = spinnerDtype.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String result7 = ReadResultFn7.getText().toString();
        String gener1 = spinnergener1.getSelectedItem().toString();
        String note1 = notatFn7_1.getText().toString();
        String gener2 = spinnergener2.getSelectedItem().toString();
        String note2 = notatFn7_2.getText().toString();
        String gener3 = spinnergener3.getSelectedItem().toString();
        String note3 = notatFn7_3.getText().toString();
        String gener4 = spinnergener4.getSelectedItem().toString();
        String note4 = notatFn7_4.getText().toString();
        String gener5 = spinnergener5.getSelectedItem().toString();
        String note5 = notatFn7_5.getText().toString();
        String gener6 = spinnergener6.getSelectedItem().toString();
        String note6 = notatFn7_6.getText().toString();
      //  String nameDevice = nameDevicefn7.getText().toString();
        String note = notation.getText().toString();
       // String signfn7 = Sign.getText().toString();
       // String posit_Sign = position_Signature.getText().toString();
       // String ed_Signfn7 = ed_Sign.getText().toString();
        //String posit_EdSign = position_ed_Signinspector.getText().toString();

        if(!TextUtils.isEmpty(gener1)){
            String id = firebaseReference.child("CheckFoamFireFn7").push().getKey();
            FoamFire fire7 = new FoamFire();

            fire7.setId_fn7(id);
            fire7.setDate_fn7(date);
//            fire7.setDeviceType_fn7(devicetype);
//            fire7.setLocation_fn7(locat);
            fire7.setResult7(result7);
            fire7.setGeneralityfn_7_1(gener1);
            fire7.setNotationfn7_1(note1);
            fire7.setGeneralityfn_7_2(gener2);
            fire7.setNotationfn7_2(note2);
            fire7.setGeneralityfn_7_3(gener3);
            fire7.setNotationfn7_3(note3);
            fire7.setGeneralityfn_7_4(gener4);
            fire7.setNotationfn7_4(note4);
            fire7.setGeneralityfn_7_5(gener5);
            fire7.setNotationfn7_5(note5);
            fire7.setGeneralityfn_7_6(gener6);
            fire7.setNotationfn7_6(note6);
            //fire7.setManufacturer_fn7(nameDevice);
            //fire7.setSignature_fn7(signfn7);
           // fire7.setPosition_signature_fn7(posit_Sign);
           // fire7.setEd_signspector_fn7(ed_Signfn7);
           // fire7.setPosition_ed_signspector_fn7(posit_EdSign);

            firebaseReference.child("CheckFoamFireFn7").child(id).setValue(fire7);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }

    }

    private void showData(){
        Query query = firebaseReference.child("CheckFoamFireFn7");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foamFires.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    FoamFire fire7 = postSnapshot.getValue(FoamFire.class);
                    foamFires.add(fire7);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
