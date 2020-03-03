package com.example.tlm_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.L;
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

public class FnSafety_3 extends AppCompatActivity {

    public static TextView date,nameDevicefn3,tv_ReadResultFn3;
    private Spinner numlocation,typedevicefn3,locationfn3,generalityfn3_1,generalityfn3_2,generalityfn3_3,generalityfn3_4,generalityfn3_5,generalityfn3_6;
    private EditText namesignfn3,nameEdSignfn3,notationFn3_1,notationFn3_2,notationFn3_3,notationFn3_4,notationFn3_5,notationFn3_6;
    private Button btn_saveData,btnQRScannerFn3;
    private ListView listViewSafety3;
    private List<FireExtinguisher> fireExtinguishers;
    private SafetyFn3Adapter safetyFn3Adapter;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn3;
    String DeviceModel, DeviceName;
    //String manufacturer = Build.MANUFACTURER;
    //String brand        = Build.BRAND;
    //String product      = Build.PRODUCT;
    //String model        = Build.MODEL;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_3);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn3);
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

        btn_saveData = (Button) findViewById(R.id.btn_save_fn3);
       // manufacturer = (TextView) findViewById(R.id.manufacturer);
        date = (TextView) findViewById(R.id.datefn3);
        tv_ReadResultFn3 = findViewById(R.id.tv_ReadResultFn3);
//        numlocation = (Spinner) findViewById(R.id.spinner_fnsafety3_0);
//        locationfn3 = (Spinner) findViewById(R.id.spinner_fnsafety3_1);
//        typedevicefn3 = (Spinner) findViewById(R.id.spinner_fnsafety3_2);
        generalityfn3_1 = (Spinner) findViewById(R.id.spinner_fnsafety3_3);
        notationFn3_1 = (EditText) findViewById(R.id.notationFn3_1);
        generalityfn3_2 = (Spinner) findViewById(R.id.spinner_fnsafety3_4);
        notationFn3_2 = (EditText) findViewById(R.id.notationFn3_2);
        generalityfn3_3 =  (Spinner) findViewById(R.id.spinner_fnsafety3_5);
        notationFn3_3 = (EditText) findViewById(R.id.notationFn3_3);
        generalityfn3_4 = (Spinner) findViewById(R.id.spinner_fnsafety3_6);
        notationFn3_4 = (EditText) findViewById(R.id.notationFn3_4);
        generalityfn3_5 = (Spinner) findViewById(R.id.spinner_fnsafety3_7);
        notationFn3_5 = (EditText) findViewById(R.id.notationFn3_5);
        generalityfn3_6  = (Spinner) findViewById(R.id.spinner_fnsafety3_8);
        notationFn3_6 = (EditText) findViewById(R.id.notationFn3_6);
        btnQRScannerFn3 = (Button) findViewById(R.id.btnQRScannerFn3);
        //nameDevicefn3 = (TextView) findViewById(R.id.nameDevicefn3);
        //namesignfn3 = (EditText) findViewById(R.id.Signfn3);
        //nameEdSignfn3 = (EditText) findViewById(R.id.ed_Signaturefn3);
        im_back_arrowfn3 = (ImageView) findViewById(R.id.im_back_arrowfn3);
        im_back_arrowfn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_3.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });


        btn_saveData = (Button) findViewById(R.id.btn_save_fn3);
        btn_saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String numlocat = numlocation.getSelectedItem().toString();
//                String locat = locationfn3.getSelectedItem().toString();
//                String type = typedevicefn3.getSelectedItem().toString();
                String gener1 = generalityfn3_1.getSelectedItem().toString();
                String gener2 = generalityfn3_2.getSelectedItem().toString();
                String gener3 = generalityfn3_3.getSelectedItem().toString();
                String gener4 = generalityfn3_4.getSelectedItem().toString();
                String gener5 = generalityfn3_5.getSelectedItem().toString();
                String gener6 = generalityfn3_6.getSelectedItem().toString();

//                if (TextUtils.isEmpty(numlocat)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(locat)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(type)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(gener1)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gener2)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gener3)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gener4)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gener5)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gener6)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (v == btn_saveData){
                    addDataFn3();
                    Intent intent = new Intent(FnSafety_3.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_3.this,ScanQRFn_3.class));
            }
        });

//        numlocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                //ตำแหน่งของ spinner ที่เลือก
//                int getPositionFirstSpinner = numlocation.getSelectedItemPosition();
//
//                //get array string ของ spinner 2 จาก strings.xml
//                String[] secondSpinnerStr;
//                secondSpinnerStr = getResources().getStringArray(R.array.location_Fn3);
//
//                //ถ้ากด spinner แรกเป็น No.1, No.2, No.4
//                if(getPositionFirstSpinner == 0 || getPositionFirstSpinner == 1 ||  getPositionFirstSpinner == 3 ) {
//                    //set spinner 2 เป็น Co2
//                    locationfn3.setSelection()
//                }
//            }
//
//        });
//
//        locationfn3.setOnItemClickListener (new View.OnClickListener() {
//            onItemSelected {
//                //ทำเหมือนอันแรก
//            }
//        })
//





