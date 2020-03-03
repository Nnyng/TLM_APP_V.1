package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class FnSafety_4 extends AppCompatActivity {
    private EditText notationFnSafety4,Sign,ed_Sign;
    private Spinner spinner_location,spinner_type,spinner_generality;
    public static TextView date,nameDevicefn4,ReadResultFn4;
    private Button  btn_save_fn4,btnQRScannerFn4;
    private ListView listViewSafety4;
    private List<FireExitDoors>fireExitDoors;
    private SafetyFn4Adapter safetyFn4Adapter;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrowfn4;
    String DeviceModel, DeviceName;

    //String manufacturer = Build.MANUFACTURER;
    //String brand        = Build.BRAND;
    //String product      = Build.PRODUCT;
    //String model        = Build.MODEL;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_4);

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
                                TextView tdate = (TextView) findViewById(R.id.datefn4);
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

    private void initInstances(){

        btn_save_fn4 = (Button) findViewById(R.id.btn_save_fn4);
        date = (TextView) findViewById(R.id.datefn4);
        ReadResultFn4 = (TextView) findViewById(R.id.tv_ReadResultFn4);
        //spinner_location = (Spinner) findViewById(R.id.spinner_fnsafety4_1);
        //spinner_type = (Spinner) findViewById(R.id.spinner_fnsafety4_2);
        spinner_generality = (Spinner) findViewById(R.id.spinner_fnsafety4_3);
        //nameDevicefn4 = (TextView) findViewById(R.id.nameDevicefn4);
        notationFnSafety4 = (EditText) findViewById(R.id.notationFn4) ;
        //Sign = (EditText) findViewById(R.id.SignatureFn4);
        //ed_Sign = (EditText)findViewById(R.id.ed_SigninspectorFn4);
        im_back_arrowfn4 = (ImageView) findViewById(R.id.im_back_arrowfn4);
        btnQRScannerFn4 = (Button) findViewById(R.id.btnQRScannerFn4);
        im_back_arrowfn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_4.this,Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save_fn4 = (Button) findViewById(R.id.btn_save_fn4);
        btn_save_fn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String location = spinner_location.getSelectedItem().toString();
                //String type = spinner_type.getSelectedItem().toString();
                String generality = spinner_generality.getSelectedItem().toString();
                String notation = notationFnSafety4.getText().toString();
//                if(TextUtils.isEmpty(location)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(type)){
//                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(generality)){
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(notation)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if  (v == btn_save_fn4) {
                    addDataFn4();
                    Intent intent = new Intent(FnSafety_4.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_4.this,ScanQRFn_4.class));
            }
        });


//        listViewSafety4 = (ListView) findViewById(R.id.listViewSafety4);
//        listViewSafety4.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                FireExitDoors fire4 = fireExitDoors.get(i);
//                showDialog(fire4);
//                return false;
//            }
//        });

        fireExitDoors = new ArrayList<>();

    }


    private void addDataFn4(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn4.setText(DeviceName);



        String btn = btn_save_fn4.getText().toString();
        String datetime = date.getText().toString();
        String result4 = ReadResultFn4.getText().toString();
        //String location = spinner_location.getSelectedItem().toString();
        //String type = spinner_type.getSelectedItem().toString();
        String generality = spinner_generality.getSelectedItem().toString();
       // String nameDevice = nameDevicefn4.getText().toString();
        String note = notationFnSafety4.getText().toString();
        //String signfn4 = Sign.getText().toString();
        //String ed_signfn4  = ed_Sign.getText().toString();

        if (!TextUtils.isEmpty(generality)){
            String id = firebaseReference.child("CheckFireExitDoors").push().getKey();

            FireExitDoors fire4 = new FireExitDoors();

//            //osVersion = android.os.Build.VERSION.RELEASE;
//            //DeviceModel= android.os.Build.MODEL;
//            DeviceName= android.os.Build.MANUFACTURER;
//
//            // manufacturer.setText(DeviceModel);
//            manufacturer.setText(DeviceName);

            fire4.setId_fn4(id);
            fire4.setDete_fn4(datetime);
            fire4.setResult_fn4(result4);
            //fire4.setLocat_fn4(location);
            //fire4.setType_fn4(type);
            fire4.setGenerality_fn4(generality);
           // fire4.setManufacturer_fn4(nameDevice);
            fire4.setNonation_fn4(note);
           // fire4.setSignature_fn4(signfn4);
           // fire4.setEd_signspector_fn4(ed_signfn4);

            firebaseReference.child("CheckFireExitDoorsFn4").child(id).setValue(fire4);

            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();

        }else {
            //if the value is not given displaying a toast
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();

        }
    }

    private void showData(){
        Query query = firebaseReference.child("CheckFireExitDoorsFn4");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireExitDoors.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    FireExitDoors fire4 = postSnapshot.getValue(FireExitDoors.class);
                    fireExitDoors.add(fire4);
                }
