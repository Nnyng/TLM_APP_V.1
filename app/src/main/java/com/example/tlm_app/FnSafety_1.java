package com.example.tlm_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
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
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class FnSafety_1 extends AppCompatActivity {
    private EditText notationFnSafety1, ed_Signature, position_ed_Signature, ed_Signinspector, position_ed_Signinspector;
    private Spinner spinner_deviceType, spinner_fntotalType, spinner_fntotal, spinner_fnlocation, spinner_fngenerality;
    public static TextView date, nDevice,tv_ReadResultFn1;
    private Button btn_save,btnQRScannerFn1;
    private ListView listViewSafety1;
    private List<FireFightingEquipment> fireFightingEquipment;
    private SafetyFn1Adapter safetyFn1Adapter;
    private DatabaseReference firebaseReference;
    private ImageView im_back_arrow;
    String DeviceModel, DeviceName;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    private TextView latitude, longitude ;

//    String manufacturer = Build.MANUFACTURER;
//    String brand        = Build.BRAND;
//    String product      = Build.PRODUCT;
//    String model        = Build.MODEL;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fn_safety_1);


        initInstances();
        initFirebase();
        showData();

        getLastLocation();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
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

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    latitude.setText(location.getLatitude()+"");
                                    longitude.setText(location.getLongitude()+"");
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }

    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude.setText(mLastLocation.getLatitude()+"");
            longitude.setText(mLastLocation.getLongitude()+"");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }


    private void initFirebase() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();

    }

    private void initInstances() {

        ///// getLocation //////
        latitude  = findViewById(R.id.latTextView);
        longitude  = findViewById(R.id.lonTextView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        btnQRScannerFn1 = (Button) findViewById(R.id.btnQRScannerFn1);
        btn_save = (Button) findViewById(R.id.btn_save);
        nDevice = (TextView) findViewById(R.id.manufacturer);
        date = (TextView) findViewById(R.id.date);
        tv_ReadResultFn1 = findViewById(R.id.tv_ReadResultFn1);
       // ed_Signature = (EditText) findViewById(R.id.ed_Signature);
        //position_ed_Signature = (EditText) findViewById(R.id.position_ed_Signature);
        //ed_Signinspector = (EditText) findViewById(R.id.ed_Signinspector);
        //position_ed_Signinspector = (EditText) findViewById(R.id.position_ed_Signinspector);
        notationFnSafety1 = (EditText) findViewById(R.id.notationFnSafety1);
       // spinner_deviceType = (Spinner) findViewById(R.id.spinner_fnsafety1_2);
       // spinner_fntotalType = (Spinner) findViewById(R.id.spinner_fnsafety1_3_1);
       // spinner_fntotal = (Spinner) findViewById(R.id.spinner_fnsafety1_3);
        spinner_fngenerality = (Spinner) findViewById(R.id.spinner_fnsafety1_4);
       // spinner_fnlocation = (Spinner) findViewById(R.id.spinner_fnsafety1_1);
        im_back_arrow = (ImageView) findViewById(R.id.im_back_arrow);
        im_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FnSafety_1.this, Fn_Safety.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String location = spinner_fnlocation.getSelectedItem().toString();
              //  String device = spinner_deviceType.getSelectedItem().toString();
               // String total = spinner_fntotal.getSelectedItem().toString();
              //  String totalType = spinner_fntotalType.getSelectedItem().toString();
                String gennerality = spinner_fngenerality.getSelectedItem().toString();
                //String namedevice = manufacturer.getText().toString();
                String notation = notationFnSafety1.getText().toString();

//                if (TextUtils.isEmpty(location)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(device)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(total)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(totalType)) {
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

                if (v == btn_save) {
                    addDataFn1();
                    Intent intent = new Intent(FnSafety_1.this, Fn_Safety.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnQRScannerFn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FnSafety_1.this,ScanQRresult.class));
            }
        });

