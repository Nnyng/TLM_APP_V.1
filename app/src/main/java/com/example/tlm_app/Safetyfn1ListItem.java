package com.example.tlm_app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Safetyfn1ListItem extends BaseCustomViewGroup {

    private TextView tvdatefn1;
    private TextView tvLocationfn1;
    private TextView tvDeviceTypefn1;
    private TextView tvTotalfn1;
    private TextView tvTotalTypefn1;
    private TextView tvGeneralityfn1;
    private TextView tvManufacturerfn1;
    private TextView tvNotationfn1;
    private TextView tvSignfn1;
    private TextView tvPosition_Signfn1;
    private TextView tvEdSignfn1;
    private TextView tvPosition_Ed_Signfn1;


    public Safetyfn1ListItem(Context context) {
        super(context);
        initInflate();
        initInstances();

    }

    public Safetyfn1ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public Safetyfn1ListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public Safetyfn1ListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.safetyfn1_list_item, this);
    }

    private void initInstances() {
        tvdatefn1 = (TextView) findViewById(R.id.tvdatefn1);
        tvLocationfn1 = (TextView) findViewById(R.id.tvLocationfn1);
        tvDeviceTypefn1 = (TextView) findViewById(R.id.tvDeviceTypefn1);
        tvTotalfn1 = (TextView) findViewById(R.id.tvTotalfn1);
        tvTotalTypefn1 = (TextView) findViewById(R.id.tvTotalTypefn1);
        tvGeneralityfn1 = (TextView) findViewById(R.id.tvGeneralityfn1);
        tvManufacturerfn1 = (TextView)findViewById(R.id.tvManufacturerfn1);
        tvNotationfn1 = (TextView) findViewById(R.id.tvNotationfn1);
        tvSignfn1 = (TextView) findViewById(R.id.tvSignfn1);
        tvPosition_Signfn1 = (TextView) findViewById(R.id.tvPosition_Signfn1);
        tvEdSignfn1 = (TextView) findViewById(R.id.tvEdSignfn1);
        tvPosition_Ed_Signfn1 = (TextView) findViewById(R.id.tvPosition_Ed_Signfn1);
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

    public void setTvdatefn1(String text) {
        tvdatefn1.setText(text);
    }

    public void setTvLocationfn1(String text) {

        tvLocationfn1.setText(text);
    }
    public void setTvDeviceTypefn1(String text) {

        tvDeviceTypefn1.setText(text);
    }
    public void setTvTotalfn1 (String text){
        tvTotalfn1.setText(text);
    }
    public void setTvTotalTypefn1 (String text) {
        tvTotalTypefn1.setText(text);
    }
    public void setTvGeneralityfn1 (String text) {
        tvGeneralityfn1.setText(text);
    }
    public void setTvManufacturerfn1 (String text)
    { tvManufacturerfn1.setText(text);}
    public void setTvNotationfn1 (String text) {
        tvNotationfn1.setText(text);
    }
    public void setTvSignfn1 (String text) {
        tvSignfn1.setText(text);
    }
    public void setTvPosition_Signfn1 (String text) {
        tvPosition_Signfn1.setText(text);
    }
    public  void setTvEdSignfn1 (String text) {
        tvEdSignfn1.setText(text);
    }
    public void setTvPosition_Ed_Signfn1 (String text) {
        tvPosition_Ed_Signfn1.setText(text);
    }

}
