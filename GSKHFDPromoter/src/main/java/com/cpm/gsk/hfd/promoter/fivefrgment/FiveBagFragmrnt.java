package com.cpm.gsk.hfd.promoter.fivefrgment;

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
import com.cpm.gsk.hfd.promoter.fragment.JHorlicksStage2Fragment;
import com.cpm.gsk.hfd.promoter.fragment.JuniorHorlSpiFragment;


public class FiveBagFragmrnt extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

FragmentManager fragmentManager;

    public FiveBagFragmrnt() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_five_bag_fragmrnt, container, false);
        Button nxt_btn1 = (Button) view.findViewById(R.id.nxt_btn1);
        nxt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.nxt_btn1:
                        JHorlicksStage2Fragment fragment=new JHorlicksStage2Fragment();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                        break;
                }
            }
        });
        return view;
    }


}