//
//        listViewSafety1 = (ListView) findViewById(R.id.listViewSafety1);
//        listViewSafety1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                FireFightingEquipment fire = fireFightingEquipment.get(i);
//                showDialog(fire);
//                return false;
//            }
//        });

        fireFightingEquipment = new ArrayList<>();


    }


    private void addDataFn1() {


        //DeviceModel= android.os.Build.MODEL;
        DeviceName = Build.MANUFACTURER;
        //manufacturer.setText(DeviceModel);
        nDevice.setText(DeviceName);

        String btn = btn_save.getText().toString();
        String datetime = date.getText().toString();
        String result = tv_ReadResultFn1.getText().toString();
       // String edSign = ed_Signature.getText().toString();
       // String positEdSign = position_ed_Signature.getText().toString();
       // String edSignSpector = ed_Signinspector.getText().toString();
        //String positEdSignSpector = position_ed_Signinspector.getText().toString();
        String nameDevice = nDevice.getText().toString();
        String note = notationFnSafety1.getText().toString();
        //String device = spinner_deviceType.getSelectedItem().toString();
        //String total = spinner_fntotal.getSelectedItem().toString();
        //String totalType = spinner_fntotalType.getSelectedItem().toString();
        String generality = spinner_fngenerality.getSelectedItem().toString();
        String lat = latitude.getText().toString();
        String lon = longitude.getText().toString();
        // String location = spinner_fnlocation.getSelectedItem().toString();


//        Build.BOARD = MSM8974
//        Build.BOOTLOADER = s1
//        Build.BRAND = Sony
//        Build.CPU_ABI = armeabi-v7a
//        Build.CPU_ABI2 = armeabi
//        Build.DEVICE = D5503
//        Build.DISPLAY = 14.6.A.1.236
//        Build.FINGERPRINT = Sony/D5503/D5503:5.1.1/14.6.A.1.236/2031203XXX:user/release-keys
//        Build.HARDWARE = qcom
//        Build.HOST = BuildHost
//        Build.ID = 14.6.A.1.236
//        Build.IS_DEBUGGABLE = false
//        Build.MANUFACTURER = Sony
//        Build.MODEL = D5503
//        Build.PRODUCT = D5503
//        Build.RADIO = unknown
//        Build.SERIAL = CB5A1YGVMT
//        Build.SUPPORTED_32_BIT_ABIS = [Ljava.lang.String;@3dd90541
//        Build.SUPPORTED_64_BIT_ABIS = [Ljava.lang.String;@1da4fc3
//        Build.SUPPORTED_ABIS = [Ljava.lang.String;@525f635
//        Build.TAGS = release-keys
//        Build.TIME = 144792559XXXX
//        Build.TYPE = user
//        Build.UNKNOWN = unknown
//        Build.USER = BuildUser
//        if location = ""  location = "-SELECT-" {
//            // แจ้งเตือน
//            // ออก
//        }


        //checking if the value is provided
        if (!TextUtils.isEmpty(generality)) {
            String id = firebaseReference.child("CheckFireFightingEquipmentFn1").push().getKey();

            FireFightingEquipment fire = new FireFightingEquipment();

//            //osVersion = android.os.Build.VERSION.RELEASE;
//            //DeviceModel= android.os.Build.MODEL;
//            DeviceName = Build.MANUFACTURER;
//
//            // manufacturer.setText(DeviceModel);
//            nDevice.setText(DeviceName);


            fire.setId_fn1(id);
            fire.setDate_fn1(datetime);
            fire.setResult_fn1(result);
           // fire.setLocat_fn1(location);
           // fire.setDevice_fn1(device);
            //fire.setTotal_fn1(total);
            //fire.setTotal_type_fn1(totalType);
            fire.setGenerality_fn1(generality);
            fire.setNameDevice_fn1(nameDevice);
            fire.setNonation_fn1(note);
            fire.setLatitude_fn1(lat);
            fire.setLongitude_fn1(lon);
          //  fire.setSignature_fn1(edSign);
           // fire.setPosition_signature_fn1(positEdSign);
           // fire.setEd_signspector_fn1(edSignSpector);
           // fire.setPosition_ed_signspector_fn1(positEdSignSpector);


            firebaseReference.child("CheckFireFightingEquipmentFn1").child(id).setValue(fire);

            Toast.makeText(this, "Checking Successful", Toast.LENGTH_LONG).show();

        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please fill your information completely", Toast.LENGTH_LONG).show();
        }

    }
