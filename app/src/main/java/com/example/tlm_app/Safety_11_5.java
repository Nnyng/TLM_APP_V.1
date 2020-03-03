package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class Safety_11_5 extends AppCompatActivity {
    private EditText Sign, ed_Sign,notatFn11_5_1;
    public static TextView date,nameDevicefn11_5,ReadResultFn11_5;
    private Spinner spinnerno, spinnerzone, spinnerlocat, spinnermainboard;
    private Button btn_save_fb11_5,btnQRScannerFn11_5;
    private ImageView im_back_arrowfn11_5;
    private List<AutoTreater3Month> autoTreater3MonthList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11_5);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn11_5);
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

        date = (TextView) findViewById(R.id.datefn11_5);
        ReadResultFn11_5 = (TextView) findViewById(R.id.tv_ReadResultFn11_5) ;
//        spinnerno = (Spinner) findViewById(R.id.spinner_fnsafety11_5_0);
//        spinnerzone = (Spinner) findViewById(R.id.spinner_fnsafety11_5_1);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety11_5_2);
        spinnermainboard = (Spinner) findViewById(R.id.spinner_fnsafety11_5_3);
        notatFn11_5_1 = (EditText) findViewById(R.id.notationFn11_5_1);
        //nameDevicefn11_5 = (TextView) findViewById(R.id.nameDevicefn11_5);
        //Sign = (EditText) findViewById(R.id.Signaturefn11_5);
        //ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn11_5);
        btnQRScannerFn11_5 = (Button) findViewById(R.id.btnQRScannerFn11_5);
        im_back_arrowfn11_5 = (ImageView) findViewById(R.id.im_back_arrowfn11_5);
        im_back_arrowfn11_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11_5.this, Safety_11.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb11_5 = (Button) findViewById(R.id.btn_save_fb11_5);
        btn_save_fb11_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String no = spinnerno.getSelectedItem().toString();
//                String zone = spinnerzone.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
                String mainboard = spinnermainboard.getSelectedItem().toString();

//                if (TextUtils.isEmpty(no)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(zone)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(locat)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if (TextUtils.isEmpty(mainboard)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (v == btn_save_fb11_5) {
                    addDataFn11_5();
                    Intent intent = new Intent(Safety_11_5.this, Safety_11.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnQRScannerFn11_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Safety_11_5.this,ScanQRFn_11_5.class));
            }
        });

        autoTreater3MonthList = new ArrayList<>();
    }

    private void addDataFn11_5() {

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn11_5.setText(DeviceName);


        String datetime = date.getText().toString();
        String result11_5 = ReadResultFn11_5.getText().toString();
//        String no = spinnerno.getSelectedItem().toString();
//        String zone = spinnerzone.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String mainboard = spinnermainboard.getSelectedItem().toString();
        String note = notatFn11_5_1.getText().toString();
       // String nameDevice = nameDevicefn11_5.getText().toString();
       // String Signfn11_5 = Sign.getText().toString();
        //String ed_Signfn11_5 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(mainboard)){
            String id = firebaseReference.child("CheckAutoTreater3MonthFn11_5").push().getKey();
            AutoTreater3Month autofn11_5 = new AutoTreater3Month();

            autofn11_5.setId_fn11_5(id);
            autofn11_5.setDate_fn11_5(datetime);
            autofn11_5.setResult_fn11_5(result11_5);
//            autofn11_5.setZone_fn11_5(zone);
//            autofn11_5.setLocat_fn11_5(locat);
            autofn11_5.setTestWork_mainboard_fn11_5(mainboard);
            autofn11_5.setNotationfn11_5(note);
            //autofn11_5.setDeviceName_fn11_5(nameDevice);
            //autofn11_5.setSignature_fn11_5(Signfn11_5);
            //autofn11_5.setEd_Signspector_fn11_5(ed_Signfn11_5);

            firebaseReference.child("CheckAutoTreater3MonthFn11_5").child(id).setValue(autofn11_5);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckAutoTreater3MonthFn11_5");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                autoTreater3MonthList.clear();
                for (DataSnapshot postSnapshot  : dataSnapshot.getChildren()){
                    AutoTreater3Month autofn11_5 = postSnapshot.getValue(AutoTreater3Month.class);
                    autoTreater3MonthList.add(autofn11_5);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}