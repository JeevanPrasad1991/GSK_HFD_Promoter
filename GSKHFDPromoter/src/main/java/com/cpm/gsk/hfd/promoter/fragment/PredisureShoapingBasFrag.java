package com.cpm.gsk.hfd.promoter.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.fivefrgment.FiveBagFragmrnt;
import com.cpm.gsk.hfd.promoter.skufragment.SkuFragment;

public class PredisureShoapingBasFrag extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentManager fragmentManager;
    String value;

    public PredisureShoapingBasFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle args = getArguments();
        if (args != null) {
            value = args.toString();
        } else {
            value = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_predisure_shoaping_bas, container, false);
        Button btn_start_call = (Button) view.findViewById(R.id.btn_yes);
        Button btn_practice_session = (Button) view.findViewById(R.id.btn_no);
        btn_start_call.setOnClickListener(this);
        btn_practice_session.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_yes:

               AdviseFragment fragment1=new AdviseFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment1
                ).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;
            case R.id.btn_no:
                if (!value.equalsIgnoreCase("")) {
                    FiveBagFragmrnt fragment = new FiveBagFragmrnt();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                } else {
                    PredisureMoreOptFragment fragment = new PredisureMoreOptFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                }
                break;
        }
    }
}