// เพิ่มชื่อ เครื่อง
//    public boolean isAnHTCDevice()
//    {
//        String manufacturer = android.os.Build.MANUFACTURER;
//        if (manufacturer.toLowerCase().contains("htc"))
//            return true;
//        else
//            return false;
//    }

    private void showData() {
        Query query = firebaseReference.child("CheckFireFightingEquipmentFn1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireFightingEquipment.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FireFightingEquipment fire = postSnapshot.getValue(FireFightingEquipment.class);
                    fireFightingEquipment.add(fire);
                }
//                safetyFn1Adapter = new SafetyFn1Adapter(fireFightingEquipment);
//                listViewSafety1.setAdapter(safetyFn1Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
//    private boolean updateData(String id, String date, String locat, String device,String total, String totalType, String generality, String manufacturer,String notation, String Sign, String positionSign, String edSignSpec, String positionEdSignSpec){
//        //updating
//        FireFightingEquipment fire = new FireFightingEquipment();
//        fire.setId_fn1(id);
//        fire.setDate_fn1(date);
//        fire.setLocat_fn1(locat);
//        fire.setDevice_fn1(device);
//        fire.setTotal_fn1(total);
//        fire.setTotal_type_fn1(totalType);
//        fire.setGenerality_fn1(generality);
//        fire.setManufacturer_fn1(manufacturer);
//        fire.setNonation_fn1(notation);
//        fire.setSignature_fn1(Sign);
//        fire.setPosition_signature_fn1(positionSign);
//        fire.setEd_signspector_fn1(edSignSpec);
//        fire.setPosition_ed_signspector_fn1(positionEdSignSpec);
//
//
//        firebaseReference.child("CheckFireFightingEquipmentFn1").child(id).setValue(fire);
//        Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//        private boolean deleteData(String id) {
//            // removing
//            firebaseReference.child("CheckFireFightingEquipmentFn1").child(id).removeValue();
//
//            Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
//
//            return true;
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
//    private void showDialog(final FireFightingEquipment fire){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.fn_safety1_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final ImageButton ib_exit = (ImageButton) dialogView.findViewById(R.id.ib_exitfn1);
//        final TextView datetime = (TextView) dialogView.findViewById(R.id.date);
//        final Spinner spinnerlocat = (Spinner) dialogView.findViewById(R.id.spinner_locatfn1);
//        final Spinner spinnerdevice = (Spinner) dialogView.findViewById(R.id.spinner_devicefn1);
//        final Spinner spinnertotal = (Spinner) dialogView.findViewById(R.id.spinner_totalfn1);
//        final Spinner spinnertotalTypr = (Spinner) dialogView.findViewById(R.id.spinner_totalTypefn1);
//        final Spinner spinnergenerality = (Spinner) dialogView.findViewById(R.id.spinner_generalityfn1);
//        final TextView manufacturer = (TextView) dialogView.findViewById(R.id.manufacturer);
//        final EditText note = (EditText) dialogView.findViewById(R.id.notefn1);
//        final EditText sign = (EditText) dialogView.findViewById(R.id.Signfn1);
//        final EditText postiSign = (EditText) dialogView.findViewById(R.id.positionSignfn1);
//        final EditText edSigninspector = (EditText) dialogView.findViewById(R.id.ed_Signinspectorfn1);
//        final EditText positionedSignspector = (EditText) dialogView.findViewById(R.id.position_ed_Signinspectorfn1);
//        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
//        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);
//
//
//        dialogBuilder.setTitle(fire.getDate_fn1());
//        datetime.setText(fire.getDate_fn1());
//        spinnerlocat.setSelection(getIndex(spinnerlocat,fire.getLocat_fn1()));
//        spinnerdevice.setSelection(getIndex(spinnerdevice,fire.getDevice_fn1()));
//        spinnertotal.setSelection(getIndex(spinnertotal,fire.getTotal_fn1()));
//        spinnertotalTypr.setSelection(getIndex(spinnertotalTypr,fire.getTotal_type_fn1()));
//        spinnergenerality.setSelection(getIndex(spinnergenerality,fire.getGenerality_fn1()));
//        dialogBuilder.setTitle(fire.getManufacturer_fn1());
//        manufacturer.setText(fire.getManufacturer_fn1());
//        dialogBuilder.setTitle(fire.getNonation_fn1());
//        note.setText(fire.getNonation_fn1());
//        dialogBuilder.setTitle(fire.getSignature_fn1());
//        sign.setText(fire.getSignature_fn1());
//        dialogBuilder.setTitle(fire.getPosition_signature_fn1());
//        postiSign.setText(fire.getPosition_signature_fn1());
//        dialogBuilder.setTitle(fire.getEd_signspector_fn1());
//        edSigninspector.setText(fire.getEd_signspector_fn1());
//        dialogBuilder.setTitle(fire.getPosition_ed_signspector_fn1());
//        positionedSignspector.setText(fire.getPosition_ed_signspector_fn1());
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//
//        ib_exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FnSafety_1.this, FnSafety_1.class);
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
//                String device = spinnerdevice.getSelectedItem().toString();
//                String total = spinnertotal.getSelectedItem().toString();
//                String totalType = spinner_fntotalType.getSelectedItem().toString();
//                String generality = spinnergenerality.getSelectedItem().toString();
//                String manufacturerfn = manufacturer.getText().toString();
//                String notation = note.getText().toString();
//                String signature = sign.getText().toString();
//                String positonsignature = postiSign.getText().toString();
//                String edSign = edSigninspector.getText().toString();
//                String positionedSign = positionedSignspector.getText().toString();
//                if (!TextUtils.isEmpty(locat)){
//                    updateData(fire.getId_fn1(), date,locat,device,total,totalType,generality,manufacturerfn,notation,signature,positonsignature,edSign,positionedSign);
//                    alertDialog.dismiss();
//                }
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData(fire.getId_fn1());
//                alertDialog.dismiss();
//            }
//        });
//
//    }
//
//}



//        final String[] totalFn1 = getResources().getStringArray(R.array.total);
//        ArrayAdapter<String> adapterTotal = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line,totalFn1);
//        spinner_fntotal.setAdapter(adapterTotal);
//
//        spinner_fntotal.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(FnSafety_1.this,
////                        "Select : " + totalFn1[position],
////                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        final String[] deviceTypeFn1 = getResources().getStringArray(R.array.device_Type);
//        ArrayAdapter<String> adapterDevice_Type = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line,deviceTypeFn1);
//        spinner_deviceType.setAdapter(adapterDevice_Type);
//        spinner_deviceType.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(FnSafety_1.this,
////                        "Select : " + deviceTypeFn1[position],
////                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        final String[] totalTypeFn1 = getResources().getStringArray(R.array.Total_Type);
//        ArrayAdapter<String> adapterTotal_Type = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line,totalTypeFn1);
//        spinner_fntotalType.setAdapter(adapterTotal_Type);
//        spinner_fntotalType.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(FnSafety_1.this,
////                        "Select : " + totalTypeFn1[position],
////                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        final String[] generalityFn1 = getResources().getStringArray(R.array.Generality);
//        ArrayAdapter<String> adapterGenerality = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, generalityFn1);
//        spinner_fngenerality.setAdapter(adapterGenerality);
//
//        spinner_fngenerality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(FnSafety_1.this,
////                        "Select : " + generalityFn1[position],
////                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//        });
//
//        final String[] locatFn1 = getResources().getStringArray(R.array.Location);
//        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, locatFn1);
//        spinner_fnlocation.setAdapter(adapterLocation);
//
//        spinner_fnlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(FnSafety_1.this,
////                        "Select : " + locatFn1[position],
////                        Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//

//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()){
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                TextView tdate = (TextView) findViewById(R.id.date);
//                                long date = System.currentTimeMillis();
//                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
//                                String dateString = sdf.format(date);
//                                tdate.setText(dateString);
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        t.start();
//    }
//
//}
