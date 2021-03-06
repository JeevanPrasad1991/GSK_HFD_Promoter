package com.cpm.gsk.hfd.promoter.fragment_6;

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

public class CutOfChilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fragmentManager;
    public CutOfChilFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_cut_of_chil, container, false);
        Button nxt = (Button) view.findViewById(R.id.nxt_btn);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NineHoSignGFragment adviseFragment = new NineHoSignGFragment();
                fragmentManager.beginTransaction().replace(R.id.container, adviseFragment).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
            }
        });
        return view;
    }

}
