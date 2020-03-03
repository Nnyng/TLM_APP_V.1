package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

public class SafetyFn3ListItem extends BaseCustomViewGroup {

    private TextView tvdatefn3;
    private TextView tvNumLocationfn3;
    private TextView tvlocationfn3;
    private TextView tvTypeDvicefn3;
    private TextView tvGeneralityfn3_1;
    private TextView tvGeneralityfn3_2;
    private TextView tvGeneralityfn3_3;
    private TextView tvGeneralityfn3_4;
    private TextView tvGeneralityfn3_5;
    private TextView tvGeneralityfn3_6;
    private TextView tvSignfn3;
    private TextView tvEdSignfn3;

    public SafetyFn3ListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public SafetyFn3ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public SafetyFn3ListItem(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public  SafetyFn3ListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.safetyfn3_list_item, this);
    }

    private void initInstances(){
        tvdatefn3 = (TextView) findViewById(R.id.tvdatefn3);
        tvNumLocationfn3 = (TextView) findViewById(R.id.tvNumLocationfn3);
        tvlocationfn3 = (TextView) findViewById(R.id.tvlocationfn3);
        tvTypeDvicefn3 = (TextView)findViewById(R.id.tvTypeDvicefn3);
        tvGeneralityfn3_1 = (TextView) findViewById(R.id.tvGeneralityfn3_1);
        tvGeneralityfn3_2 = (TextView) findViewById(R.id.tvGeneralityfn3_2);
        tvGeneralityfn3_3 = (TextView) findViewById(R.id.tvGeneralityfn3_3);
        tvGeneralityfn3_4 = (TextView) findViewById(R.id.tvGeneralityfn3_4);
        tvGeneralityfn3_5 = (TextView) findViewById(R.id.tvGeneralityfn3_5);
        tvGeneralityfn3_6 = (TextView) findViewById(R.id.tvGeneralityfn3_6);
        tvSignfn3 = (TextView) findViewById(R.id.tvSignfn3);
        tvEdSignfn3 = (TextView) findViewById(R.id.tvEdSignfn3);
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

    public void setTvdatefn3(String text){
        tvdatefn3.setText(text);
    }

    public void setTvNumLocationfn3(String text){
        tvNumLocationfn3.setText(text);
    }

    public void  setTvlocationfn3(String text){
        tvlocationfn3.setText(text);
    }

    public void setTvTypeDvicefn3(String text){
        tvTypeDvicefn3.setText(text);
    }

    public void setTvGeneralityfn3_1(String text){
        tvGeneralityfn3_1.setText(text);
    }

    public void setTvGeneralityfn3_2(String text){
        tvGeneralityfn3_2.setText(text);
    }

    public void setTvGeneralityfn3_3(String text){
        tvGeneralityfn3_3.setText(text);
    }

    public void setTvGeneralityfn3_4(String text){
        tvGeneralityfn3_4.setText(text);
    }

    public void setTvGeneralityfn3_5(String text){
        tvGeneralityfn3_5.setText(text);
    }

    public void setTvGeneralityfn3_6(String text){
        tvGeneralityfn3_6.setText(text);
    }

    public void setTvSignfn3(String text){
        tvSignfn3.setText(text);
    }

    public void setTvEdSignfn3(String text){
        tvEdSignfn3.setText(text);
    }
}



