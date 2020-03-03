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

public class Safety_11_1 extends AppCompatActivity {
    private EditText Sign,ed_Sign,notatFn11_1,notatFn11_2,notatFn11_3,notatFn11_4;
    public static TextView date,nameDevicefn11,ReadResultFn11_1;
    private Spinner spinnerno,spinnerzone,spinnerlocat,spinnergener,spinnermainboard,spinneroffice,spinnerwalkway;
    private Button btn_save_fb11_1,btnQRScannerFn11_1;
    private ImageView im_back_arrowfn11_1;
    private List<ManualEveryMonth> manualEveryMonthList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11_1);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn11_1);
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
      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    private void initInstances() {

        date = (TextView) findViewById(R.id.datefn11_1);
        ReadResultFn11_1 = (TextView) findViewById(R.id.tv_ReadResultFn11_1);
//        spinnerno = (Spinner) findViewById(R.id.spinner_fnsafety11_1_0);
//        spinnerzone = (Spinner) findViewById(R.id.spinner_fnsafety11_1_1);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety11_1_2);
        spinnergener = (Spinner) findViewById(R.id.spinner_fnsafety11_1_3);
        notatFn11_1 = (EditText) findViewById(R.id.notationFn11_1);
        spinnermainboard = (Spinner) findViewById(R.id.spinner_fnsafety11_1_4);
        notatFn11_2 = (EditText) findViewById(R.id.notationFn11_2);
        spinneroffice = (Spinner) findViewById(R.id.spinner_fnsafety11_1_5);
        notatFn11_3 = (EditText) findViewById(R.id.notationFn11_3);
        spinnerwalkway = (Spinner) findViewById(R.id.spinner_fnsafety11_1_6);
        notatFn11_4 = (EditText) findViewById(R.id.notationFn11_4);
        btnQRScannerFn11_1 = (Button) findViewById(R.id.btnQRScannerFn11_1);
        //nameDevicefn11 = (TextView) findViewById(R.id.nameDevicefn11);
        //Sign = (EditText) findViewById(R.id.Signaturefn11_1);
       // ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn11_1) ;
        im_back_arrowfn11_1 = (ImageView) findViewById(R.id.im_back_arrowfn11_1);
        im_back_arrowfn11_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11_1.this,Safety_11.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb11_1 = (Button) findViewById(R.id.btn_save_fb11_1);
        btn_save_fb11_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String no = spinnerno.getSelectedItem().toString();
//                String zone = spinnerzone.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
                String gener = spinnergener.getSelectedItem().toString();
                String mainboard = spinnermainboard.getSelectedItem().toString();
                String office = spinneroffice.getSelectedItem().toString();
                String walkway = spinnerwalkway.getSelectedItem().toString();

//                if(TextUtils.isEmpty(no)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(zone)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(locat)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(gener)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mainboard)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(office)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(walkway)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(v == btn_save_fb11_1){
                    addDataFn11_1();
                    Intent intent = new Intent(Safety_11_1.this,Safety_11.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn11_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Safety_11_1.this,ScanQRFn_11_1.class));
            }
        });

        manualEveryMonthList = new ArrayList<>();
    }

    private void addDataFn11_1(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn11.setText(DeviceName);

        String datetime = date.getText().toString();
        String result11_1 = ReadResultFn11_1.getText().toString();
//        String no = spinnerno.getSelectedItem().toString();
//        String zone = spinnerzone.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String gener = spinnergener.getSelectedItem().toString();
        String note1 = notatFn11_1.getText().toString();
        String mainboard = spinnermainboard.getSelectedItem().toString();
        String note2 = notatFn11_2.getText().toString();
        String office = spinneroffice.getSelectedItem().toString();
        String note3 = notatFn11_3.getText().toString();
        String walkway = spinnerwalkway.getSelectedItem().toString();
        String note4 = notatFn11_4.getText().toString();
       // String nameDevice = nameDevicefn11.getText().toString();
        //String Signfn11_1 = Sign.getText().toString();
        //String ed_Signfn11_1 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(gener)){
            String id = firebaseReference.child("CheckManualEveryMonthFn11_1").push().getKey();
            ManualEveryMonth manual = new ManualEveryMonth();

            manual.setId_fn11_1(id);
            manual.setDate_fn11_1(datetime);
            manual.setResult_fn11_1(result11_1);
//            manual.setNo_fn11_1(no);
//            manual.setZone_fn11_1(zone);
//            manual.setLocat_fn11_1(locat);
            manual.setGenerality_fn11_1(gener);
            manual.setNotation_fn11_1(note1);
            manual.setTestwork_mainboad_fn11_1(mainboard);
            manual.setNotation_fn11_2(note2);
            manual.setTestwork_office_fn11_1(office);
            manual.setNotation_fn11_3(note3);
            manual.setTestwork_walkway_fn11_1(walkway);
            manual.setNotation_fn11_4(note4);
           //manual.setDeviceName_fn11(nameDevice);
           // manual.setSignature_fn11_1(Signfn11_1);
            //manual.setEd_Signspector_fn11_1(ed_Signfn11_1);

            firebaseReference.child("CheckManualEveryMonthFn11_1").child(id).setValue(manual);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckManualEveryMonthFn11_1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                manualEveryMonthList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ManualEveryMonth manual = postSnapshot.getValue(ManualEveryMonth.class);
                    manualEveryMonthList.add(manual);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
