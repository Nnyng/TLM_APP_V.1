package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_11_4 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn_11_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn_11_4 = new ZXingScannerView(this);
        setContentView(scannerViewFn_11_4);
    }

    @Override
    public void handleResult(Result result) {
        Safety_11_4.ReadResultFn11_4.setText(result.getText());
        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn_11_4.setResultHandler(this);
        scannerViewFn_11_4.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn_11_4.stopCamera();

    }
}
