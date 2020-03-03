package com.example.tlm_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.ArrayList;
import java.util.List;

public class ScanQRMLKit extends AppCompatActivity {
    public static TextView tvDialog,tv_ReadResult;
    private Button btn_again;
    private DatabaseReference firebaseReference;
    CameraView camera_view;
    boolean isDetected = false;
    Button btn_start_again;
    private List<GetDataDialog>dataDialogs;
    private String result;


    FirebaseVisionBarcodeDetectorOptions options;
    FirebaseVisionBarcodeDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qrmlkit);

//        initInstances();
//        initFirebase();
//        showData();

        Dexter.withActivity(this)
                .withPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO})
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        setupCamera();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }


    private void setupCamera() {

        btn_start_again = (Button) findViewById(R.id.btn_again);
        btn_start_again.setEnabled(isDetected);
        btn_start_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDetected = !isDetected;
            }
        });


        camera_view = (CameraView)findViewById(R.id.cameraView);
        camera_view.setLifecycleOwner(this);
        camera_view.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {
                processImage(getVisionImageFromFrame(frame));
            }
        });

        options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
                .build();
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
    }

    private void processImage(FirebaseVisionImage image) {

        if(!isDetected)
        {
            detector.detectInImage(image)
                    .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                        @Override
                        public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                            processResult(firebaseVisionBarcodes);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ScanQRMLKit.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        if (firebaseVisionBarcodes.size() > 0) {
            isDetected = true;
            btn_start_again.setEnabled(isDetected);
            for (FirebaseVisionBarcode item : firebaseVisionBarcodes) {
                int value_type = item.getValueType();
                switch (value_type) {
                    case FirebaseVisionBarcode.TYPE_TEXT: {
                       // tv_ReadResult.setText(item.getRawValue().toString());
                        createDialog(item.getRawValue());
                    }
                    break;
                    case FirebaseVisionBarcode.TYPE_URL: {
                        //Start Browser intent
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getRawValue()));
            startActivity(intent);
        }
        break;
        case FirebaseVisionBarcode.TYPE_CONTACT_INFO: {
            String info = new StringBuilder("Location: ")
                    .append(item.getContactInfo().getName().getFormattedName())
                    .append("\n")
                    .append("Detail1: ")
                    .append(item.getContactInfo().getAddresses().get(0).getAddressLines()[0])
                    .append("Detail2: ")
                    .append(item.getContactInfo().getEmails().get(0).getAddress())
                    .toString();
           // tv_ReadResult.setText(info);
            createDialog(info);
        }
        break;
        default:
        break;
    }
}
        }
    }


//    private void initFirebase() {
//        firebaseReference = FirebaseDatabase.getInstance().getReference();
//    }
//
//    private void  initInstances(){
//        tvDialog = (TextView) findViewById(R.id.tvDialog);
//        btn_again = (Button) findViewById(R.id.btn_again);
//
//
//        btn_again = (Button) findViewById(R.id.btn_again);
//        btn_again.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if  (v == btn_again) {
//                    addData();
//                }
//            }
//        });
//
//
//        dataDialogs = new ArrayList<>();
//
//    }
//
//    private  void addData(){
//
//        String detail = tvDialog.getText().toString();
//        String btn = btn_again.getText().toString();
//
//        if (!TextUtils.isEmpty(detail)){
//            String id = firebaseReference.child("GetDataDialog").push().getKey();
//
//            GetDataDialog data = new GetDataDialog();
//
//            data.setId(id);
//            data.setDevice_type(detail);
//            data.setLocation(detail);
//
//            firebaseReference.child("GetDataDialog").child(id).setValue(data);
//
//            Toast.makeText(this, "Checking Successful", Toast.LENGTH_LONG).show();
//
//        }else {
//
//            //if the value is not given displaying a toast
//            Toast.makeText(this, "Please fill your information completely", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private  void showData(){
//        Query query = firebaseReference.child("GetDataDialog");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                dataDialogs.clear();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                    GetDataDialog data = postSnapshot.getValue(GetDataDialog.class);
//                    dataDialogs.add(data);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }



    private void createDialog(final String text) {
       // final TextView input = new TextView(ScanQRMLKit.this);
       // input.setSingleLine(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
              // .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // String f = text.getText().toString();
                       // tv_ReadResult.getText().toString();
                        dialogInterface.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();

        dialog.show();


    }



    private FirebaseVisionImage getVisionImageFromFrame(Frame frame) {

        byte[] data = frame.getData();
        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setHeight(frame.getSize().getHeight())
                .setWidth(frame.getSize().getWidth())
               // .setRotation(frame.getRotation()) // Only use it if you work on Land scape mode - for portrail , don't use it
                .build();
        return FirebaseVisionImage.fromByteArray(data,metadata);
    }
}
