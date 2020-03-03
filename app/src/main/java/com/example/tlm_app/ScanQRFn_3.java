package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_3 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn3 = new ZXingScannerView(this);
        setContentView(scannerViewFn3);
    }

    @Override
    public void handleResult(Result result) {
        FnSafety_3.tv_ReadResultFn3.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn3.setResultHandler(this);
        scannerViewFn3.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn3.stopCamera();
    }

}
