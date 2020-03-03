package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_7 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn7 = new ZXingScannerView(this);
        setContentView(scannerViewFn7);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_7.ReadResultFn7.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn7.setResultHandler(this);
        scannerViewFn7.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn7.stopCamera();

    }
}
