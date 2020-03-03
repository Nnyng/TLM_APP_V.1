package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_11_5 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn11_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn11_5 = new ZXingScannerView(this);
        setContentView(scannerViewFn11_5);
    }

    @Override
    public void handleResult(Result result) {
        Safety_11_5.ReadResultFn11_5.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn11_5.setResultHandler(this);
        scannerViewFn11_5.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn11_5.stopCamera();

    }
}
