package com.example.tlm_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class Fn_Safety_2 extends AppCompatActivity {
    private EditText Sign,ed_Signaturefn2,notationFnSafety2;
    private Spinner spinner_fnsafety2_locat,spinner_fnsafety2_electricity,spinner_fnsafety2_generality;
    private  Button btn_save_fn2,btnQRScannerFn2;
    public static TextView date,nameDevicefn2,tv_ReadResultFn2;
    private List<EmergencyLight> emergencyLights;
    private DatabaseReference firebaseReference;
    private SafetyFn2Adapter safetyFn2Adapter;
    private FirebaseAuth auth;
    private ListView listViewSafety2;
    String DeviceModel, DeviceName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_2);



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
                                TextView tdate = (TextView) findViewById(R.id.datefn2);
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

        date = (TextView) findViewById(R.id.datefn2);
        tv_ReadResultFn2 = (TextView) findViewById(R.id.tv_ReadResultFn2);
        //spinner_fnsafety2_locat = (Spinner) findViewById(R.id.spinner_fnsafety2_1);
       // spinner_fnsafety2_electricity = (Spinner) findViewById(R.id.spinner_fnsafety2_2);
        spinner_fnsafety2_generality = (Spinner) findViewById(R.id.spinner_fnsafety2_3);
        ImageView im_back_arrowfn2 = (ImageView) findViewById(R.id.im_back_arrowfn2);
        //nameDevicefn2 = (TextView) findViewById(R.id.nameDevicefn2);
       // Sign = (EditText) findViewById(R.id.Signfn2);
       // ed_Signaturefn2 = (EditText) findViewById(R.id.ed_Signaturefn2);
        notationFnSafety2 = (EditText) findViewById(R.id.notationFnSafety2);
        btn_save_fn2 = (Button) findViewById(R.id.btn_save_fn2);
        btnQRScannerFn2 = (Button) findViewById(R.id.btnQRScannerFn2);
        im_back_arrowfn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fn_Safety_2.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save_fn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String location = spinner_fnsafety2_locat.getSelectedItem().toString();
               // String TypeLightEmer = spinner_fnsafety2_electricity.getSelectedItem().toString();
                String gennerality = spinner_fnsafety2_generality.getSelectedItem().toString();
                //  String namedevice = manufacturer.getText().toString();
                String notation = notationFnSafety2.getText().toString();

//                if (TextUtils.isEmpty(location)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(TypeLightEmer)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(gennerality)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(notation)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (v == btn_save_fn2) {
                    addDataFn2();
                    Intent intent = new Intent(Fn_Safety_2.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fn_Safety_2.this,ScanQRFn_2.class));
            }
        });

