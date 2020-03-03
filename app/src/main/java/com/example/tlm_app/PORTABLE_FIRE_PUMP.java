package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

public class PORTABLE_FIRE_PUMP extends AppCompatActivity {
    private TextView date;
    private Spinner generBattery1,generBattery2,generBattery3,generAutoCharg1,generAutoCharg2,generRope1,generMoter1,generMoter2,generMoter3,generMoter4,generMoter5,generTest1;
    private EditText notatfn12_3_1,notatfn12_3_2,notatfn12_3_3,notatfn12_3_4,notatfn12_3_5,notatfn12_3_6,notatfn12_3_7,notatfn12_3_8,notatfn12_3_9,notatfn12_3_10,notatfn12_3_11,notatfn12_3_12,Signfn12_3,ed_Signfn12_3;
    private List<Portable> portableList;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrow_Portable_firePump;
    private Button btn_save_portable_firepump;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portable_fire_pump);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn12_PortableFirePump);
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

        date = (TextView) findViewById(R.id.datefn12_PortableFirePump);
        generBattery1 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Battrey1);
        notatfn12_3_1 = (EditText) findViewById(R.id.notationfn12_3_1);
        generBattery2 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Battrey2);
        notatfn12_3_2 = (EditText) findViewById(R.id.notationfn12_3_2);
        generBattery3 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Battrey3);
        notatfn12_3_3 = (EditText) findViewById(R.id.notationfn12_3_3);
        generAutoCharg1 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Chargebattery1);
        notatfn12_3_4 = (EditText) findViewById(R.id.notationfn12_3_4);
        generAutoCharg2 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Chargebattery2);
        notatfn12_3_5 = (EditText) findViewById(R.id.notationfn12_3_5);
        generRope1 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Rope1);
        notatfn12_3_6 = (EditText) findViewById(R.id.notationfn12_3_6);
        generMoter1 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Motor1);
        notatfn12_3_7 = (EditText) findViewById(R.id.notationfn12_3_7);
        generMoter2 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Motor2);
        notatfn12_3_8 = (EditText) findViewById(R.id.notationfn12_3_8);
        generMoter3 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Motor3);
        notatfn12_3_9 = (EditText) findViewById(R.id.notationfn12_3_9);
        generMoter4 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Motor4);
        notatfn12_3_10 = (EditText) findViewById(R.id.notationfn12_3_10);
        generMoter5 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_Motor5);
        notatfn12_3_11 = (EditText) findViewById(R.id.notationfn12_3_11);
        generTest1 = (Spinner) findViewById(R.id.spinner_fn12_Portable_FirePump_TestWork1);
        notatfn12_3_12 = (EditText) findViewById(R.id.notationfn12_3_12);
        //Signfn12_3 = (EditText) findViewById(R.id.Signaturefn12_3);
        //ed_Signfn12_3 = (EditText) findViewById(R.id.ed_Signspectorfn12_3);
        im_back_arrow_Portable_firePump = (ImageView) findViewById(R.id.im_back_arrow_Portable_firePump);
        im_back_arrow_Portable_firePump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PORTABLE_FIRE_PUMP.this, FnSafety_12.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save_portable_firepump = (Button) findViewById(R.id.btn_save_portable_firepump);
        btn_save_portable_firepump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerbattery1 = generBattery1.getSelectedItem().toString();
                String spinnerbattery2 = generBattery2.getSelectedItem().toString();
                String spinnerbattery3 = generBattery2.getSelectedItem().toString();
                String spinnerAutoCharg1 = generAutoCharg1.getSelectedItem().toString();
                String spinnerAutoCharg2 = generAutoCharg2.getSelectedItem().toString();
                String spinnerRope1 = generRope1.getSelectedItem().toString();
                String spinnerMoter1 = generMoter1.getSelectedItem().toString();
                String spinnerMoter2 = generMoter2.getSelectedItem().toString();
                String spinnerMoter3 = generMoter3.getSelectedItem().toString();
                String spinnerMoter4 = generMoter4.getSelectedItem().toString();
                String spinnerMoter5 = generMoter5.getSelectedItem().toString();
                String spinnerTest1 = generTest1.getSelectedItem().toString();

                if(TextUtils.isEmpty(spinnerbattery1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerbattery2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerbattery3)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerAutoCharg1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerAutoCharg2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerRope1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerMoter1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerMoter2)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerMoter3)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerMoter4)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerMoter5)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(spinnerTest1)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(v == btn_save_portable_firepump){
                    addDataFn12_3();
                    Intent intent = new Intent(PORTABLE_FIRE_PUMP.this,FnSafety_12.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        portableList = new ArrayList<>();
    }

    private void addDataFn12_3(){

        String datetime = date.getText().toString();
        String Battery1 = generBattery1.getSelectedItem().toString();
        String noteBattery1 = notatfn12_3_1.getText().toString();
        String Battery2 = generBattery2.getSelectedItem().toString();
        String noteBattery2 = notatfn12_3_2.getText().toString();
        String Battery3 = generBattery3.getSelectedItem().toString();
        String noteBattery3 = notatfn12_3_3.getText().toString();
        String AutoCharg1 = generAutoCharg1.getSelectedItem().toString();
        String noteAutoCharg1 = notatfn12_3_4.getText().toString();
        String AutoCharg2 = generAutoCharg2.getSelectedItem().toString();
        String noteAutoCharg2 = notatfn12_3_5.getText().toString();
        String Rope1 = generRope1.getSelectedItem().toString();
        String noteRope1 = notatfn12_3_6.getText().toString();
        String Moter1 = generMoter1.getSelectedItem().toString();
        String noteMoter1 = notatfn12_3_7.getText().toString();
        String Moter2 = generMoter2.getSelectedItem().toString();
        String noteMoter2 = notatfn12_3_8.getText().toString();
        String Moter3 = generMoter3.getSelectedItem().toString();
        String noteMoter3 = notatfn12_3_9.getText().toString();
        String Moter4 = generMoter4.getSelectedItem().toString();
        String noteMoter4 = notatfn12_3_10.getText().toString();
        String Moter5 = generMoter5.getSelectedItem().toString();
        String noteMoter5 = notatfn12_3_11.getText().toString();
        String Test1 = generTest1.getSelectedItem().toString();
        String noteTest1 = notatfn12_3_12.getText().toString();
        //String Sign = Signfn12_3.getText().toString();
       // String ed_Sign = ed_Signfn12_3.getText().toString();

        if(!TextUtils.isEmpty(Battery1)){
            String id = firebaseReference.child("CheckPortableFn12_3").push().getKey();
            Portable portablefn12_3 = new Portable();

            portablefn12_3.setId_fn12_3(id);
            portablefn12_3.setDate_fn12_3(datetime);
            portablefn12_3.setGenerality_Battery1(Battery1);
            portablefn12_3.setNotation_fn12_3_1(noteBattery1);
            portablefn12_3.setGenerality_Battery2(Battery2);
            portablefn12_3.setNotation_fn12_3_2(noteBattery2);
            portablefn12_3.setGenerality_Battery3(Battery3);
            portablefn12_3.setNotation_fn12_3_3(noteBattery3);
            portablefn12_3.setGenerality_AutoCharg1(AutoCharg1);
            portablefn12_3.setNotation_fn12_3_4(noteAutoCharg1);
            portablefn12_3.setGenerality_AutoCharg2(AutoCharg2);
            portablefn12_3.setNotation_fn12_3_5(noteAutoCharg2);
            portablefn12_3.setGenerality_Rope1(Rope1);
            portablefn12_3.setNotation_fn12_3_6(noteRope1);
            portablefn12_3.setGenerality_Moter1(Moter1);
            portablefn12_3.setNotation_fn12_3_7(noteMoter1);
            portablefn12_3.setGenerality_Moter2(Moter2);
            portablefn12_3.setNotation_fn12_3_8(noteMoter2);
            portablefn12_3.setGenerality_Moter3(Moter3);
            portablefn12_3.setNotation_fn12_3_9(noteMoter3);
            portablefn12_3.setGenerality_Moter4(Moter4);
            portablefn12_3.setNotation_fn12_3_10(noteMoter4);
            portablefn12_3.setGenerality_Moter5(Moter5);
            portablefn12_3.setNotation_fn12_3_11(noteMoter5);
            portablefn12_3.setGenerality_Test1(Test1);
            portablefn12_3.setNotation_fn12_3_12(noteTest1);
           // portablefn12_3.setSignaturefn12_3(Sign);
           // portablefn12_3.setEd_Signspectorfn12_3(ed_Sign);

            firebaseReference.child("CheckPortableFn12_3").child(id).setValue(portablefn12_3);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckPortableFn12_3");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                portableList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Portable portablefn12_3 = postSnapshot.getValue(Portable.class);
                    portableList.add(portablefn12_3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
