package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_9 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn9 = new ZXingScannerView(this);
        setContentView(scannerViewFn9);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_9.ReadResultFn9.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn9.setResultHandler(this);
        scannerViewFn9.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn9.stopCamera();
    }
}
