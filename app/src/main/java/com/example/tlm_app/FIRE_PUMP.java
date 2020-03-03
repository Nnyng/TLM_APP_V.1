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

public class FIRE_PUMP extends AppCompatActivity {
    private  TextView date,nameDeviceFire;
    private  Spinner spinBattery1,spinBattery2,spinBattery3,spinBattery4,spinnerMoter1,spinnerMoter2,spinnerMoter3,spinnerMoter4,spinnerMoter5,spinWater1,spinWater2,spinWater3,
                     spinControl1,spinControl2,spinTestWork1,spinTestWork2,spinTestWork3,spinTestWork4,spinTestWork5,spinTestWork6,spinTestWork7,spinTestWork8,spinTestWork9,spinTestWork10,
                     spinTestWork11,spinTestWork12,spinTestWork13,spinTestWork14,spinFirePump1,spinFirePump2,spinFirePump3,spinFirePump4;
    private EditText notatBattery1,notatBattery2,notatBattery3,notatBattery4,notatMoter1,notatMoter2,notatMoter3,notatMoter4,notatMoter5,notatWater1,notatWater2,notatWater3,notatControl1,notatControl2,notatTestWork1,notatTestWork2,
                     notatTestWork3,notatTestWork4,notatTestWork5,notatTestWork6,notatTestWork7,notatTestWork8,notatTestWork9,notatTestWork10,notatTestWork11,notatTestWork12,notatTestWork13,notatTestWork14,notatFirePump1,notatFirePump2,notatFirePump3,notatFirePump4,
                     Signfn12_2,ed_Signfn12_2;
    private ImageView im_back_arrow_FirePump;
    private Button btn_save_firepump;
    private List<Fire> fireList;
    private DatabaseReference firebaseReference;
    String DeviceModel, DeviceName;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fire_pump);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn12_FirePump);
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

        date = (TextView) findViewById(R.id.datefn12_FirePump);
        spinBattery1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_ฺBattery1);
        notatBattery1 = (EditText) findViewById(R.id.notationfn12_2_1);
        spinBattery2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_ฺBattery2);
        notatBattery2 = (EditText) findViewById(R.id.notationfn12_2_2);
        spinBattery3 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Battery3);
        notatBattery3 = (EditText) findViewById(R.id.notationfn12_2_3);
        spinBattery4 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Battery4);
        notatBattery4 = (EditText) findViewById(R.id.notationfn12_2_4);
        spinnerMoter1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Motor1);
        notatMoter1 = (EditText) findViewById(R.id.notationfn12_2_5);
        spinnerMoter2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Motor2);
        notatMoter2 = (EditText) findViewById(R.id.notationfn12_2_6);
        spinnerMoter3 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Motor3);
        notatMoter3 = (EditText) findViewById(R.id.notationfn12_2_7);
        spinnerMoter4 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Motor4);
        notatMoter4 = (EditText) findViewById(R.id.notationfn12_2_8);
        spinnerMoter5 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Motor5);
        notatMoter5 = (EditText) findViewById(R.id.notationfn12_2_9);
        spinWater1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Water1);
        notatWater1 = (EditText) findViewById(R.id.notationfn12_2_10);
        spinWater2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Water2);
        notatWater2 = (EditText) findViewById(R.id.notationfn12_2_11);
        spinWater3 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Water3);
        notatWater3 = (EditText) findViewById(R.id.notationfn12_2_12);
        spinControl1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Electronic_Control1);
        notatControl1 = (EditText) findViewById(R.id.notationfn12_2_13);
        spinControl2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_Electronic_Control2);
        notatControl2 = (EditText) findViewById(R.id.notationfn12_2_14);
        spinTestWork1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_ฺTestWork1);
        notatTestWork1 = (EditText) findViewById(R.id.notationfn12_2_15);
        spinTestWork2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork2);
        notatTestWork2 = (EditText) findViewById(R.id.notationfn12_2_16);
        spinTestWork3 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork3);
        notatTestWork3 = (EditText) findViewById(R.id.notationfn12_2_17);
        spinTestWork4 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork4);
        notatTestWork4 = (EditText) findViewById(R.id.notationfn12_2_18);
        spinTestWork5 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork5);
        notatTestWork5 = (EditText) findViewById(R.id.notationfn12_2_19);
        spinTestWork6 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork6);
        notatTestWork6 = (EditText) findViewById(R.id.notationfn12_2_20);
        spinTestWork7 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork7);
        notatTestWork7 = (EditText) findViewById(R.id.notationfn12_2_21);
        spinTestWork8 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork8);
        notatTestWork8 = (EditText) findViewById(R.id.notationfn12_2_22);
        spinTestWork9 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork9);
        notatTestWork9 = (EditText) findViewById(R.id.notationfn12_2_23);
        spinTestWork10 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork10);
        notatTestWork10 = (EditText) findViewById(R.id.notationfn12_2_24);
        spinTestWork11 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork11);
        notatTestWork11 = (EditText) findViewById(R.id.notationfn12_2_25);
        spinTestWork12 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork12);
        notatTestWork12 = (EditText) findViewById(R.id.notationfn12_2_26);
        spinTestWork13 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork13);
        notatTestWork13 = (EditText) findViewById(R.id.notationfn12_2_27);
        spinTestWork14 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_TestWork14);
        notatTestWork14 = (EditText) findViewById(R.id.notationfn12_2_28);
        spinFirePump1 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_RoomFirePump1);
        notatFirePump1 = (EditText) findViewById(R.id.notationfn12_2_29);
        spinFirePump2 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_RoomFirePump2);
        notatFirePump2 = (EditText) findViewById(R.id.notationfn12_2_30);
        spinFirePump3 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_RoomFirePump3);
        notatFirePump3 = (EditText) findViewById(R.id.notationfn12_2_31);
        spinFirePump4 = (Spinner) findViewById(R.id.spinner_fnsafety12_Firepump_RoomFirePump4);
        notatFirePump4 = (EditText) findViewById(R.id.notationfn12_2_32);
        //nameDeviceFire = (TextView) findViewById(R.id.nameDeviceFire);
        //Signfn12_2 = (EditText) findViewById(R.id.Signaturefn12_2);
        //ed_Signfn12_2 = (EditText) findViewById(R.id.ed_Signspectorfn12_2);
        im_back_arrow_FirePump = (ImageView) findViewById(R.id.im_back_arrow_FirePump);
        im_back_arrow_FirePump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FIRE_PUMP.this, FnSafety_12.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_firepump = (Button) findViewById(R.id.btn_save_firepump);
        btn_save_firepump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String generBattery1 = spinBattery1.getSelectedItem().toString();
                String generBattery2 = spinBattery2.getSelectedItem().toString();
                String generBattery3 = spinBattery3.getSelectedItem().toString();
                String generBattery4 = spinBattery4.getSelectedItem().toString();
                String genermoter1 = spinnerMoter1.getSelectedItem().toString();
                String genermoter2 = spinnerMoter2.getSelectedItem().toString();
                String genermoter3 = spinnerMoter3.getSelectedItem().toString();
                String genermoter4 = spinnerMoter4.getSelectedItem().toString();
                String genermoter5 = spinnerMoter5.getSelectedItem().toString();
                String generWater1 = spinWater1.getSelectedItem().toString();
                String generWater2 = spinWater2.getSelectedItem().toString();
                String generWater3 = spinWater3.getSelectedItem().toString();
                String generControl1 = spinControl1.getSelectedItem().toString();
                String generControl2 = spinControl2.getSelectedItem().toString();
                String generTestWork1 = spinTestWork1.getSelectedItem().toString();
                String generTestWork2 = spinTestWork2.getSelectedItem().toString();
                String generTestWork3 = spinTestWork3.getSelectedItem().toString();
                String generTestWork4 = spinTestWork4.getSelectedItem().toString();
                String generTestWork5 = spinTestWork5.getSelectedItem().toString();
                String generTestWork6 = spinTestWork6.getSelectedItem().toString();
                String generTestWork7 = spinTestWork7.getSelectedItem().toString();
                String generTestWork8 = spinTestWork8.getSelectedItem().toString();
                String generTestWork9 = spinTestWork9.getSelectedItem().toString();
                String generTestWork10 = spinTestWork10.getSelectedItem().toString();
                String generTestWork11 = spinTestWork11.getSelectedItem().toString();
                String generTestWork12 = spinTestWork12.getSelectedItem().toString();
                String generTestWork13 = spinTestWork13.getSelectedItem().toString();
                String generTestWork14 = spinTestWork14.getSelectedItem().toString();
                String generFirePump1 = spinFirePump1.getSelectedItem().toString();
                String generFirePump2 = spinFirePump2.getSelectedItem().toString();
                String generFirePump3 = spinFirePump3.getSelectedItem().toString();
                String generFirePump4 = spinFirePump4.getSelectedItem().toString();


                if (TextUtils.isEmpty(generBattery1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generBattery2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generBattery3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(generBattery4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(genermoter1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(genermoter2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(genermoter3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(genermoter4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(genermoter5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generWater1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generWater2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generWater3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generControl1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generControl2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork7)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork8)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork9)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork10)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork11)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork12)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork13)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generTestWork14)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generFirePump1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generFirePump2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generFirePump3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(generFirePump4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (v == btn_save_firepump) {
                    addDataFn12_2();
                    Intent intent = new Intent(FIRE_PUMP.this, FnSafety_12.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        fireList = new ArrayList<>();
    }

    private void addDataFn12_2(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDeviceFire.setText(DeviceName);


        String datetime  = date.getText().toString();
        String Battery1  = spinBattery1.getSelectedItem().toString();
        String noteBattery1 = notatBattery1.getText().toString();
        String Battery2  = spinBattery2.getSelectedItem().toString();
        String noteBattery2 = notatBattery2.getText().toString();
        String Battery3  = spinBattery3.getSelectedItem().toString();
        String noteBattery3 = notatBattery3.getText().toString();
        String Battery4  = spinBattery4.getSelectedItem().toString();
        String noteBattery4 = notatBattery4.getText().toString();
        String moter1 = spinnerMoter1.getSelectedItem().toString();
        String noteMoter1 = notatMoter1.getText().toString();
        String moter2 = spinnerMoter2.getSelectedItem().toString();
        String noteMoter2 = notatMoter2.getText().toString();
        String moter3 = spinnerMoter3.getSelectedItem().toString();
        String noteMoter3 = notatMoter3.getText().toString();
        String moter4 = spinnerMoter4.getSelectedItem().toString();
        String noteMoter4 = notatMoter4.getText().toString();
        String moter5 = spinnerMoter5.getSelectedItem().toString();
        String noteMoter5 = notatMoter5.getText().toString();
        String Water1 = spinWater1.getSelectedItem().toString();
        String noteWater1 = notatWater1.getText().toString();
        String Water2 = spinWater2.getSelectedItem().toString();
        String noteWater2 = notatWater2.getText().toString();
        String Water3 = spinWater3.getSelectedItem().toString();
        String noteWater3 = notatWater3.getText().toString();
        String Control1 = spinControl1.getSelectedItem().toString();
        String noteCotrol1 = notatControl1.getText().toString();
        String Control2 = spinControl2.getSelectedItem().toString();
        String noteCotrol2 = notatControl2.getText().toString();
        String TestWork1 = spinTestWork1.getSelectedItem().toString();
        String noteTestWork1 = notatTestWork1.getText().toString();
        String TestWork2 = spinTestWork2.getSelectedItem().toString();
        String noteTestWork2 = notatTestWork2.getText().toString();
        String TestWork3 = spinTestWork3.getSelectedItem().toString();
        String noteTestWork3 = notatTestWork3.getText().toString();
        String TestWork4 = spinTestWork4.getSelectedItem().toString();
        String noteTestWork4 = notatTestWork4.getText().toString();
        String TestWork5 = spinTestWork5.getSelectedItem().toString();
        String noteTestWork5 = notatTestWork5.getText().toString();
        String TestWork6 = spinTestWork6.getSelectedItem().toString();
        String noteTestWork6 = notatTestWork6.getText().toString();
        String TestWork7 = spinTestWork7.getSelectedItem().toString();
        String noteTestWork7 = notatTestWork7.getText().toString();
        String TestWork8 = spinTestWork8.getSelectedItem().toString();
        String noteTestWork8 = notatTestWork8.getText().toString();
        String TestWork9 = spinTestWork9.getSelectedItem().toString();
        String noteTestWork9 = notatTestWork9.getText().toString();
        String TestWork10 = spinTestWork10.getSelectedItem().toString();
        String noteTestWork10 = notatTestWork10.getText().toString();
        String TestWork11 = spinTestWork11.getSelectedItem().toString();
        String noteTestWork11 = notatTestWork11.getText().toString();
        String TestWork12 = spinTestWork12.getSelectedItem().toString();
        String noteTestWork12 = notatTestWork12.getText().toString();
        String TestWork13 = spinTestWork13.getSelectedItem().toString();
        String noteTestWork13 = notatTestWork13.getText().toString();
        String TestWork14 = spinTestWork14.getSelectedItem().toString();
        String noteTestWork14 = notatTestWork14.getText().toString();
        String FirePump1 = spinFirePump1.getSelectedItem().toString();
        String noteFirePump1 = notatFirePump1.getText().toString();
        String FirePump2 = spinFirePump2.getSelectedItem().toString();
        String noteFirePump2 = notatFirePump2.getText().toString();
        String FirePump3 = spinFirePump3.getSelectedItem().toString();
        String noteFirePump3 = notatFirePump3.getText().toString();
        String FirePump4 = spinFirePump4.getSelectedItem().toString();
        String noteFirePump4 = notatFirePump4.getText().toString();
        //String nameDevice = nameDeviceFire.getText().toString();
        //String Sign = Signfn12_2.getText().toString();
        //String ed_Sign = ed_Signfn12_2.getText().toString();

        if(!TextUtils.isEmpty(Battery1)){
            String id = firebaseReference.child("CheckFireFn12_2").push().getKey();
            Fire firefn12_2 = new Fire();

            firefn12_2.setId_fn12_2(id);
            firefn12_2.setDate_fn12_2(datetime);
            firefn12_2.setSpinerBattery1(Battery1);
            firefn12_2.setNotation_Battery1(noteBattery1);
            firefn12_2.setSpinnerMoter2(Battery2);
            firefn12_2.setNotation_Battery2(noteBattery2);
            firefn12_2.setSpinerBattery3(Battery3);
            firefn12_2.setNotation_Battery3(noteBattery3);
            firefn12_2.setSpinerBattery4(Battery4);
            firefn12_2.setNotation_Battery4(noteBattery4);
            firefn12_2.setSpinnerMoter1(moter1);
            firefn12_2.setNotation_Moter1(noteMoter1);
            firefn12_2.setSpinnerMoter2(moter2);
            firefn12_2.setNotation_Moter2(noteMoter2);
            firefn12_2.setSpinnerMoter3(moter3);
            firefn12_2.setNotation_Moter3(noteMoter3);
            firefn12_2.setSpinnerMoter4(moter4);
            firefn12_2.setNotation_Moter4(noteMoter4);
            firefn12_2.setSpinnerMoter5(moter5);
            firefn12_2.setNotation_Moter5(noteMoter5);
            firefn12_2.setSpinnerWater1(Water1);
            firefn12_2.setNotation_Water1(noteWater1);
            firefn12_2.setSpinnerWater2(Water2);
            firefn12_2.setNotation_Water2(noteWater2);
            firefn12_2.setSpinnerWater3(Water3);
            firefn12_2.setNotation_Water3(noteWater3);
            firefn12_2.setSpinnerControl1(Control1);
            firefn12_2.setNotation_Control1(noteCotrol1);
            firefn12_2.setSpinnerControl2(Control2);
            firefn12_2.setNotation_Control2(noteCotrol2);
            firefn12_2.setSpinnerTestWork1(TestWork1);
            firefn12_2.setNotation_TestWork1(noteTestWork1);
            firefn12_2.setSpinnerTestWork2(TestWork2);
            firefn12_2.setNotation_TestWork2(noteTestWork2);
            firefn12_2.setSpinnerTestWork3(TestWork3);
            firefn12_2.setNotation_TestWork3(noteTestWork3);
            firefn12_2.setSpinnerTestWork4(TestWork4);
            firefn12_2.setNotation_TestWork4(noteTestWork4);
            firefn12_2.setSpinnerTestWork5(TestWork5);
            firefn12_2.setNotation_TestWork5(noteTestWork5);
            firefn12_2.setSpinnerTestWork6(TestWork6);
            firefn12_2.setNotation_TestWork6(noteTestWork6);
            firefn12_2.setSpinnerTestWork7(TestWork7);
            firefn12_2.setNotation_TestWork7(noteTestWork7);
            firefn12_2.setSpinnerTestWork8(TestWork8);
            firefn12_2.setNotation_TestWork8(noteTestWork8);
            firefn12_2.setSpinnerTestWork9(TestWork9);
            firefn12_2.setNotation_TestWork9(noteTestWork9);
            firefn12_2.setSpinnerTestWork10(TestWork10);
            firefn12_2.setNotation_TestWork10(noteTestWork10);
            firefn12_2.setSpinnerTestWork11(TestWork11);
            firefn12_2.setNotation_TestWork11(noteTestWork11);
            firefn12_2.setSpinnerTestWork12(TestWork12);
            firefn12_2.setNotation_TestWork12(noteTestWork12);
            firefn12_2.setSpinnerTestWork13(TestWork13);
            firefn12_2.setNotation_TestWork13(noteTestWork13);
            firefn12_2.setSpinnerTestWork14(TestWork14);
            firefn12_2.setNotation_TestWork14(noteTestWork14);
            firefn12_2.setSpinnerFirePump1(FirePump1);
            firefn12_2.setNotation_FirePump1(noteFirePump1);
            firefn12_2.setSpinnerFirePump2(FirePump2);
            firefn12_2.setNotation_FirePump2(noteFirePump2);
            firefn12_2.setSpinnerFirePump3(FirePump3);
            firefn12_2.setNotation_FirePump3(noteFirePump3);
            firefn12_2.setSpinnerFirePump4(FirePump4);
            firefn12_2.setNotation_FirePump4(noteFirePump4);
            //firefn12_2.setDeviceNameFire(nameDevice);
           // firefn12_2.setSignature_fn12_2(Sign);
            //firefn12_2.setEd_Signspector_fn12_2(ed_Sign);

            firebaseReference.child("CheckFireFn12_2").child(id).setValue(firefn12_2);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }

    }

    private void showData(){
        Query query = firebaseReference.child("CheckFireFn12_2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Fire firefn12_2 = postSnapshot.getValue(Fire.class);
                    fireList.add(firefn12_2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}