//                safetyFn4Adapter = new SafetyFn4Adapter(fireExitDoors);
//                listViewSafety4.setAdapter(safetyFn4Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private boolean updateData(String id,String date,String locat,String type,String gener,String note,String sign,String ed_sign){
//        //updating
//        FireExitDoors fire4 = new FireExitDoors();
//        fire4.setId_fn4(id);
//        fire4.setDete_fn4(date);
//        fire4.setLocat_fn4(locat);
//        fire4.setType_fn4(type);
//        fire4.setGenerality_fn4(gener);
//        fire4.setNonation_fn4(note);
//        fire4.setSignature_fn4(sign);
//        fire4.setEd_signspector_fn4(ed_sign);
//
//        firebaseReference.child("CheckFireExitDoorsFn4").child(id).setValue(fire4);
//        Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    private boolean deleteData(String id) {
//        // removing
//        firebaseReference.child("CheckFireExitDoorsFn4").child(id).removeValue();
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
//    private void showDialog(final FireExitDoors fire4) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.fn_safety4_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final ImageButton ib_exit4 = (ImageButton) dialogView.findViewById(R.id.ib_exitfn4);
//        final TextView datetime = (TextView) dialogView.findViewById(R.id.date4);
//        final Spinner spinnerlocat = (Spinner) dialogView.findViewById(R.id.locatfn4_1);
//        final Spinner spinnertype = (Spinner) dialogView.findViewById(R.id.typefn4_2);
//        final Spinner spinnergener = (Spinner) dialogView.findViewById(R.id.generfn4_3);
//        final EditText notation = (EditText) dialogView.findViewById(R.id.noteFn4);
//        final EditText sign = (EditText)dialogView.findViewById(R.id.Signfn4);
//        final EditText ed_sign = (EditText) dialogView.findViewById(R.id.ed_Signfn4);
//        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdatefn4);
//        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDeletefn4);
//
//        dialogBuilder.setTitle(fire4.getDete_fn4());
//        datetime.setText(fire4.getDete_fn4());
//        spinnerlocat.setSelection(getIndex(spinnerlocat,fire4.getLocat_fn4()));
//        spinnertype.setSelection(getIndex(spinnertype,fire4.getType_fn4()));
//        spinnergener.setSelection(getIndex(spinnergener,fire4.getGenerality_fn4()));
//        dialogBuilder.setTitle(fire4.getNonation_fn4());
//        notation.setText(fire4.getNonation_fn4());
//        dialogBuilder.setTitle(fire4.getSignature_fn4());
//        sign.setText(fire4.getSignature_fn4());
//        dialogBuilder.setTitle(fire4.getEd_signspector_fn4());
//        ed_sign.setText(fire4.getEd_signspector_fn4());
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        ib_exit4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FnSafety_4.this, FnSafety_4.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String date = datetime.getText().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
//                String type = spinnertype.getSelectedItem().toString();
//                String genera = spinnergener.getSelectedItem().toString();
//                String note = notation.getText().toString();
//                String signature = sign.getText().toString();
//                String edSignator = ed_sign.getText().toString();
//                if (!TextUtils.isEmpty(locat)){
//                    updateData(fire4.getId_fn4(),date,locat,type,genera,note,signature,edSignator);
//                    alertDialog.dismiss();
//
//                }
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData(fire4.getId_fn4());
//                alertDialog.dismiss();
//            }
//        });
//    }
}
