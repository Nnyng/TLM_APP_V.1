package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRFn_2 extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerViewFn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerViewFn2 = new ZXingScannerView(this);
        setContentView(scannerViewFn2);
    }

    @Override
    public void handleResult(Result result) {

        Fn_Safety_2.tv_ReadResultFn2.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerViewFn2.setResultHandler(this);
        scannerViewFn2.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerViewFn2.stopCamera();
    }
}