package com.cpm.gsk.hfd.promoter.womenfragment;

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


public class WomenSubHomeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ImageView feeling,occation_body,stress,high,high_low,expecting;
   FragmentManager fragmentManager;
    public WomenSubHomeFragment() {
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
        fragmentManager=getActivity().getSupportFragmentManager();
        View view=inflater.inflate(R.layout.fragment_women_sub_home, container, false);
        feeling = (ImageView) view.findViewById(R.id.img_feeling);
        occation_body = (ImageView) view.findViewById(R.id.img_occation);
        stress = (ImageView) view.findViewById(R.id.img_stress);
        high = (ImageView) view.findViewById(R.id.img_high);
        high_low = (ImageView) view.findViewById(R.id.img_highlow);
        expecting = (ImageView) view.findViewById(R.id.img_expecting);
        feeling.setOnClickListener(this);
        occation_body.setOnClickListener(this);
        stress.setOnClickListener(this);
        high.setOnClickListener(this);
        high_low.setOnClickListener(this);
        expecting.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.img_feeling:
                WHorlickPBrandFragment fragment=new WHorlickPBrandFragment();
                fragmentManager.beginTransaction().replace(R.id.container,fragment)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_occation:
                AfterAgeFragment fragment1=new AfterAgeFragment();
                fragmentManager.beginTransaction().replace(R.id.container,fragment1).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_stress:
                WomenHorContainFragment fragment2=new WomenHorContainFragment();
                fragmentManager.beginTransaction().replace(R.id.container,fragment2)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_high:
                WomenHorContainFragment fragment3=new WomenHorContainFragment();
                fragmentManager.beginTransaction().replace(R.id.container,fragment3).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_highlow:
                WomenHorContainFragment fragment4=new WomenHorContainFragment();
                fragmentManager.beginTransaction().replace(R.id.container,fragment4).addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_expecting:
                MotherHorlickFRagment fRagment7=new MotherHorlickFRagment();
                fragmentManager.beginTransaction().replace(R.id.container,fRagment7).addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
        }
    }
}
