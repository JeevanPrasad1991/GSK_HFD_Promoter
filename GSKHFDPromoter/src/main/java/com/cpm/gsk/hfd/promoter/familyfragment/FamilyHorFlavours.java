package com.cpm.gsk.hfd.promoter.familyfragment;

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
import com.cpm.gsk.hfd.promoter.fragment.AdviseFragment;

public class FamilyHorFlavours extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   FragmentManager fragmentManager;

    public FamilyHorFlavours() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager=getActivity().getSupportFragmentManager();
        View view=inflater.inflate(R.layout.fragment_family_hor_flavours, container, false);
        Button nxt_btn = (Button) view.findViewById(R.id.nxt_btn);
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                int id = view1.getId();
                switch (id) {
                    case R.id.nxt_btn:
                        AdviseFragment fragment=new AdviseFragment();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                                .commit();
                        break;
                }

            }
        });
        return view;

    }


}
