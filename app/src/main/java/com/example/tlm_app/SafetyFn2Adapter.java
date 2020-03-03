package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class SafetyFn2Adapter extends BaseAdapter {

    List<EmergencyLight>emergencyLights;

    public SafetyFn2Adapter(List<EmergencyLight>emergencyLights){
        this.emergencyLights = emergencyLights;
    }
    @Override
    public int getCount() {
        if(emergencyLights == null)
        return 0;
        return emergencyLights.size();
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
        Safetyfn2ListItem item ;
        if (view!= null)
            item = (Safetyfn2ListItem) view;
        else
            item = new Safetyfn2ListItem(viewGroup.getContext());
        EmergencyLight firefn2 = emergencyLights.get(position);

        item.setTvdatefn2(firefn2.getDate_fn2());
        item.setTvLocationfn2(firefn2.getLocat_fn2());
        item.setTvTypeLightEmergencyfn2(firefn2.gettype_EmergencyLight_fn2());
        item.setTvGeneralityfn2(firefn2.getGenerality_fn2());
        item.setTvNotationfn2(firefn2.getNotation_fn2());
        item.setTvSignfn2(firefn2.getSignfn2());
        item.setTvEdSignfn2(firefn2.getEdSignfn2());

        return item;
    }
}
