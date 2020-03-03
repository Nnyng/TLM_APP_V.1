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

public class DIESEL_FIRE_PUMP extends AppCompatActivity {
    private TextView date,nameDeviceDiesel;
    private Spinner spinnermoter1,spinnermoter2,spinnermoter3,spinnermoter4,spinnermoter5,spinnermoter6,spinnerJockey1,spinnerJockey2,spinnerJockey3,spinnerJockey4,spinnerJockey5,spinnerJockey6,
                    spinnerBattery1,spinnerBattery2,spinnerBattery3,spinnerBattery4,spinnerBattery5,spinnerBattery6,spinnerWater1,spinnerWater2,spinnerWater3,spinnerWater4,
                    spinnerControl1,spinnerControl2,spinnerControl3,spinnerControl4,spinnerTestWork1,spinnerTestWork2,spinnerTestWork3,spinnerTestWork4,spinnerTestWork5,spinnerFirePump1,spinnerFirePump2,spinnerFirePump3,spinnerFirePump4;
    private EditText notetationmoter1,notetationmoter2,notetationmoter3,notetationmoter4,notetationmoter5,notetationmoter6,notetationJockey1,notetationJockey2,notetationJockey3,notetationJockey4,
                     notetationBattery1,notetationBattery2,notetationBattery3,notetationBattery4,notetationBattery5,notetationBattery6,notetationWater1,notetationWater2,notetationWater3,notetationWater4,
                     notetationControl1,notetationControl2,notetationControl3,notetationTestWork1,notetationFirePump1,notetationFirePump2,notetationFirePump3,notetationFirePump4,Sign,ed_Sign;
    private Button btn_save_diesel_firepump;
    private ImageView im_back_arrow_Diesel_FirePump;
    private List<Diesel> dieselList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diesel__fire__pump);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn12_Dieselfirepump);
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

        date = (TextView) findViewById(R.id.datefn12_Dieselfirepump);
        spinnermoter1 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor1);
        notetationmoter1 = (EditText) findViewById(R.id.notationfn12_1_1);
        spinnermoter2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor2);
        notetationmoter2 = (EditText) findViewById(R.id.notationfn12_1_2);
        spinnermoter3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor3);
        notetationmoter3 = (EditText) findViewById(R.id.notationfn12_1_3);
        spinnermoter4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor4);
        notetationmoter4 = (EditText) findViewById(R.id.notationfn12_1_4);
        spinnermoter5 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor5);
        notetationmoter5 = (EditText) findViewById(R.id.notationfn12_1_5);
        spinnermoter6 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Motor6);
        notetationmoter6 = (EditText) findViewById(R.id.notationfn12_1_6);
        spinnerJockey1 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey1);
        spinnerJockey2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey2);
        notetationJockey1 = (EditText) findViewById(R.id.notationfn12_1_7);
        spinnerJockey3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey3);
        notetationJockey2 = (EditText) findViewById(R.id.notationfn12_1_8);
        spinnerJockey4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey4);
        notetationJockey3 = (EditText) findViewById(R.id.notationfn12_1_9);
        spinnerJockey5 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey5);
        spinnerJockey6 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_jockey6);
        notetationJockey4 = (EditText) findViewById(R.id.notationfn12_1_10);
        spinnerBattery1 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_ฺBattery1);
        notetationBattery1 = (EditText)findViewById(R.id.notationfn12_1_11);
        spinnerBattery2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_ฺBattery2);
        notetationBattery2 = (EditText)findViewById(R.id.notationfn12_1_12);
        spinnerBattery3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Battery3);
        notetationBattery3 = (EditText)findViewById(R.id.notationfn12_1_13);
        spinnerBattery4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Battery4);
        notetationBattery4 = (EditText)findViewById(R.id.notationfn12_1_14);
        spinnerBattery5 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Battery5);
        notetationBattery5 = (EditText)findViewById(R.id.notationfn12_1_15);
        spinnerBattery6 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Battery6);
        notetationBattery6 = (EditText)findViewById(R.id.notationfn12_1_16);
        spinnerWater1  = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Water1);
        notetationWater1 = (EditText) findViewById(R.id.notationfn12_1_17);
        spinnerWater2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Water2);
        notetationWater2 = (EditText) findViewById(R.id.notationfn12_1_18);
        spinnerWater3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Water3);
        notetationWater3 = (EditText) findViewById(R.id.notationfn12_1_19);
        spinnerWater4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Water4);
        notetationWater4 = (EditText) findViewById(R.id.notationfn12_1_20);
        spinnerControl1 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Electronic_Control1);
        notetationControl1 = (EditText) findViewById(R.id.notationfn12_1_21);
        spinnerControl2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Electronic_Control2);
        spinnerControl3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Electronic_Control3);
        notetationControl2 = (EditText) findViewById(R.id.notationfn12_1_22);
        spinnerControl4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Electronic_Control4);
        notetationControl3 = (EditText) findViewById(R.id.notationfn12_1_23);
        spinnerTestWork1 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_TestWork1);
        spinnerTestWork2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_TestWork2);
        spinnerTestWork3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_TestWork3);
        spinnerTestWork4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_TestWork4);
        spinnerTestWork5 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_TestWork5);
        notetationTestWork1 = (EditText) findViewById(R.id.notationfn12_1_24) ;
        spinnerFirePump1 = (Spinner)  findViewById(R.id.spinner_fnsafety12_DieselFirepump_Room_FirePump1);
        notetationFirePump1 = (EditText) findViewById(R.id.notationfn12_1_25);
        spinnerFirePump2 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Room_FirePump2);
        notetationFirePump2 = (EditText) findViewById(R.id.notationfn12_1_26);
        spinnerFirePump3 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Room_FirePump3);
        notetationFirePump3 = (EditText) findViewById(R.id.notationfn12_1_27);
        spinnerFirePump4 = (Spinner) findViewById(R.id.spinner_fnsafety12_DieselFirepump_Room_FirePump4);
        notetationFirePump4 = (EditText) findViewById(R.id.notationfn12_1_28);
        //nameDeviceDiesel = (TextView) findViewById(R.id.nameDeviceDiesel);
        //Sign = (EditText) findViewById(R.id.Signaturefn12_1) ;
        //ed_Sign = (EditText) findViewById(R.id.ed_Signspectorfn12_1);
        im_back_arrow_Diesel_FirePump = (ImageView) findViewById(R.id.im_back_arrow_Diesel_FirePump);
        im_back_arrow_Diesel_FirePump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DIESEL_FIRE_PUMP.this,FnSafety_12.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_diesel_firepump = (Button) findViewById(R.id.btn_save_diesel_firepump);
        btn_save_diesel_firepump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moter1 = spinnermoter1.getSelectedItem().toString();
                String moter2 = spinnermoter2.getSelectedItem().toString();
                String moter3 = spinnermoter3.getSelectedItem().toString();
                String moter4 = spinnermoter4.getSelectedItem().toString();
                String moter5 = spinnermoter5.getSelectedItem().toString();
                String motor6 = spinnermoter6.getSelectedItem().toString();
                String Jockey1 = spinnerJockey1.getSelectedItem().toString();
                String Jockey2 = spinnerJockey2.getSelectedItem().toString();
                String Jockey3 = spinnerJockey3.getSelectedItem().toString();
                String Jockey4 = spinnerJockey4.getSelectedItem().toString();
                String Jockey5 = spinnerJockey5.getSelectedItem().toString();
                String Jockey6 = spinnerJockey6.getSelectedItem().toString();
                String Battery1 = spinnerBattery1.getSelectedItem().toString();
                String Battery2 = spinnerBattery2.getSelectedItem().toString();
                String Battery3 = spinnerBattery3.getSelectedItem().toString();
                String Battery4 = spinnerBattery4.getSelectedItem().toString();
                String Battery5 = spinnerBattery5.getSelectedItem().toString();
                String Battery6 = spinnerBattery6.getSelectedItem().toString();
                String Water1 = spinnerWater1.getSelectedItem().toString();
                String Water2 = spinnerWater2.getSelectedItem().toString();
                String Water3 = spinnerWater3.getSelectedItem().toString();
                String Water4 = spinnerWater4.getSelectedItem().toString();
                String Control1 = spinnerControl1.getSelectedItem().toString();
                String Control2 = spinnerControl2.getSelectedItem().toString();
                String Control3 = spinnerControl3.getSelectedItem().toString();
                String Control4 = spinnerControl4.getSelectedItem().toString();
                String TestWork1 = spinnerTestWork1.getSelectedItem().toString();
                String TestWork2 = spinnerTestWork2.getSelectedItem().toString();
                String TestWork3 = spinnerTestWork3.getSelectedItem().toString();
                String TestWork4 = spinnerTestWork4.getSelectedItem().toString();
                String TestWork5 = spinnerTestWork5.getSelectedItem().toString();
                String FirePump1 = spinnerFirePump1.getSelectedItem().toString();
                String FirePump2 = spinnerFirePump2.getSelectedItem().toString();
                String FirePump3 = spinnerFirePump3.getSelectedItem().toString();
                String FirePump4 = spinnerFirePump4.getSelectedItem().toString();

                if (TextUtils.isEmpty(moter1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(moter2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(moter3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(moter4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(moter5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(motor6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Jockey6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Battery6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Water1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Water2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Water3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Water4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Control1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Control2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Control3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(Control4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(TestWork1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(TestWork2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(TestWork3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(TestWork4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(TestWork5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(FirePump1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(FirePump2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(FirePump3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(FirePump4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (v == btn_save_diesel_firepump) {
                    addDataFn12_1();
                    Intent intent = new Intent(DIESEL_FIRE_PUMP.this, FnSafety_12.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        dieselList = new ArrayList<>();

    }

    private void addDataFn12_1(){


//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDeviceDiesel.setText(DeviceName);

        String datetime = date.getText().toString();
        String moter1 = spinnermoter1.getSelectedItem().toString();
        String notemoter1 = notetationmoter1.getText().toString();
        String moter2 =  spinnermoter2.getSelectedItem().toString();
        String notemoter2 = notetationmoter2.getText().toString();
        String moter3 =  spinnermoter3.getSelectedItem().toString();
        String notemoter3 = notetationmoter3.getText().toString();
        String moter4 =  spinnermoter4.getSelectedItem().toString();
        String notemoter4 = notetationmoter4.getText().toString();
        String moter5 =  spinnermoter5.getSelectedItem().toString();
        String notemoter5 = notetationmoter5.getText().toString();
        String moter6 =  spinnermoter6.getSelectedItem().toString();
        String notemoter6 = notetationmoter6.getText().toString();
        String jockey1 = spinnerJockey1.getSelectedItem().toString();
        String jockey2 = spinnerJockey2.getSelectedItem().toString();
        String notejockey1 = notetationJockey1.getText().toString();
        String jockey3 = spinnerJockey3.getSelectedItem().toString();
        String notejockey2 = notetationJockey2.getText().toString();
        String jockey4 = spinnerJockey4.getSelectedItem().toString();
        String notejockey3 = notetationJockey3.getText().toString();
        String jockey5 = spinnerJockey5.getSelectedItem().toString();
        String jockey6 = spinnerJockey6.getSelectedItem().toString();
        String notejockey4 = notetationJockey4.getText().toString();
        String Battery1 = spinnerBattery1.getSelectedItem().toString();
        String noteBattery1 = notetationBattery1.getText().toString();
        String Battery2 = spinnerBattery2.getSelectedItem().toString();
        String noteBattery2 = notetationBattery2.getText().toString();
        String Battery3 = spinnerBattery3.getSelectedItem().toString();
        String noteBattery3 = notetationBattery3.getText().toString();
        String Battery4 = spinnerBattery4.getSelectedItem().toString();
        String noteBattery4 = notetationBattery4.getText().toString();
        String Battery5 = spinnerBattery5.getSelectedItem().toString();
        String noteBattery5 = notetationBattery5.getText().toString();
        String Battery6 = spinnerBattery6.getSelectedItem().toString();
        String noteBattery6 = notetationBattery6.getText().toString();
        String Water1 = spinnerWater1.getSelectedItem().toString();
        String noteWater1 = notetationWater1.getText().toString();
        String Water2 = spinnerWater2.getSelectedItem().toString();
        String noteWater2 = notetationWater2.getText().toString();
        String Water3 = spinnerWater3.getSelectedItem().toString();
        String noteWater3 = notetationWater3.getText().toString();
        String Water4 = spinnerWater4.getSelectedItem().toString();
        String noteWater4 = notetationWater4.getText().toString();
        String Control1 = spinnerControl1.getSelectedItem().toString();
        String noteControl1 = notetationControl1.getText().toString();
        String Control2 = spinnerControl2.getSelectedItem().toString();
        String Control3 = spinnerControl3.getSelectedItem().toString();
        String noteControl2 = notetationControl2.getText().toString();
        String Control4 = spinnerControl4.getSelectedItem().toString();
        String noteControl3 = notetationControl3.getText().toString();
        String TestWork1 = spinnerTestWork1.getSelectedItem().toString();
        String TestWork2 = spinnerTestWork2.getSelectedItem().toString();
        String TestWork3 = spinnerTestWork3.getSelectedItem().toString();
        String TestWork4 = spinnerTestWork4.getSelectedItem().toString();
        String TestWork5 = spinnerTestWork5.getSelectedItem().toString();
        String noteTestWork1 = notetationTestWork1.getText().toString();
        String FirePump1 = spinnerFirePump1.getSelectedItem().toString();
        String noteFirePump1 = notetationFirePump1.getText().toString();
        String FirePump2 = spinnerFirePump2.getSelectedItem().toString();
        String noteFirePump2 = notetationFirePump2.getText().toString();
        String FirePump3 = spinnerFirePump3.getSelectedItem().toString();
        String noteFirePump3 = notetationFirePump3.getText().toString();
        String FirePump4 = spinnerFirePump4.getSelectedItem().toString();
        String noteFirePump4 = notetationFirePump4.getText().toString();
       // String nameDevice = nameDeviceDiesel.getText().toString();
        //String Signfn12_1 = Sign.getText().toString();
        //String ed_Sign12_1 = ed_Sign.getText().toString();

        if(!TextUtils.isEmpty(moter1)){
            String id = firebaseReference.child("CheckDieselFn12_1").push().getKey();
            Diesel dieselfn12_1 = new Diesel();

            dieselfn12_1.setId_fn12_1(id);
            dieselfn12_1.setDate_fn12_1(datetime);
            dieselfn12_1.setGeneralityMoter1(moter1);
            dieselfn12_1.setNotationMoter1(notemoter1);
            dieselfn12_1.setGeneralityMoter2(moter2);
            dieselfn12_1.setNotationMoter2(notemoter2);
            dieselfn12_1.setGeneralityMoter3(moter3);
            dieselfn12_1.setNotationMoter3(notemoter3);
            dieselfn12_1.setGeneralityMoter4(moter4);
            dieselfn12_1.setNotationMoter4(notemoter4);
            dieselfn12_1.setGeneralityMoter5(moter5);
            dieselfn12_1.setNotationMoter5(notemoter5);
            dieselfn12_1.setGeneralityMoter6(moter6);
            dieselfn12_1.setNotationMoter6(notemoter6);
            dieselfn12_1.setGeneralityJockeyPump1(jockey1);
            dieselfn12_1.setGeneralityJockeyPump2(jockey2);
            dieselfn12_1.setNotationJockeyPump1(notejockey1);
            dieselfn12_1.setGeneralityJockeyPump3(jockey3);
            dieselfn12_1.setNotationJockeyPump2(notejockey2);
            dieselfn12_1.setGeneralityJockeyPump4(jockey4);
            dieselfn12_1.setNotationJockeyPump3(notejockey3);
            dieselfn12_1.setGeneralityJockeyPump5(jockey5);
            dieselfn12_1.setGeneralityJockeyPump6(jockey6);
            dieselfn12_1.setNotationJockeyPump4(notejockey4);
            dieselfn12_1.setGeneralityBattery1(Battery1);
            dieselfn12_1.setNotationBattery1(noteBattery1);
            dieselfn12_1.setGeneralityBattery2(Battery2);
            dieselfn12_1.setNotationBattery2(noteBattery2);
            dieselfn12_1.setGeneralityBattery3(Battery3);
            dieselfn12_1.setNotationBattery3(noteBattery3);
            dieselfn12_1.setGeneralityBattery4(Battery4);
            dieselfn12_1.setNotationBattery4(noteBattery4);
            dieselfn12_1.setGeneralityBattery5(Battery5);
            dieselfn12_1.setNotationBattery5(noteBattery5);
            dieselfn12_1.setGeneralityBattery6(Battery6);
            dieselfn12_1.setNotationBattery6(noteBattery6);
            dieselfn12_1.setGeneralityWater1(Water1);
            dieselfn12_1.setNotationWater1(noteWater1);
            dieselfn12_1.setGeneralityWater2(Water2);
            dieselfn12_1.setNotationWater2(noteWater2);
            dieselfn12_1.setGeneralityWater3(Water3);
            dieselfn12_1.setNotationWater3(noteWater3);
            dieselfn12_1.setGeneralityWater4(Water4);
            dieselfn12_1.setNotationWater4(noteWater4);
            dieselfn12_1.setGeneralityControl1(Control1);
            dieselfn12_1.setNotationControl1(noteControl1);
            dieselfn12_1.setGeneralityControl2(Control2);
            dieselfn12_1.setGeneralityControl3(Control3);
            dieselfn12_1.setNotationControl2(noteControl2);
            dieselfn12_1.setGeneralityControl4(Control4);
            dieselfn12_1.setNotationControl3(noteControl3);
            dieselfn12_1.setGeneralityTestwork1(TestWork1);
            dieselfn12_1.setGeneralityTestwork2(TestWork2);
            dieselfn12_1.setGeneralityTestwork3(TestWork3);
            dieselfn12_1.setGeneralityTestwork4(TestWork4);
            dieselfn12_1.setGeneralityTestwork5(TestWork5);
            dieselfn12_1.setNotationTestwork1(noteTestWork1);
            dieselfn12_1.setGeneralityFirePump1(FirePump1);
            dieselfn12_1.setNotationFirePump1(noteFirePump1);
            dieselfn12_1.setGeneralityFirePump2(FirePump2);
            dieselfn12_1.setNotationFirePump2(noteFirePump2);
            dieselfn12_1.setGeneralityFirePump3(FirePump3);
            dieselfn12_1.setNotationFirePump3(noteFirePump3);
            dieselfn12_1.setGeneralityFirePump4(FirePump4);
            dieselfn12_1.setNotationFirePump4(noteFirePump4);
            //dieselfn12_1.setDeviceNameDiesel(nameDevice);
            //dieselfn12_1.setSignaturefn12_1(Signfn12_1);
            //dieselfn12_1.setEd_Signspectorfn12_1(ed_Sign12_1);

            firebaseReference.child("CheckDieselFn12_1").child(id).setValue(dieselfn12_1);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckDieselFn12_1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dieselList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Diesel dieselfn12_1 = postSnapshot.getValue(Diesel.class);
                    dieselList.add(dieselfn12_1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
