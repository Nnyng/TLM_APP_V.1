package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_4 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn4 = new ZXingScannerView(this);
        setContentView(scannerViewFn4);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_4.ReadResultFn4.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn4.setResultHandler(this);
        scannerViewFn4.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn4.stopCamera();
    }

}