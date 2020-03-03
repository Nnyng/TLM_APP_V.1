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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Safety_11_4 extends AppCompatActivity {
    private EditText Sign,ed_Sign,notatFn11_4_1;
    public static TextView date,nameDevicefn11_4,ReadResultFn11_4;
    private Spinner spinnerno,spinnerzone,spinnerlocat,spinnergener;
    private Button btn_save_fb11_4,btnQRScannerFn11_4;
    private List<AutoTreaterEveryMonth> autoTreaterEveryMonthList;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn11_4;
    String DeviceModel, DeviceName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11_4);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn11_4);
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

    private void initFirebase(){

        firebaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initInstances() {

        date = (TextView) findViewById(R.id.datefn11_4);
        ReadResultFn11_4 = (TextView) findViewById(R.id.tv_ReadResultFn11_4);
//        spinnerno = (Spinner) findViewById(R.id.spinner_fnsafety11_4_0);
//        spinnerzone = (Spinner) findViewById(R.id.spinner_fnsafety11_4_1);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety11_4_2);
        spinnergener = (Spinner) findViewById(R.id.spinner_fnsafety11_4_3);
        notatFn11_4_1 = (EditText) findViewById(R.id.notationFn11_4_1);
        //nameDevicefn11_4 = (TextView) findViewById(R.id.nameDevicefn11_4);
        //Sign = (EditText) findViewById(R.id.Signaturefn11_4);
        //ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn11_4);
        btnQRScannerFn11_4 = (Button) findViewById(R.id.btnQRScannerFn11_4);
        im_back_arrowfn11_4 = (ImageView) findViewById(R.id.im_back_arrowfn11_4);
        im_back_arrowfn11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Safety_11_4.this, Safety_11.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb11_4 = (Button) findViewById(R.id.btn_save_fb11_4);
        btn_save_fb11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String no = spinnerno.getSelectedItem().toString();
//                String zone = spinnerzone.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
                String gener = spinnergener.getSelectedItem().toString();

//                if(TextUtils.isEmpty(no)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(zone)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(locat)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
//                    return;
//                }
                if(TextUtils.isEmpty(gener)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(v == btn_save_fb11_4){
                    addDataFn11_4();
                    Intent intent = new Intent(Safety_11_4.this, Safety_11.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        btnQRScannerFn11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Safety_11_4.this,ScanQRFn_11_4.class));
            }
        });

        autoTreaterEveryMonthList = new ArrayList<>();
    }

    private void addDataFn11_4(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn11_4.setText(DeviceName);

        String datetime = date.getText().toString();
        String result11_4 = ReadResultFn11_4.getText().toString();
//        String no = spinnerno.getSelectedItem().toString();
//        String zone = spinnerzone.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String gener = spinnergener.getSelectedItem().toString();
        String note = notatFn11_4_1.getText().toString();
        //String nameDevice = nameDevicefn11_4.getText().toString();
        //String Signfn11_4 = Sign.getText().toString();
       // String ed_Signfn11_4 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(gener)){
            String id = firebaseReference.child("CheckAutoTreaterEveryMonthFn11_4").push().getKey();
            AutoTreaterEveryMonth autofn11_4 = new AutoTreaterEveryMonth();

            autofn11_4.setId_fn11_4(id);
            autofn11_4.setDate_fn11_4(datetime);
            autofn11_4.setResult_fn11_4(result11_4);
//            autofn11_4.setNo_fn11_4(no);
//            autofn11_4.setZone_fn11_4(zone);
//            autofn11_4.setLocat_fn11_4(locat);
            autofn11_4.setGenerality_fn11_4(gener);
            autofn11_4.setNotation_fn11_4(note);
           // autofn11_4.setDeviceName_fn11_4(nameDevice);
           // autofn11_4.setSignature_fn11_4(Signfn11_4);
           // autofn11_4.setSignature_fn11_4(ed_Signfn11_4);

            firebaseReference.child("CheckAutoTreaterEveryMonthFn11_4").child(id).setValue(autofn11_4);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckAutoTreaterEveryMonthFn11_4");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                autoTreaterEveryMonthList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    AutoTreaterEveryMonth autofn11_4 = postSnapshot.getValue(AutoTreaterEveryMonth.class);
                    autoTreaterEveryMonthList.add(autofn11_4);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
