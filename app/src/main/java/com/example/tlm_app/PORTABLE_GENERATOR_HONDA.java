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

public class PORTABLE_GENERATOR_HONDA extends AppCompatActivity {
    private TextView date;
    private Spinner generMoter1,generMoter2,generMoter3,generMoter4,generMoter5,generRope1,generDynamo1,generDynamo2,generDynamo3,generDynamo4,generDynamo5,
            generDynamo6,generDynamo7,generDynamo8,generClean1,generTestWork1,generTestWork2,generTestWork3,generTestWork4,generTestWork5;

    private EditText notatMoter1,notatMoter2,notatMoter3,notatMoter4,notatMoter5,notatRope1,notatDynamo1,notatDynamo2,notatDynamo3,
            notatDynamo4,notatDynamo5,notatDynamo6,notatDynamo7,notatDynamo8,notatClean1,notatTestWork1,notatTestWork2,notatTestWork3,
            notatTestWork4,notatTestWork5,Signfn12_4,ed_Signfn12_4;

    private ImageView im_back_arrow_fnPORTABLE_GENERATOR_HONDA;
    private Button btn_save_generator_HONDA;
    private List<PortableHonda> portableHondaList;
    private DatabaseReference firebaseReference;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portable_generator_honda);


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
                                TextView tdate = (TextView) findViewById(R.id.datefn12_PORTABLE_GENERATOR_HONDA);
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
        date = (TextView) findViewById(R.id.datefn12_PORTABLE_GENERATOR_HONDA);
        generMoter1 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Motor1);
        notatMoter1 = (EditText) findViewById(R.id.notationfn12_4_1);
        generMoter2 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Motor2);
        notatMoter2 = (EditText) findViewById(R.id.notationfn12_4_2);
        generMoter3 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Motor3);
        notatMoter3 = (EditText) findViewById(R.id.notationfn12_4_3);
        generMoter4 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Motor4);
        notatMoter4 = (EditText) findViewById(R.id.notationfn12_4_4);
        generMoter5 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Motor5);
        notatMoter5 = (EditText) findViewById(R.id.notationfn12_4_5);
        generRope1 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_PullRope1);
        notatRope1 = (EditText) findViewById(R.id.notationfn12_4_6);
        generDynamo1 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo1);
        notatDynamo1 = (EditText) findViewById(R.id.notationfn12_4_7);
        generDynamo2 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo2);
        notatDynamo2 = (EditText) findViewById(R.id.notationfn12_4_8);
        generDynamo3 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo3);
        notatDynamo3 = (EditText) findViewById(R.id.notationfn12_4_9);
        generDynamo4 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo4);
        notatDynamo4 = (EditText) findViewById(R.id.notationfn12_4_10);
        generDynamo5 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo5);
        notatDynamo5 = (EditText) findViewById(R.id.notationfn12_4_11);
        generDynamo6 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo6);
        notatDynamo6 = (EditText) findViewById(R.id.notationfn12_4_12);
        generDynamo7 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo7);
        notatDynamo7 = (EditText) findViewById(R.id.notationfn12_4_13);
        generDynamo8 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Dynamo8);
        notatDynamo8 = (EditText) findViewById(R.id.notationfn12_4_14);
        generClean1 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_Clean1);
        notatClean1 = (EditText) findViewById(R.id.notationfn12_4_15);
        generTestWork1 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_TestWork1);
        notatTestWork1 = (EditText) findViewById(R.id.notationfn12_4_16);
        generTestWork2 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_TestWork2);
        notatTestWork2 = (EditText) findViewById(R.id.notationfn12_4_17);
        generTestWork3 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_TestWork3);
        notatTestWork3 = (EditText) findViewById(R.id.notationfn12_4_18);
        generTestWork4 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_TestWork4);
        notatTestWork4 = (EditText) findViewById(R.id.notationfn12_4_19);
        generTestWork5 = (Spinner) findViewById(R.id.spinner_fnsafety12_GENERATOR_HONDA_TestWork5);
        notatTestWork5 = (EditText) findViewById(R.id.notationfn12_4_20);
        //Signfn12_4 = (EditText) findViewById(R.id.Signaturefn12_4);
        //ed_Signfn12_4 = (EditText) findViewById(R.id.ed_Signspectorfn12_4);

        im_back_arrow_fnPORTABLE_GENERATOR_HONDA = (ImageView) findViewById(R.id.im_back_arrow_fnPORTABLE_GENERATOR_HONDA);
        im_back_arrow_fnPORTABLE_GENERATOR_HONDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PORTABLE_GENERATOR_HONDA.this, FnSafety_12.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save_generator_HONDA = (Button) findViewById(R.id.btn_save_generator_honda);
        btn_save_generator_HONDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinMoter1  = generMoter1.getSelectedItem().toString();
                String spinMoter2  = generMoter2.getSelectedItem().toString();
                String spinMoter3  = generMoter3.getSelectedItem().toString();
                String spinMoter4  = generMoter4.getSelectedItem().toString();
                String spinMoter5  = generMoter5.getSelectedItem().toString();
                String spinRope1 = generRope1.getSelectedItem().toString();
                String spinDynamo1 = generDynamo1.getSelectedItem().toString();
                String spinDynamo2 = generDynamo2.getSelectedItem().toString();
                String spinDynamo3 = generDynamo3.getSelectedItem().toString();
                String spinDynamo4 = generDynamo4.getSelectedItem().toString();
                String spinDynamo5 = generDynamo5.getSelectedItem().toString();
                String spinDynamo6 = generDynamo6.getSelectedItem().toString();
                String spinDynamo7 = generDynamo7.getSelectedItem().toString();
                String spinDynamo8 = generDynamo8.getSelectedItem().toString();
                String spinClean = generClean1.getSelectedItem().toString();
                String spinTestWork1 = generTestWork1.getSelectedItem().toString();
                String spinTestWork2 = generTestWork2.getSelectedItem().toString();
                String spinTestWork3 = generTestWork3.getSelectedItem().toString();
                String spinTestWork4 = generTestWork4.getSelectedItem().toString();
                String spinTestWork5 = generTestWork5.getSelectedItem().toString();

                if (TextUtils.isEmpty(spinMoter1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinMoter2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinMoter3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinMoter4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinMoter5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinRope1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(spinDynamo1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinDynamo2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(spinDynamo2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinDynamo3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinDynamo4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(spinDynamo5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(spinDynamo6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinDynamo7)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinDynamo8)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinClean)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinTestWork1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinTestWork2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(spinTestWork3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinTestWork4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spinTestWork5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (v == btn_save_generator_HONDA){
                    addDataFn12_4();
                    Intent intent = new Intent(PORTABLE_GENERATOR_HONDA.this,FnSafety_12.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        portableHondaList = new ArrayList<>();
    }

    private void  addDataFn12_4(){

        String datetime = date.getText().toString();
        String moter1 = generMoter1.getSelectedItem().toString();
        String notemoter1 = notatMoter1.getText().toString();
        String moter2 = generMoter2.getSelectedItem().toString();
        String notemoter2 = notatMoter2.getText().toString();
        String moter3 = generMoter3.getSelectedItem().toString();
        String notemoter3 = notatMoter3.getText().toString();
        String moter4 = generMoter4.getSelectedItem().toString();
        String notemoter4 = notatMoter4.getText().toString();
        String moter5 = generMoter5.getSelectedItem().toString();
        String notemoter5 = notatMoter5.getText().toString();
        String Rope1 = generRope1.getSelectedItem().toString();
        String noteRope1 = notatRope1.getText().toString();
        String Dynamo1 = generDynamo1.getSelectedItem().toString();
        String noteDynamo1 = notatDynamo1.getText().toString();
        String Dynamo2 = generDynamo2.getSelectedItem().toString();
        String noteDynamo2 = notatDynamo2.getText().toString();
        String Dynamo3 = generDynamo3.getSelectedItem().toString();
        String noteDynamo3 = notatDynamo3.getText().toString();
        String Dynamo4 = generDynamo4.getSelectedItem().toString();
        String noteDynamo4 = notatDynamo4.getText().toString();
        String Dynamo5 = generDynamo5.getSelectedItem().toString();
        String noteDynamo5 = notatDynamo5.getText().toString();
        String Dynamo6 = generDynamo6.getSelectedItem().toString();
        String noteDynamo6 = notatDynamo6.getText().toString();
        String Dynamo7 = generDynamo7.getSelectedItem().toString();
        String noteDynamo7 = notatDynamo7.getText().toString();
        String Dynamo8 = generDynamo8.getSelectedItem().toString();
        String noteDynamo8 = notatDynamo8.getText().toString();
        String Clean1 = generClean1.getSelectedItem().toString();
        String noteClean1 = notatClean1.getText().toString();
        String TestWork1 = generTestWork1.getSelectedItem().toString();
        String noteTestWork1 = notatTestWork1.getText().toString();
        String TestWork2 = generTestWork2.getSelectedItem().toString();
        String noteTestWork2 = notatTestWork2.getText().toString();
        String TestWork3 = generTestWork3.getSelectedItem().toString();
        String noteTestWork3 = notatTestWork3.getText().toString();
        String TestWork4 = generTestWork4.getSelectedItem().toString();
        String noteTestWork4 = notatTestWork4.getText().toString();
        String TestWork5 = generTestWork5.getSelectedItem().toString();
        String noteTestWork5 = notatTestWork5.getText().toString();
        //String Sign = Signfn12_4.getText().toString();
       // String ed_Sign = ed_Signfn12_4.getText().toString();

        if(!TextUtils.isEmpty(moter1)){
            String id = firebaseReference.child("CheckPortableHondaFn12_4").push().getKey();
            PortableHonda honda = new PortableHonda();

            honda.setId_fn12_4(id);
            honda.setDate_fn12_4(datetime);
            honda.setGenerality_Moter1(moter1);
            honda.setNotation_Moter1(notemoter1);
            honda.setGenerality_Moter2(moter2);
            honda.setNotation_Moter2(notemoter2);
            honda.setGenerality_Moter3(moter3);
            honda.setNotation_Moter3(notemoter3);
            honda.setGenerality_Moter4(moter4);
            honda.setNotation_Moter4(notemoter4);
            honda.setGenerality_Moter5(moter5);
            honda.setNotation_Moter5(notemoter5);
            honda.setGenerality_Rope1(Rope1);
            honda.setNotaton_Rope1(noteRope1);
            honda.setGenerality_Dynamo1(Dynamo1);
            honda.setNotation_Dynamo1(noteDynamo1);
            honda.setGenerality_Dynamo2(Dynamo2);
            honda.setNotation_Dynamo2(noteDynamo2);
            honda.setGenerality_Dynamo3(Dynamo3);
            honda.setNotation_Dynamo3(noteDynamo3);
            honda.setGenerality_Dynamo4(Dynamo4);
            honda.setNotation_Dynamo4(noteDynamo4);
            honda.setGenerality_Dynamo5(Dynamo5);
            honda.setNotation_Dynamo5(noteDynamo5);
            honda.setGenerality_Dynamo6(Dynamo6);
            honda.setNotation_Dynamo6(noteDynamo6);
            honda.setGenerality_Dynamo7(Dynamo7);
            honda.setNotation_Dynamo7(noteDynamo7);
            honda.setGenerality_Dynamo8(Dynamo8);
            honda.setNotation_Dynamo8(noteDynamo8);
            honda.setGenerality_Clean1(Clean1);
            honda.setNotation_Clean1(noteClean1);
            honda.setGenerality_TestWork1(TestWork1);
            honda.setNotation_TestWork1(noteTestWork1);
            honda.setGenerality_TestWork2(TestWork2);
            honda.setNotation_TestWork2(noteTestWork2);
            honda.setGenerality_TestWork3(TestWork3);
            honda.setNotation_TestWork3(noteTestWork3);
            honda.setGenerality_TestWork4(TestWork4);
            honda.setNotation_TestWork4(noteTestWork4);
            honda.setGenerality_TestWork5(TestWork5);
            honda.setNotation_TestWork5(noteTestWork5);
           // honda.setSignaturefn12_4(Sign);
           // honda.setEd_Signspectorfn12_4(ed_Sign);

            firebaseReference.child("CheckPortableHondaFn12_4").child(id).setValue(honda);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private  void  showData(){
        Query query = firebaseReference.child("CheckPortableHondaFn12_4");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                portableHondaList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PortableHonda honda = postSnapshot.getValue(PortableHonda.class);
                    portableHondaList.add(honda);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
