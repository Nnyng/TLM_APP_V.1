package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class SafetyFn5Adapter extends BaseAdapter {

    List<FIRECABINETS>firecabinets;
    public SafetyFn5Adapter(List<FIRECABINETS>firecabinets){
        this.firecabinets = firecabinets;
    }

    @Override
    public int getCount() {
        if(firecabinets == null)
        return 0;
        return firecabinets.size();
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
        Safetyfn5ListItem item;
        if(view!= null)
            item = (Safetyfn5ListItem) view;
        else
            item = new Safetyfn5ListItem(viewGroup.getContext());
        FIRECABINETS firefn5 = firecabinets.get(position);

        item.setTvdatefn5(firefn5.getDate_fn5());
        item.setTvLocationfn5(firefn5.getLocat_fn5());
        item.setTvDevice_Typefn5(firefn5.getDevice_typefn5());
        item.setTvGeneralityfn5_1(firefn5.getGenerality_fn5_1());
        item.setTvGeneralityfn5_2(firefn5.getGenerality_fn5_2());
        item.setTvGeneralityfn5_3(firefn5.getGenerality_fn5_3());
        item.setTvGeneralityfn5_4(firefn5.getGenerality_fn5_4());
        item.setTvGeneralityfn5_5(firefn5.getGenerality_fn5_5());
        item.setTvGeneralityfn5_6(firefn5.getGenerality_fn5_6());
        item.setTvDateTestfn5(firefn5.getDateTest_fn5());
        item.setTvSignfn5(firefn5.getSignature_fn5());
        item.setTvEdSignfn5(firefn5.getEd_signspector_fn5());
        return item;
    }
}
