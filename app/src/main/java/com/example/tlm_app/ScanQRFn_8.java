package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_8 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn8 = new ZXingScannerView(this);
        setContentView(scannerViewFn8);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_8.ReadResultFn8.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn8.setResultHandler(this);
        scannerViewFn8.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn8.stopCamera();
    }
}
