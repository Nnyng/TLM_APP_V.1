package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class  SafetyFn1Adapter extends BaseAdapter {

    List<FireFightingEquipment> fireEquipment;

    public SafetyFn1Adapter(List<FireFightingEquipment>fireEquipment){
        this.fireEquipment = fireEquipment;
    }

    @Override
    public int getCount() {
      if(fireEquipment == null)
        return 0;
      return fireEquipment.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view , ViewGroup viewGroup)  {
        Safetyfn1ListItem item ;
        if (view!= null)
            item = (Safetyfn1ListItem) view;
        else
            item = new Safetyfn1ListItem(viewGroup.getContext());
        FireFightingEquipment firefn1 = fireEquipment.get(position);

        item.setTvdatefn1(firefn1.getDate_fn1());
        item.setTvLocationfn1(firefn1.getLocat_fn1());
        item.setTvDeviceTypefn1(firefn1.getDevice_fn1());
        item.setTvTotalfn1(firefn1.getTotal_fn1());
        item.setTvTotalTypefn1(firefn1.getTotal_type_fn1());
        item.setTvGeneralityfn1(firefn1.getGenerality_fn1());
        item.setTvManufacturerfn1(firefn1.getNameDevice_fn1());
        item.setTvNotationfn1(firefn1.getNonation_fn1());
        item.setTvSignfn1(firefn1.getSignature_fn1());
        item.setTvPosition_Signfn1(firefn1.getPosition_signature_fn1());
        item.setTvEdSignfn1(firefn1.getEd_signspector_fn1());
        item.setTvPosition_Ed_Signfn1(firefn1.getPosition_ed_signspector_fn1());

        return item;
    }
}
