package com.example.tlm_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Calendar;
import java.util.List;

public class FnSafety_5 extends AppCompatActivity {
    private EditText Signature,ed_Signature,notationFn5_1,notationFn5_2,notationFn5_3,notationFn5_4,notationFn5_5,notationFn5_6;
    private Spinner spinner_numdevice,spinner_locat,spinner_devicetype,spinner_gener5_1,spinner_gener5_2,spinner_gener5_3,spinner_gener5_4,spinner_gener5_5,spinner_gener5_6;
    public static TextView dateAlert,CalendarText,nameDevicefn5,ReadResultFn5;
    private Button btn_save_fb5,btnQRScannerFn5;
    private ListView listViewSafety5;
    private List<FIRECABINETS>firecabinets;
    private SafetyFn5Adapter safetyFn5Adapter;
    private DatabaseReference firebaseReference;
    private ImageView imCalendar, im_back_arrowfn5;
    String DeviceModel, DeviceName;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String date;
    TextView tvCalendarTextFn5;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_5);

        initInstances();
        initFirebase();
        showData();

        tvCalendarTextFn5 = findViewById(R.id.tvCalendarTextFn5);
        tvCalendarTextFn5.setText(date);
        //ImageView imCalendar = (ImageView) findViewById(R.id.imCalendar);

        date = getIntent().getStringExtra("date");
        Log.e("date", "onCreate: "+date);


//        imCalendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calendar = Calendar.getInstance();
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                datePickerDialog = new DatePickerDialog(FnSafety_5.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                                tvCalendarTextFn5.setText(day + "/" + (month + 1) + "/" + year);
//                            }
//                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//                datePickerDialog.show();
//            }
//        });



        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.datefn5);
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

    private void initInstances(){

        btn_save_fb5 = (Button) findViewById(R.id.btn_save_fb5);
        dateAlert = (TextView)findViewById(R.id.datefn5);
        ReadResultFn5 = (TextView)findViewById(R.id.tv_ReadResultFn5);
//        spinner_numdevice = (Spinner)findViewById(R.id.spinner_fnsafety5_1);
//        spinner_locat = (Spinner)findViewById(R.id.spinner_fnsafety5_2);
//        spinner_devicetype = (Spinner)findViewById(R.id.spinner_fnsafety5_3);
        spinner_gener5_1 = (Spinner)findViewById(R.id.spinner_fnsafety5_4);
        notationFn5_1  = (EditText) findViewById(R.id.notationFn5_1);
        spinner_gener5_2 = (Spinner)findViewById(R.id.spinner_fnsafety5_5);
        notationFn5_2  = (EditText) findViewById(R.id.notationFn5_2);
        spinner_gener5_3 = (Spinner)findViewById(R.id.spinner_fnsafety5_6);
        notationFn5_3  = (EditText) findViewById(R.id.notationFn5_3);
        spinner_gener5_4 = (Spinner)findViewById(R.id.spinner_fnsafety5_7);
        notationFn5_4  = (EditText) findViewById(R.id.notationFn5_4);
        spinner_gener5_5 = (Spinner)findViewById(R.id.spinner_fnsafety5_8);
        notationFn5_5  = (EditText) findViewById(R.id.notationFn5_5);
        spinner_gener5_6 = (Spinner)findViewById(R.id.spinner_fnsafety5_9);
        notationFn5_6  = (EditText) findViewById(R.id.notationFn5_6);
        CalendarText = (TextView)findViewById(R.id.tvCalendarTextFn5);
        btnQRScannerFn5 = (Button) findViewById(R.id.btnQRScannerFn5);
        //nameDevicefn5 = (TextView) findViewById(R.id.nameDevicefn5);
        //Signature = (EditText) findViewById(R.id.SignatureFn5);
        //ed_Signature = (EditText) findViewById(R.id.ed_SigninspectorFn5);
        im_back_arrowfn5 = (ImageView) findViewById(R.id.im_back_arrowfn5);
        im_back_arrowfn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_5.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });
        imCalendar = (ImageView) findViewById(R.id.imCalendar);
        imCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FnSafety_5.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tvCalendarTextFn5.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

         btn_save_fb5 = (Button) findViewById(R.id.btn_save_fb5);
         btn_save_fb5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

