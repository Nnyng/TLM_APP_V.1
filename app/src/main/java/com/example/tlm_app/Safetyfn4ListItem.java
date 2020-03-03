package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Safetyfn4ListItem extends BaseCustomViewGroup {

    private TextView tvdatefn4;
    private TextView tvLocationfn4;
    private TextView tvTypeLightEmergencyfn4;
    private TextView tvGeneralityfn4;
    private TextView tvNotationfn4;
    private TextView tvSignfn4;
    private TextView tvEdSignfn4;


    public  Safetyfn4ListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public Safetyfn4ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs,0,0);
    }

    public Safetyfn4ListItem(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs,defStyleAttr,0);
    }

    @TargetApi(21)
    public Safetyfn4ListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(),R.layout.safetyfn4_list_item, this);

    }

    private void  initInstances(){
        tvdatefn4 = (TextView) findViewById(R.id.tvdatefn4);
        tvLocationfn4 = (TextView) findViewById(R.id.tvLocationfn4);
        tvTypeLightEmergencyfn4 = (TextView) findViewById(R.id.tvTypeLightEmergencyfn4);
        tvGeneralityfn4 = (TextView) findViewById(R.id.tvGeneralityfn4);
        tvNotationfn4 = (TextView) findViewById(R.id.tvNotationfn4);
        tvSignfn4 = (TextView) findViewById(R.id.tvSignfn4);
        tvEdSignfn4 = (TextView) findViewById(R.id.tvEdSignfn4);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);
        try {
        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setTvdatefn4(String text) {
        tvdatefn4.setText(text);
    }

    public void setTvLocationfn4(String text) {
        tvLocationfn4.setText(text);
    }

    public  void  setTvTypeLightEmergencyfn4(String text) {
        tvTypeLightEmergencyfn4.setText(text);
    }

    public void setTvGeneralityfn4(String text) {
        tvGeneralityfn4.setText(text);
    }

    public void setTvNotationfn4(String text) {
        tvNotationfn4.setText(text);
    }

    public void setTvSignfn4(String text) {
        tvSignfn4.setText(text);
    }

    public  void setTvEdSignfn4 (String text) {
        tvEdSignfn4.setText(text);
    }
}
