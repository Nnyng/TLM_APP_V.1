package com.example.tlm_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        zXingScannerView = new ZXingScannerView(getActivity());
        return zXingScannerView;
    }

    @Override
    public void handleResult(Result result) {

        String resultString = result.getText().toString().trim();
        Intent intent = new Intent(getActivity(), Main_QrCode.class);
        intent.putExtra("ResultQR",resultString);
        getActivity().setResult(100,intent);
        getActivity().finish();
    }
}
