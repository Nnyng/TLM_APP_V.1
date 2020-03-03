package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_5 extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    ZXingScannerView scannerViewFn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn5 = new ZXingScannerView(this);
        setContentView(scannerViewFn5);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_5.ReadResultFn5.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn5.setResultHandler(this);
        scannerViewFn5.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn5.stopCamera();
    }
}
