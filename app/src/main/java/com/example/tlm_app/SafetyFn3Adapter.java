package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class SafetyFn3Adapter extends BaseAdapter {

    List<FireExtinguisher>fireExtinguishers;

    public SafetyFn3Adapter(List<FireExtinguisher>fireExtinguishers){
        this.fireExtinguishers = fireExtinguishers;
    }
    @Override
    public int getCount(){
        if(fireExtinguishers == null)
            return 0;
        return fireExtinguishers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        SafetyFn3ListItem item;
        if (view!= null)
            item = (SafetyFn3ListItem) view;
        else
            item = new SafetyFn3ListItem(viewGroup.getContext());
        FireExtinguisher firefn3 = fireExtinguishers.get(position);

        item.setTvdatefn3(firefn3.getDate_fn3());
        item.setTvNumLocationfn3(firefn3.getNum_locatfn3());
        item.setTvlocationfn3(firefn3.getLocat_fn3());
        item.setTvTypeDvicefn3(firefn3.getType_devicefn3());
        item.setTvGeneralityfn3_1(firefn3.getGeneralityfn3_1());
        item.setTvGeneralityfn3_2(firefn3.getGeneralityfn3_2());
        item.setTvGeneralityfn3_3(firefn3.getGeneralityfn3_3());
        item.setTvGeneralityfn3_4(firefn3.getGeneralityfn3_4());
        item.setTvGeneralityfn3_5(firefn3.getGeneralityfn3_5());
        item.setTvGeneralityfn3_6(firefn3.getGeneralityfn3_6());
        item.setTvSignfn3(firefn3.getSignfn3());
        item.setTvEdSignfn3(firefn3.getEdSignfn3());
        return item;
    }
}
