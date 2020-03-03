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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Safety_11_3 extends AppCompatActivity {
    private EditText Sign,ed_Sign,notatFn11_3_1,notatFn11_3_2,notatFn11_3_3;
    public static TextView date,nameDevicefn11_3,ReadResultFn11_3;
    private Spinner spinnerno,spinnerzone,spinnerlocat,spinnermainboard,spinneroffice,spinnerwalkway;
    private Button btn_save_fb11_3,btnQRScannerFn11_3;
    private ImageView im_back_arrowfn11_3;
    private List<Auto3Month> auto3MontList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11_3);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn11_3);
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

        date = (TextView) findViewById(R.id.datefn11_3);
        ReadResultFn11_3 = (TextView) findViewById(R.id.tv_ReadResultFn11_3);
//        spinnerno = (Spinner)findViewById(R.id.spinner_fnsafety11_3_0);
//        spinnerzone = (Spinner)findViewById(R.id.spinner_fnsafety11_3_1);
//        spinnerlocat = (Spinner)findViewById(R.id.spinner_fnsafety11_3_2);
        spinnermainboard = (Spinner) findViewById(R.id.spinner_fnsafety11_3_4);
        notatFn11_3_1  = (EditText) findViewById(R.id.notationFn11_3_1) ;
        spinneroffice = (Spinner) findViewById(R.id.spinner_fnsafety11_3_5);
        notatFn11_3_2 = (EditText) findViewById(R.id.notationFn11_3_2) ;
        spinnerwalkway = (Spinner) findViewById(R.id.spinner_fnsafety11_3_6);
        notatFn11_3_3  = (EditText) findViewById(R.id.notationFn11_3_3) ;
        //nameDevicefn11_3 = (TextView) findViewById(R.id.nameDevicefn11_3);
        //Sign = (EditText) findViewById(R.id.Signaturefn11_3);
        //ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn11_3);
        btnQRScannerFn11_3 = (Button) findViewById(R.id.btnQRScannerFn11_3) ;
        im_back_arrowfn11_3 = (ImageView)findViewById(R.id.im_back_arrowfn11_3);
        im_back_arrowfn11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11_3.this,Safety_11.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb11_3 = (Button)findViewById(R.id.btn_save_fb11_3);
        btn_save_fb11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String no = spinnerno.getSelectedItem().toString();
//                String zone = spinnerzone.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
                String mainboard = spinnermainboard.getSelectedItem().toString();
                String office = spinneroffice.getSelectedItem().toString();
                String walkway = spinnerwalkway.getSelectedItem().toString();
//
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
                if(v == btn_save_fb11_3){
                    addDataFn11_3();
                    Intent intent = new Intent(Safety_11_3.this, Safety_11.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Safety_11_3.this,ScanQRFn_11_3.class));
            }
        });

        auto3MontList = new ArrayList<>();
    }

    private void addDataFn11_3(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn11_3.setText(DeviceName);


        String datetime = date.getText().toString();
        String result11_3 = ReadResultFn11_3.getText().toString();
//        String no = spinnerno.getSelectedItem().toString();
//        String zone = spinnerzone.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String mainboard = spinnermainboard.getSelectedItem().toString();
        String note1 = notatFn11_3_1.getText().toString();
        String office = spinneroffice.getSelectedItem().toString();
        String note2 = notatFn11_3_2.getText().toString();
        String walkway = spinnerwalkway.getSelectedItem().toString();
        String note3 = notatFn11_3_3.getText().toString();
        //String nameDevice = nameDevicefn11_3.getText().toString();
       // String Signfn11_1 = Sign.getText().toString();
        //String ed_Signfn11_1 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(mainboard)){
            String id = firebaseReference.child("CheckAuto3MonthFn11_3").push().getKey();
            Auto3Month autofn11_3 = new Auto3Month();

            autofn11_3.setId_fn11_3(id);
            autofn11_3.setDate_fn11_3(datetime);
            autofn11_3.setResult_fn11_3(result11_3);
//            autofn11_3.setNo_fn11_3(no);
//            autofn11_3.setZone_fn11_3(zone);
//            autofn11_3.setLocat_fn11_3(locat);
            autofn11_3.setTestwork_mainboad_fn11_3(mainboard);
            autofn11_3.setNotation_fn11_3_1(note1);
            autofn11_3.setTestwork_office_fn11_3(office);
            autofn11_3.setNotation_fn11_3_2(note2);
            autofn11_3.setTestwork_walkway_fn11_3(walkway);
            autofn11_3.setNotation_fn11_3_3(note3);
           // autofn11_3.setDeviceName_fn11_3(nameDevice);
            //autofn11_3.setSignature_fn11_3(Signfn11_1);
           // autofn11_3.setEd_Signspector_fn11_3(ed_Signfn11_1);

            firebaseReference.child("CheckAuto3MonthFn11_3").child(id).setValue(autofn11_3);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }

     }

    private void showData(){
        Query query = firebaseReference.child("CheckAuto3MonthFn11_3");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                auto3MontList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Auto3Month manual = postSnapshot.getValue(Auto3Month.class);
                    auto3MontList.add(manual);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
