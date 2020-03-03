package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_10 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn10 = new ZXingScannerView(this);
        setContentView(scannerViewFn10);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_10.ReadResultFn10.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn10.setResultHandler(this);
        scannerViewFn10.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn10.stopCamera();
    }

}
