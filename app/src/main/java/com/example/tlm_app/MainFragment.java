package com.example.tlm_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.annotations.Nullable;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        return view;
    }   // onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Icon1 Controller
        icon1Controller();

        //Tutorial Controller
        tutorialController();

    }   // onActivityCreate

    private void tutorialController() {
        ImageView imageView = getView().findViewById(R.id.imvIcon2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }   // onClick
        });
    }

    private void icon1Controller() {
        ImageView imageView = getView().findViewById(R.id.imvIcon1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Show Dialog
                CharSequence[] charSequences = new CharSequence[]{"เพศชาย", "เพศหญิง"};
                final int[] intGender = {0};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setIcon(R.mipmap.ic_alert);
                builder.setTitle(R.string.choose_gender);
                builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intGender[0] = i;

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.myContent, ServiceFragment.serviceInstance(intGender[0]))
                                .addToBackStack(null)
                                .commit();

                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }   // onClick
        });
    }


}