//        listViewSafety2 = (ListView) findViewById(R.id.listViewSafety2);
//        listViewSafety2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                EmergencyLight light = emergencyLights.get(i);
//                showDialog(light);
//                return false;
//            }
//        });

        emergencyLights = new ArrayList<>();

    }

    private void addDataFn2(){

//
//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn2.setText(DeviceName);


       String btnfn2 = btn_save_fn2.getText().toString();
       String datetimefn2 = date.getText().toString();
       String resultfn2 = tv_ReadResultFn2.getText().toString();
       //String Signfn2 = Sign.getText().toString();
      // String edSign = ed_Signaturefn2.getText().toString();
       String notefn2 = notationFnSafety2.getText().toString();
       //String locat = spinner_fnsafety2_locat.getSelectedItem().toString();
       //String typeLight = spinner_fnsafety2_electricity.getSelectedItem().toString();
       String generafn2 = spinner_fnsafety2_generality.getSelectedItem().toString();
      // String nameDevice =  nameDevicefn2.getText().toString();

        //checking if the value is provided
        if(!TextUtils.isEmpty(generafn2)){
            String id = firebaseReference.child("CheckEmergencyLightFn2").push().getKey();
            EmergencyLight light = new EmergencyLight();

//            DeviceName= Build.MANUFACTURER;
//
//            // manufacturer.setText(DeviceModel);
//            nameDevicefn2.setText(DeviceName);


            light.setId_fn2(id);
            light.setDate_fn2(datetimefn2);
            light.setResult_fn2(resultfn2);
            //light.setLocat_fn2(locat);
            //light.settype_EmergencyLight_fn2(typeLight);
            light.setGenerality_fn2(generafn2);
          //  light.setManufacturer_fn2(nameDevice);
            light.setNotation_fn2(notefn2);
           // light.setSignfn2(Signfn2);
           // light.setEdSignfn2(edSign);


            firebaseReference.child("CheckEmergencyLightFn2").child(id).setValue(light);

            Toast.makeText(this, "Checking Successful", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Please fill your information completely", Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckEmergencyLightFn2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                emergencyLights.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    EmergencyLight light = postSnapshot.getValue(EmergencyLight.class);
                    emergencyLights.add(light);
                }
//                safetyFn2Adapter = new SafetyFn2Adapter(emergencyLights);
//                listViewSafety2.setAdapter(safetyFn2Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



//    private boolean updateData(String id, String date, String locat, String typelight, String general, String Note, String Sign, String edSign) {
//        /// Updating ////
//
//        EmergencyLight light = new EmergencyLight();
//        light.setId_fn2(id);
//        light.setDate_fn2(date);
//        light.setLocat_fn2(locat);
//        light.settype_EmergencyLight_fn2(typelight);
//        light.setGenerality_fn2(general);
//        light.setNotation_fn2(Note);
//        light.setSignfn2(Sign);
//        light.setEdSignfn2(edSign);
//
//        firebaseReference.child("CheckEmergencyLightFn2").child(id).setValue(light);
//        Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//
//    private boolean deleteData(String id) {
//        // removing
//        firebaseReference.child("CheckEmergencyLightFn2").child(id).removeValue();
//
//        Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
//
//        return true;
//    }
//
//    private int getIndex(Spinner spinner, String myString) {
//        int index = 0;
//
//        for (int i = 0; i < spinner.getCount(); i++) {
//            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }
//
//    private void showDialog(final EmergencyLight light){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.fn_safety2_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final ImageButton ib_exitfn2 = (ImageButton) dialogView.findViewById(R.id.ib_exitfn2);
//        final TextView datetime = (TextView) dialogView.findViewById(R.id.datefn2);
//        final Spinner spinnerlocat = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety2_1);
//        final Spinner spinnertypeLight = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety2_2);
//        final Spinner spinnergeneral = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety2_3);
//        final EditText note = (EditText) dialogView.findViewById(R.id.notationFnSafety2);
//        final EditText sign = (EditText) dialogView.findViewById(R.id.Signfn2);
//        final EditText edSign = (EditText) dialogView.findViewById(R.id.ed_Signaturefn2);
//        final Button btnUpdatefn2 = (Button) dialogView.findViewById(R.id.btnUpdatefn2);
//        final Button btnDeletefn2 = (Button) dialogView.findViewById(R.id.btnDeletefn2);
//
//
//        dialogBuilder.setTitle(light.getDate_fn2());
//        datetime.setText(light.getDate_fn2());
//        spinnerlocat.setSelection(getIndex(spinnerlocat,light.getLocat_fn2()));
//        spinnertypeLight.setSelection(getIndex(spinnertypeLight,light.gettype_EmergencyLight_fn2()));
//        spinnergeneral.setSelection(getIndex(spinnergeneral,light.getGenerality_fn2()));
//        dialogBuilder.setTitle(light.getNotation_fn2());
//        note.setText(light.getNotation_fn2());
//        dialogBuilder.setTitle(light.getSignfn2());
//        sign.setText(light.getSignfn2());
//        dialogBuilder.setTitle(light.getEdSignfn2());
//        edSign.setText(light.getEdSignfn2());
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        ib_exitfn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Fn_Safety_2.this, Fn_Safety_2.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btnUpdatefn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String date = datetime.getText().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
//                String typelight = spinnertypeLight.getSelectedItem().toString();
//                String general = spinnergeneral.getSelectedItem().toString();
//                String notation = note.getText().toString();
//                String signfn2 = sign.getText().toString();
//                String edSignfn2 = edSign.getText().toString();
//
//                if (!TextUtils.isEmpty(locat)){
//                    updateData(light.getId_fn2(), date,locat,typelight,general,notation,signfn2,edSignfn2);
//                    alertDialog.dismiss();
//                }
//            }
//        });
//
//        btnDeletefn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData(light.getId_fn2());
//                alertDialog.dismiss();
//            }
//        });
//
//    }
//
//
}
