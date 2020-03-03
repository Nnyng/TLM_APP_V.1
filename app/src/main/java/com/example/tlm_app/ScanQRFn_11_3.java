package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_11_3 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn11_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn11_3 = new ZXingScannerView(this);
        setContentView(scannerViewFn11_3);
    }

    @Override
    public void handleResult(Result result) {
        Safety_11_3.ReadResultFn11_3.setText(result.getText());
        onBackPressed();

    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn11_3.setResultHandler(this);
        scannerViewFn11_3.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn11_3.stopCamera();

    }
}
