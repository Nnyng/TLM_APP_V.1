package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_11_1 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn11_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn11_1 = new ZXingScannerView(this);
        setContentView(scannerViewFn11_1);
    }

    @Override
    public void handleResult(Result result) {
        Safety_11_1.ReadResultFn11_1.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn11_1.setResultHandler(this);
        scannerViewFn11_1.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn11_1.stopCamera();
    }
}
