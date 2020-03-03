package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_11_2 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn11_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn11_2 = new ZXingScannerView(this);
        setContentView(scannerViewFn11_2);
    }

    @Override
    public void handleResult(Result result) {
        Safety_11_2.ReadResultFn11_2.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn11_2.setResultHandler(this);
        scannerViewFn11_2.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn11_2.stopCamera();

    }
}
