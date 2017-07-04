package com.cpm.gsk.hfd.promoter.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cpm.gsk.hfd.promoter.R;

public class ThreePlusYearFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fm;
    ImageView img_juniH, img_pedisure, img_champ, img_complain, img_horlicks, img_other, img_firsttime;

    public ThreePlusYearFragment() {
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
        // Inflate the layout for this fragment
        fm = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_three_plus_year, container, false);
        img_juniH = (ImageView) view.findViewById(R.id.img_juniH);
        img_pedisure = (ImageView) view.findViewById(R.id.img_pedisure);
        img_champ = (ImageView) view.findViewById(R.id.img_champ);
        img_complain = (ImageView) view.findViewById(R.id.img_complain);
        img_horlicks = (ImageView) view.findViewById(R.id.img_horlicks);
        img_other = (ImageView) view.findViewById(R.id.img_other);
        img_firsttime = (ImageView) view.findViewById(R.id.img_firsttime);

        img_juniH.setOnClickListener(this);
        img_pedisure.setOnClickListener(this);
        img_champ.setOnClickListener(this);
        img_complain.setOnClickListener(this);
        img_horlicks.setOnClickListener(this);
        img_other.setOnClickListener(this);
        img_firsttime.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_juniH:
                JuniorHorlicksFragment fragment = new JuniorHorlicksFragment();
                fm.beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_pedisure:
                PredicureMainFragment fragment1 = new PredicureMainFragment();
                fm.beginTransaction().replace(R.id.container, fragment1).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_champ:
                PredicureMainFragment1 fragment2 = new PredicureMainFragment1();
                fm.beginTransaction().replace(R.id.container, fragment2).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_complain:
                PredicureMainFragment1 fragment3 = new PredicureMainFragment1();
                fm.beginTransaction().replace(R.id.container, fragment3).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_other:
                PredicureMainFragment1 fragment4 = new PredicureMainFragment1();
                fm.beginTransaction().replace(R.id.container, fragment4).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_firsttime:
                JuniorHorlSpiFragment fragment6=new JuniorHorlSpiFragment();
                fm.beginTransaction().replace(R.id.container, fragment6).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;
            case R.id.img_horlicks:

                JuniorHorlSpiFragment fragment5=new JuniorHorlSpiFragment();
                fm.beginTransaction().replace(R.id.container, fragment5).addToBackStack(String.valueOf(fm.getFragments()))
                        .commit();
                break;

        }

    }
}