//        listViewSafety3 = (ListView) findViewById(R.id.listViewSafety3);
//        listViewSafety3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                FireExtinguisher uisher = fireExtinguishers.get(i);
//                showDialog(uisher);
//                return false;
//            }
//        });

        fireExtinguishers = new ArrayList<>();

    }

    private void addDataFn3() {

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn3.setText(DeviceName);

//        DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        manufacturer.setText(DeviceModel);
//        manufacturer.setText(DeviceName);

        String btn = btn_saveData.getText().toString();
        String datetime = date.getText().toString();
        String result3 =tv_ReadResultFn3.getText().toString();
       // String Sign = namesignfn3.getText().toString();
       // String edSign = nameEdSignfn3.getText().toString();
        //String numlocat = numlocation.getSelectedItem().toString();
        //String locat = locationfn3.getSelectedItem().toString();
       // String type = typedevicefn3.getSelectedItem().toString();
        String genera1 = generalityfn3_1.getSelectedItem().toString();
        String note1 =  notationFn3_1.getText().toString();
        String genera2 = generalityfn3_2.getSelectedItem().toString();
        String note2 =  notationFn3_2.getText().toString();
        String genera3 = generalityfn3_3.getSelectedItem().toString();
        String note3 =  notationFn3_3.getText().toString();
        String genera4 = generalityfn3_4.getSelectedItem().toString();
        String note4 =  notationFn3_4.getText().toString();
        String genera5 = generalityfn3_5.getSelectedItem().toString();
        String note5 =  notationFn3_5.getText().toString();
        String genera6 = generalityfn3_6.getSelectedItem().toString();
        String note6 =  notationFn3_6.getText().toString();
       // String nameDevice =  nameDevicefn3.getText().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(genera1)){

            String id = firebaseReference.child("CheckFireExtinguisherFn3").push().getKey();

            FireExtinguisher uisher = new FireExtinguisher();

            //osVersion = android.os.Build.VERSION.RELEASE;
            //DeviceModel= android.os.Build.MODEL;
//            DeviceName= android.os.Build.MANUFACTURER;

            // manufacturer.setText(DeviceModel);
//            manufacturer.setText(DeviceName);

            uisher.setId_fn3(id);
            uisher.setDate_fn3(datetime);
            uisher.setResult_fn3(result3);
            //uisher.setNum_locatfn3(numlocat);
            //uisher.setLocat_fn3(locat);
           // uisher.setType_devicefn3(type);
            uisher.setGeneralityfn3_1(genera1);
            uisher.setNotationFn3_1(note1);
            uisher.setGeneralityfn3_2(genera2);
            uisher.setNotationFn3_2(note2);
            uisher.setGeneralityfn3_3(genera3);
            uisher.setNotationFn3_3(note3);
            uisher.setGeneralityfn3_4(genera4);
            uisher.setNotationFn3_4(note4);
            uisher.setGeneralityfn3_5(genera5);
            uisher.setNotationFn3_5(note5);
            uisher.setGeneralityfn3_6(genera6);
            uisher.setNotationFn3_6(note6);
           // uisher.setManufacturer_fn3(nameDevice);
           // uisher.setSignfn3(Sign);
            //uisher.setEdSignfn3(edSign);

            firebaseReference.child("CheckFireExtinguisherFn3").child(id).setValue(uisher);

            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please fill your information completely", Toast.LENGTH_LONG).show();
        }
    }

    private void showData() {
        Query query = firebaseReference.child("CheckFireExtinguisherFn3");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireExtinguishers.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    FireExtinguisher uisher = postSnapshot.getValue(FireExtinguisher.class);
                    fireExtinguishers.add(uisher);
                }