//                String num = spinner_numdevice.getSelectedItem().toString();
//                String location =  spinner_locat.getSelectedItem().toString();
//                String device = spinner_devicetype.getSelectedItem().toString();
                String generality1 = spinner_gener5_1.getSelectedItem().toString();
                 String notation1 = notationFn5_1.getText().toString();
                String generality2 = spinner_gener5_2.getSelectedItem().toString();
                 String notation2 = notationFn5_2.getText().toString();
                String generality3 = spinner_gener5_3.getSelectedItem().toString();
                 String notation3 = notationFn5_3.getText().toString();
                String generality4 = spinner_gener5_4.getSelectedItem().toString();
                 String notation4 = notationFn5_4.getText().toString();
                String generality5 = spinner_gener5_5.getSelectedItem().toString();
                 String notation5 = notationFn5_5.getText().toString();
                String generality6 = spinner_gener5_6.getSelectedItem().toString();
                 String notation6 = notationFn5_6.getText().toString();
                String calendar = tvCalendarTextFn5.getText().toString();
                //String Sign = Signature.getText().toString();
               // String ed_Sign = ed_Signature.getText().toString();

//                 if(TextUtils.isEmpty(num)){
//                     Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                     return;
//                 }
//                 if(TextUtils.isEmpty(location)){
//                     Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                     return;
//                 }
//                 if(TextUtils.isEmpty(device)){
//                     Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ!",Toast.LENGTH_SHORT).show();
//                     return;
//                 }
                 if(TextUtils.isEmpty(generality1)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(TextUtils.isEmpty(generality2)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(TextUtils.isEmpty(generality3)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(TextUtils.isEmpty(generality4)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(TextUtils.isEmpty(generality5)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 if(TextUtils.isEmpty(generality6)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;

                 }
                 if(TextUtils.isEmpty(calendar)) {
                     Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                     return;

                 }
                 if  (v == btn_save_fb5) {
                     addDataFn5();
                     Intent intent = new Intent(FnSafety_5.this, Fn_Safety.class);
                     startActivity(intent);
                     finish();
                 }
             }
         });

        btnQRScannerFn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_5.this,ScanQRFn_5.class));
            }
        });


