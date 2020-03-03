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

public class FnSafety_8 extends AppCompatActivity {
    private EditText notation, ed_Sign, Sign, positfn8, posit_ed_Signfn8;
   public static TextView datetime, nameDevicefn8,ReadResultFn8;
    private Spinner spinnernumdevice, spinnerDtype, spinnerlocat, spinnergener;
    private Button btn_save_fb8,btnQRScannerFn8;
    private ImageView im_back_arrowfn8;
    private List<FireFightingFn8> fightingFn8List;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_8);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn8);
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

        datetime = (TextView) findViewById(R.id.datefn8);
        ReadResultFn8 = (TextView) findViewById(R.id.tv_ReadResultFn8);
//        spinnernumdevice = (Spinner) findViewById(R.id.spinner_fnsafety8_1);
//        spinnerDtype = (Spinner) findViewById(R.id.spinner_fnsafety8_2);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety8_3);
        spinnergener = (Spinner) findViewById(R.id.spinner_fnsafety8_4);
        //nameDevicefn8 = (TextView) findViewById(R.id.nameDevicefn8);
        notation = (EditText) findViewById(R.id.notationFnSafety8);
        //Sign = (EditText) findViewById(R.id.SignatureFn8);
        //positfn8 = (EditText) findViewById(R.id.position_Signaturefn8);
        //ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn8);
        //posit_ed_Signfn8 = (EditText) findViewById(R.id.position_ed_Signinspectorfn8);
        btnQRScannerFn8 = (Button) findViewById(R.id.btnQRScannerFn8);
        im_back_arrowfn8 = (ImageView) findViewById(R.id.im_back_arrowfn8);
        im_back_arrowfn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_8.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb8 = (Button) findViewById(R.id.btn_save_fb8);
        btn_save_fb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String num = spinnernumdevice.getSelectedItem().toString();
//                String devicetpye = spinnerDtype.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
                String generality = spinnergener.getSelectedItem().toString();
                String note = notation.getText().toString();

//                if (TextUtils.isEmpty(num)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(devicetpye)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(locat)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(generality)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (v == btn_save_fb8) {
                    addDataFn8();
                    Intent intent = new Intent(FnSafety_8.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        btnQRScannerFn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_8.this,ScanQRFn_8.class));
            }
        });

        fightingFn8List = new ArrayList<>();
    }

    private void addDataFn8(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn8.setText(DeviceName);

        String date = datetime.getText().toString();
        String result8 = ReadResultFn8.getText().toString();
//        String numdevice = spinnernumdevice.getSelectedItem().toString();
//        String Devicetype = spinnerDtype.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String gener = spinnergener.getSelectedItem().toString();
        //String nameDevice = nameDevicefn8.getText().toString();
        String note = notation.getText().toString();
        //String Signfn8 = Sign.getText().toString();
        //String posit_Sign = posit_ed_Signfn8.getText().toString();
        //String ed_Signfn8 = ed_Sign.getText().toString();
       // String posit_EdSign = posit_ed_Signfn8.getText().toString();

        if(!TextUtils.isEmpty(gener)){
            String id = firebaseReference.child("CheckFireFightingFn8").push().getKey();
            FireFightingFn8 fire8 = new FireFightingFn8();

            fire8.setId_fn8(id);
            fire8.setDate_fn8(date);
            fire8.setResult_fn8(result8);
//            fire8.setNumdevice_fn8(numdevice);
//            fire8.setDeviceType_fn8(Devicetype);
//            fire8.setLocation_fn8(locat);
            fire8.setGenerality_fn8(gener);
            //fire8.setManufacturer_fn8(nameDevice);
            fire8.setNotation_fn8(note);
           // fire8.setSignature_fn8(Signfn8);
           // fire8.setPosition_signature_fn8(posit_Sign);
            //fire8.setEd_signspector_fn8(ed_Signfn8);
           // fire8.setPosition_ed_signspector_fn8(posit_EdSign);

             firebaseReference.child("CheckFireFightingFn8").child(id).setValue(fire8);
             Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }

    }

    private  void  showData(){
        Query query = firebaseReference.child("CheckFireFightingFn8");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fightingFn8List.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    FireFightingFn8 fire8 = postSnapshot.getValue(FireFightingFn8.class);
                    fightingFn8List.add(fire8);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}