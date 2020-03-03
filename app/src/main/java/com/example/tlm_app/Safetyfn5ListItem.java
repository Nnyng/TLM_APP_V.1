package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Safetyfn5ListItem extends BaseCustomViewGroup {

    private TextView tvdatefn5;
    private TextView tvLocationfn5;
    private TextView tvDevice_Typefn5;
    private TextView tvGeneralityfn5_1;
    private TextView tvGeneralityfn5_2;
    private TextView tvGeneralityfn5_3;
    private TextView tvGeneralityfn5_4;
    private TextView tvGeneralityfn5_5;
    private TextView tvGeneralityfn5_6;
    private TextView tvDateTestfn5;
    private TextView tvSignfn5;
    private TextView tvEdSignfn5;


    public Safetyfn5ListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public Safetyfn5ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs,0,0);
    }

    public Safetyfn5ListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs,defStyleAttr,0);
    }

    @TargetApi(21)
    public Safetyfn5ListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate(){
        inflate(getContext(),R.layout.safetyfn5_list_item,this);
    }

    private void initInstances(){
        tvdatefn5 = (TextView) findViewById(R.id.tvdatefn5);
        tvLocationfn5 = (TextView) findViewById(R.id.tvLocationfn5);
        tvDevice_Typefn5 = (TextView) findViewById(R.id.tvDevice_Typefn5);
        tvGeneralityfn5_1 = (TextView) findViewById(R.id.tvGeneralityfn5_1);
        tvGeneralityfn5_2 = (TextView) findViewById(R.id.tvGeneralityfn5_2);
        tvGeneralityfn5_3 = (TextView) findViewById(R.id.tvGeneralityfn5_3);
        tvGeneralityfn5_4 = (TextView) findViewById(R.id.tvGeneralityfn5_4);
        tvGeneralityfn5_5 = (TextView) findViewById(R.id.tvGeneralityfn5_5);
        tvGeneralityfn5_6 = (TextView) findViewById(R.id.tvGeneralityfn5_6);
        tvDateTestfn5 = (TextView) findViewById(R.id.tvDateTestfn5);
        tvSignfn5 = (TextView) findViewById(R.id.tvSignfn5);
        tvEdSignfn5 = (TextView) findViewById(R.id.tvEdSignfn5);

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

    public void setTvdatefn5(String text){
        tvdatefn5.setText(text);
    }

    public void setTvLocationfn5 (String text){
        tvLocationfn5.setText(text);
    }

    public void setTvDevice_Typefn5 (String text){
        tvDevice_Typefn5.setText(text);
    }

    public void setTvGeneralityfn5_1(String text){
        tvGeneralityfn5_1.setText(text);
    }

    public void setTvGeneralityfn5_2(String text){
        tvGeneralityfn5_2.setText(text);
    }

    public void setTvGeneralityfn5_3(String text){
        tvGeneralityfn5_3.setText(text);
    }

    public void setTvGeneralityfn5_4(String text){
        tvGeneralityfn5_4.setText(text);
    }

    public void setTvGeneralityfn5_5(String text){
        tvGeneralityfn5_5.setText(text);
    }

    public void  setTvGeneralityfn5_6(String text){
        tvGeneralityfn5_6.setText(text);
    }

    public void  setTvDateTestfn5(String text){
        tvDateTestfn5.setText(text);
    }

    public void  setTvSignfn5(String text){
        tvSignfn5.setText(text);
    }

    public void setTvEdSignfn5(String text){
        tvEdSignfn5.setText(text);
    }
}