//         listViewSafety5 = (ListView) findViewById(R.id.listViewSafety5);
//         listViewSafety5.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//             @Override
//             public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                 FIRECABINETS fire5 = firecabinets.get(i);
//                 showDialog(fire5);
//                 return false;
//             }
//         });

         firecabinets = new ArrayList<>();

    }

    private void addDataFn5(){

//        //DeviceModel= android.os.Build.MODEL;
//        DeviceName= android.os.Build.MANUFACTURER;
//        //manufacturer.setText(DeviceModel);
//        nameDevicefn5.setText(DeviceName);

       String datetime = dateAlert.getText().toString();
       String result5 = ReadResultFn5.getText().toString();
//        String numdevice = spinner_numdevice.getSelectedItem().toString();
//        String locat = spinner_locat.getSelectedItem().toString();
//        String deviceType = spinner_devicetype.getSelectedItem().toString();
        String gener1 = spinner_gener5_1.getSelectedItem().toString();
        String note1 = notationFn5_1.getText().toString();
        String gener2 = spinner_gener5_2.getSelectedItem().toString();
        String note2 = notationFn5_2.getText().toString();
        String gener3 = spinner_gener5_3.getSelectedItem().toString();
        String note3 = notationFn5_3.getText().toString();
        String gener4 = spinner_gener5_4.getSelectedItem().toString();
        String note4 = notationFn5_4.getText().toString();
        String gener5 = spinner_gener5_5.getSelectedItem().toString();
        String note5 = notationFn5_5.getText().toString();
        String gener6 = spinner_gener5_6.getSelectedItem().toString();
        String note6 = notationFn5_6.getText().toString();
      //  String nameDevice = nameDevicefn5.getText().toString();
        String tvcalendar = tvCalendarTextFn5.getText().toString();
      //  String sign = Signature.getText().toString();
       // String ed_Sign = ed_Signature.getText().toString();

        //checking if the value is provided
        if(!TextUtils.isEmpty(gener1)){
            String id = firebaseReference.child("CHECKFIRECABINETSFn5").push().getKey();

            FIRECABINETS fire5 = new FIRECABINETS();


           fire5.setId_fn5(id);
           fire5.setDate_fn5(datetime);
           fire5.setResult_fn5(result5);
//           fire5.setNumdevice_fn5(numdevice);
//           fire5.setLocat_fn5(locat);
//           fire5.setDevice_typefn5(deviceType);
           fire5.setGenerality_fn5_1(gener1);
           fire5.setNotationFn5_1(note1);
           fire5.setGenerality_fn5_2(gener2);
            fire5.setNotationFn5_2(note2);
           fire5.setGenerality_fn5_3(gener3);
            fire5.setNotationFn5_3(note3);
           fire5.setGenerality_fn5_4(gener4);
            fire5.setNotationFn5_4(note4);
           fire5.setGenerality_fn5_5(gener5);
            fire5.setNotationFn5_5(note5);
           fire5.setGenerality_fn5_6(gener6);
            fire5.setNotationFn5_6(note6);
           fire5.setDateTest_fn5(tvcalendar);

          // fire5.setManufacturer_fn5(nameDevice);
          // fire5.setSignature_fn5(sign);
          // fire5.setEd_signspector_fn5(ed_Sign);

           firebaseReference.child("CHECKFIRECABINETSFn5").child(id).setValue(fire5);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely", Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("CHECKFIRECABINETSFn5");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firecabinets.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FIRECABINETS fire5 = postSnapshot.getValue(FIRECABINETS.class);
                    firecabinets.add(fire5);
                }

//                safetyFn5Adapter = new SafetyFn5Adapter(firecabinets);
//                listViewSafety5.setAdapter(safetyFn5Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private boolean updateData(String id,String date,String num,String locat,String devicetype,String generat1,String generat2,String generat3,String generat4,String generat5,String generat6,String tvcalendar,String Sign,String ed_Sign){
//        //updating
//        FIRECABINETS fire5 = new FIRECABINETS();
//        fire5.setId_fn5(id);
//        fire5.setDate_fn5(date);
//        fire5.setNumdevice_fn5(num);
//        fire5.setLocat_fn5(locat);
//        fire5.setDevice_typefn5(devicetype);
//        fire5.setGenerality_fn5_1(generat1);
//        fire5.setGenerality_fn5_2(generat2);
//        fire5.setGenerality_fn5_3(generat3);
//        fire5.setGenerality_fn5_4(generat4);
//        fire5.setGenerality_fn5_5(generat5);
//        fire5.setGenerality_fn5_6(generat6);
//        fire5.setDateTest_fn5(tvcalendar);
//        fire5.setSignature_fn5(Sign);
//        fire5.setEd_signspector_fn5(ed_Sign);
//
//        firebaseReference.child("CHECKFIRECABINETSFn5").child(id).setValue(fire5);
//        Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    private boolean deleteData(String id) {
//        // removing
//        firebaseReference.child("CHECKFIRECABINETSFn5").child(id).removeValue();
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
//    private void showDialog(final FIRECABINETS fire5){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.fn_safety5_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final ImageView exitfn5 = (ImageView) dialogView.findViewById(R.id.ib_exitfn5);
//        final TextView datetime = (TextView) dialogView.findViewById(R.id.date5);
//        final Spinner spinnernum = (Spinner)dialogView.findViewById(R.id.numdevice);
//        final Spinner spinnerlocat = (Spinner)dialogView.findViewById(R.id.location);
//        final Spinner spinnertype = (Spinner)dialogView.findViewById(R.id.device_type);
//        final Spinner spinnergenerality1 = (Spinner)dialogView.findViewById(R.id.gener5_1);
//        final Spinner spinnergenerality2 = (Spinner)dialogView.findViewById(R.id.gener5_2);
//        final Spinner spinnergenerality3 = (Spinner)dialogView.findViewById(R.id.gener5_3);
//        final Spinner spinnergenerality4 = (Spinner)dialogView.findViewById(R.id.gener5_4);
//        final Spinner spinnergenerality5 = (Spinner)dialogView.findViewById(R.id.gener5_5);
//        final Spinner spinnergenerality6 = (Spinner)dialogView.findViewById(R.id.gener5_6);
//        final TextView calendar = (TextView)dialogView.findViewById(R.id.dateTest);
//        final EditText Sign = (EditText)dialogView.findViewById(R.id.Signfn5);
//        final EditText ed_Sign = (EditText)dialogView.findViewById(R.id.ed_Signfn5);
//        final Button btnUpdate = (Button)dialogView.findViewById(R.id.btnUpdatefn5);
//        final Button btnDelect = (Button)dialogView.findViewById(R.id.btnDeletefn5);
//
//        dialogBuilder.setTitle(fire5.getDate_fn5());
//        datetime.setText(fire5.getDate_fn5());
//        spinnernum.setSelection(getIndex(spinnernum,fire5.getNumdevice_fn5()));
//        spinnerlocat.setSelection(getIndex(spinnerlocat,fire5.getLocat_fn5()));
//        spinnertype.setSelection(getIndex(spinnertype,fire5.getDevice_typefn5()));
//        spinnergenerality1.setSelection(getIndex(spinnergenerality1,fire5.getGenerality_fn5_1()));
//        spinnergenerality2.setSelection(getIndex(spinnergenerality2,fire5.getGenerality_fn5_2()));
//        spinnergenerality3.setSelection(getIndex(spinnergenerality3,fire5.getGenerality_fn5_3()));
//        spinnergenerality4.setSelection(getIndex(spinnergenerality4,fire5.getGenerality_fn5_4()));
//        spinnergenerality5.setSelection(getIndex(spinnergenerality5,fire5.getGenerality_fn5_5()));
//        spinnergenerality6.setSelection(getIndex(spinnergenerality6,fire5.getGenerality_fn5_6()));
//        dialogBuilder.setTitle(fire5.getDateTest_fn5());
//        calendar.setText(fire5.getDateTest_fn5());
//        dialogBuilder.setTitle(fire5.getSignature_fn5());
//        Sign.setText(fire5.getSignature_fn5());
//        dialogBuilder.setTitle(fire5.getEd_signspector_fn5());
//        ed_Sign.setText(fire5.getEd_signspector_fn5());
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String date = datetime.getText().toString();
//                String num = spinnernum.getSelectedItem().toString();
//                String locat = spinnerlocat.getSelectedItem().toString();
//                String type = spinnertype.getSelectedItem().toString();
//                String gener1 = spinnergenerality1.getSelectedItem().toString();
//                String gener2 = spinnergenerality2.getSelectedItem().toString();
//                String gener3 = spinnergenerality3.getSelectedItem().toString();
//                String gener4 = spinnergenerality4.getSelectedItem().toString();
//                String gener5 = spinnergenerality5.getSelectedItem().toString();
//                String gener6 = spinnergenerality6.getSelectedItem().toString();
//                String tvcalendar = calendar.getText().toString();
//                String Signfn5 = Sign.getText().toString();
//                String ed_Signfn5 = ed_Sign.getText().toString();
//                if(!TextUtils.isEmpty(locat)){
//                    updateData(fire5.getId_fn5(),date,num,locat,type,gener1,gener2,gener3,gener4,gener5,gener6,tvcalendar,Signfn5,ed_Signfn5);
//                    alertDialog.dismiss();
//                }
//            }
//        });
//
//        btnDelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData(fire5.getId_fn5());
//                alertDialog.dismiss();
//            }
//        });
//    }
}