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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Safety_11_2 extends AppCompatActivity {
    private EditText Sign,ed_Sign,notatFn11_2_1;
    public static TextView date,nameDevicefn11_2,ReadResultFn11_2;
    private Spinner spinnerno,spinnerzone,spinnerlocat,spinnergener;
    private Button btn_save_fb11_2,btnQRScannerFn11_2;
    private List<AutoEveryMonth> autoEveryMonths;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn11_2;
    String DeviceModel, DeviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_11_2);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn11_2);
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

        date = (TextView) findViewById(R.id.datefn11_2);
        ReadResultFn11_2 = (TextView) findViewById(R.id.tv_ReadResultFn11_2);
//        spinnerno = (Spinner) findViewById(R.id.spinner_fnsafety11_2_0);
//        spinnerzone = (Spinner) findViewById(R.id.spinner_fnsafety11_2_1);
//        spinnerlocat = (Spinner) findViewById(R.id.spinner_fnsafety11_2_2);
        spinnergener = (Spinner) findViewById(R.id.spinner_fnsafety11_2_3);
        notatFn11_2_1 = (EditText) findViewById(R.id.notationFn11_2_1);
        //nameDevicefn11_2 = (TextView) findViewById(R.id.nameDevicefn11_2);
        //Sign = (EditText) findViewById(R.id.Signaturefn11_2);
        //ed_Sign = (EditText) findViewById(R.id.ed_Signinspectorfn11_2);
        btnQRScannerFn11_2 = (Button) findViewById(R.id.btnQRScannerFn11_2);
        im_back_arrowfn11_2 = (ImageView) findViewById(R.id.im_back_arrowfn11_2);
        im_back_arrowfn11_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Safety_11_2.this,Safety_11.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_fb11_2 = (Button) findViewById(R.id.btn_save_fb11_2);
        btn_save_fb11_2.setOnClickListener(new View.OnClickListener() {
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
                if(v == btn_save_fb11_2){
                    addDataFn11_2();
                    Intent intent = new Intent(Safety_11_2.this, Safety_11.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        btnQRScannerFn11_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Safety_11_2.this,ScanQRFn_11_2.class));
            }
        });

        autoEveryMonths = new ArrayList<>();
    }

    private void addDataFn11_2(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn11_2.setText(DeviceName);

        String datetime = date.getText().toString();
        String result11_2 = ReadResultFn11_2.getText().toString();
//        String no = spinnerno.getSelectedItem().toString();
//        String zone = spinnerzone.getSelectedItem().toString();
//        String locat = spinnerlocat.getSelectedItem().toString();
        String gener = spinnergener.getSelectedItem().toString();
        String note = notatFn11_2_1.getText().toString();
       // String nameDevice = nameDevicefn11_2.getText().toString();
        //String Signfn11_2 = Sign.getText().toString();
       // String ed_Signfn11_2 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(gener)){
            String id = firebaseReference.child("CheckAutoEveryMonthFn11_2").push().getKey();
            AutoEveryMonth autofn11_2 = new AutoEveryMonth();

            autofn11_2.setId_fn11_2(id);
            autofn11_2.setDate_fn11_2(datetime);
            autofn11_2.setResult_fn11_2(result11_2);
//            autofn11_2.setNo_fn11_2(no);
//            autofn11_2.setZone_fn11_2(zone);
//            autofn11_2.setLocat_fn11_2(locat);
            autofn11_2.setGenerality_fn11_2(gener);
            autofn11_2.setNote_fn11_2_1(note);
          //  autofn11_2.setDeviceName_fn11_2(nameDevice);
           // autofn11_2.setSignature_fn11_2(Signfn11_2);
           // autofn11_2.setSignature_fn11_2(ed_Signfn11_2);

            firebaseReference.child("CheckAutoEveryMonthFn11_2").child(id).setValue(autofn11_2);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckAutoEveryMonthFn11_2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                autoEveryMonths.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    AutoEveryMonth autofn11_2 = postSnapshot.getValue(AutoEveryMonth.class);
                    autoEveryMonths.add(autofn11_2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