//                safetyFn3Adapter = new SafetyFn3Adapter(fireExtinguishers);
//                listViewSafety3.setAdapter(safetyFn3Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private  boolean updateData(String id,String date,String num,String locat,String type,String gener1,String gener2,String gener3,String gener4,String gener5,String gener6,String Sign,String edSign) {
//        //updating
//        FireExtinguisher uisher = new FireExtinguisher();
//        uisher.setId_fn3(id);
//        uisher.setDate_fn3(date);
//        uisher.setNum_locatfn3(num);
//        uisher.setLocat_fn3(locat);
//        uisher.setType_devicefn3(type);
//        uisher.setGeneralityfn3_1(gener1);
//        uisher.setGeneralityfn3_2(gener2);
//        uisher.setGeneralityfn3_3(gener3);
//        uisher.setGeneralityfn3_4(gener4);
//        uisher.setGeneralityfn3_5(gener5);
//        uisher.setGeneralityfn3_6(gener6);
//        uisher.setSignfn3(Sign);
//        uisher.setEdSignfn3(edSign);
//
//        firebaseReference.child("CheckFireExtinguisherFn3").child(id).setValue(uisher);
//        Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//        private boolean deleteData(String id) {
//        // removing
//            firebaseReference.child("CheckFireExtinguisherFn3").child(id).removeValue();
//            Toast.makeText(this,"Data Deletd",Toast.LENGTH_LONG).show();
//
//            return true;
//        }
//
//        private int getIndex(Spinner spinner, String myString) {
//           int index = 0;
//
//            for (int i = 0; i < spinner.getCount(); i++) {
//                if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
//                   index = i;
//                   break;
//            }
//        }
//        return index;
//    }
//
//    private  void showDialog(final FireExtinguisher uisher) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.fn_safety3_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final TextView datetime = (TextView) dialogView.findViewById(R.id.datefn3);
//        final Spinner spinnernum = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_0);
//        final Spinner spinnerlocat = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_1);
//        final Spinner spinnertypeDevice = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_2);
//        final Spinner spinnergenerality1 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_3);
//        final Spinner spinnergenerality2 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_4);
//        final Spinner spinnergenerality3 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_5);
//        final Spinner spinnergenerality4 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_6);
//        final Spinner spinnergenerality5 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_7);
//        final Spinner spinnergenerality6 = (Spinner) dialogView.findViewById(R.id.spinner_fnsafety3_8);
//        final EditText nameSign = (EditText) dialogView.findViewById(R.id.Signfn3);
//        final EditText nameEdSign = (EditText) dialogView.findViewById(R.id.ed_Signaturefn3);
//        final Button btnUpdatefn3 = (Button) dialogView.findViewById(R.id.btnUpdatefn3);
//        final Button btnDeletefn3 = (Button) dialogView.findViewById(R.id.btnDeletefn3);
//
//
//        dialogBuilder.setTitle(uisher.getDate_fn3());
//        datetime.setText(uisher.getDate_fn3());
//        spinnernum.setSelection(getIndex(spinnernum,uisher.getNum_locatfn3()));
//        spinnerlocat.setSelection(getIndex(spinnerlocat,uisher.getLocat_fn3()));
//        spinnertypeDevice.setSelection(getIndex(spinnertypeDevice,uisher.getType_devicefn3()));
//        spinnergenerality1.setSelection(getIndex(spinnergenerality1,uisher.getGeneralityfn3_1()));
//        spinnergenerality2.setSelection(getIndex(spinnergenerality2,uisher.getGeneralityfn3_2()));
//        spinnergenerality3.setSelection(getIndex(spinnergenerality3,uisher.getGeneralityfn3_3()));
//        spinnergenerality4.setSelection(getIndex(spinnergenerality4,uisher.getGeneralityfn3_4()));
//        spinnergenerality5.setSelection(getIndex(spinnergenerality5,uisher.getGeneralityfn3_5()));
//        spinnergenerality6.setSelection(getIndex(spinnergenerality6,uisher.getGeneralityfn3_6()));
//        dialogBuilder.setTitle(uisher.getSignfn3());
//        nameSign.setText(uisher.getSignfn3());
//        dialogBuilder.setTitle(uisher.getEdSignfn3());
//        nameEdSign.setText(uisher.getEdSignfn3());
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        btnUpdatefn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String date = datetime.getText().toString();
//                String num = spinnernum.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
//                String type = spinnertypeDevice.getSelectedItem().toString();
//                String gener1 = spinnergenerality1.getSelectedItem().toString();
//                String gener2 = spinnergenerality2.getSelectedItem().toString();
//                String gener3 = spinnergenerality3.getSelectedItem().toString();
//                String gener4 = spinnergenerality4.getSelectedItem().toString();
//                String gener5 = spinnergenerality5.getSelectedItem().toString();
//                String gener6 = spinnergenerality6.getSelectedItem().toString();
//                String sign = nameSign.getText().toString();
//                String edSign = nameEdSign.getText().toString();
//                if (!TextUtils.isEmpty(locat)){
//                    updateData(uisher.getId_fn3(), date,num,locat,type,gener1,gener2,gener3,gener4,gener5,gener6,sign,edSign);
//                    alertDialog.dismiss();
//                }
//            }
//        });
//
//        btnDeletefn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData(uisher.getId_fn3());
//                alertDialog.dismiss();
//            }
//        });
//    }
}