package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_6 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn6 = new ZXingScannerView(this);
        setContentView(scannerViewFn6);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_6.ReadResultFn6.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn6.setResultHandler(this);
        scannerViewFn6.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn6.stopCamera();

    }
}
