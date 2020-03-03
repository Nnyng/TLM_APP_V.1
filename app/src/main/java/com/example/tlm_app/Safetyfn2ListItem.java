package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Safetyfn2ListItem extends BaseCustomViewGroup {

    private TextView tvdatefn2;
    private TextView tvLocationfn2;
    private TextView tvTypeLightEmergencyfn2;
    private TextView tvGeneralityfn2;
    private TextView tvNotationfn2;
    private TextView tvSignfn2;
    private TextView tvEdSignfn2;

    public Safetyfn2ListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public Safetyfn2ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public Safetyfn2ListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }
    @TargetApi(21)
    public Safetyfn2ListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.safetyfn2_list_item, this);
    }

    private void initInstances(){
        tvdatefn2 = (TextView) findViewById(R.id.tvdatefn2);
        tvLocationfn2 = (TextView) findViewById(R.id.tvLocationfn2);
        tvTypeLightEmergencyfn2 = (TextView) findViewById(R.id.tvTypeLightEmergencyfn2);
        tvGeneralityfn2 = (TextView) findViewById(R.id.tvGeneralityfn2);
        tvNotationfn2 = (TextView) findViewById(R.id.tvNotationfn2);
        tvSignfn2 = (TextView)findViewById(R.id.tvSignfn2);
        tvEdSignfn2 = (TextView)findViewById(R.id.tvEdSignfn2);
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

    public void setTvdatefn2(String text){
        tvdatefn2.setText(text);
    }

    public void setTvLocationfn2(String text){
        tvLocationfn2.setText(text);
    }

    public  void  setTvTypeLightEmergencyfn2(String text){
        tvTypeLightEmergencyfn2.setText(text);
    }

    public  void setTvGeneralityfn2(String text){
        tvGeneralityfn2.setText(text);
    }

    public void setTvNotationfn2(String text){
        tvNotationfn2.setText(text);
    }

    public void setTvSignfn2(String text){
        tvSignfn2.setText(text);
    }

    public void setTvEdSignfn2(String text){
        tvEdSignfn2.setText(text);
    }
}

