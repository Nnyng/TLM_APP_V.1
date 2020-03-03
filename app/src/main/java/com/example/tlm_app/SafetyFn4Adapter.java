package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class SafetyFn4Adapter extends BaseAdapter {

     List<FireExitDoors> fireExitDoors;

     public SafetyFn4Adapter(List<FireExitDoors>fireExitDoors){
         this.fireExitDoors = fireExitDoors;
     }

     @Override
     public int getCount(){
      if(fireExitDoors == null)
          return 0;
      return fireExitDoors.size();
     }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
         Safetyfn4ListItem item;
         if (view!= null)
             item = (Safetyfn4ListItem) view;
         else
             item = new Safetyfn4ListItem(viewGroup.getContext());
         FireExitDoors firefn4 = fireExitDoors.get(position);

         item.setTvdatefn4(firefn4.getDete_fn4());
         item.setTvLocationfn4(firefn4.getLocat_fn4());
         item.setTvTypeLightEmergencyfn4(firefn4.getType_fn4());
         item.setTvGeneralityfn4(firefn4.getGenerality_fn4());
         item.setTvNotationfn4(firefn4.getNonation_fn4());
         item.setTvSignfn4(firefn4.getSignature_fn4());
         item.setTvEdSignfn4(firefn4.getEd_signspector_fn4());

        return item;
    }


}
