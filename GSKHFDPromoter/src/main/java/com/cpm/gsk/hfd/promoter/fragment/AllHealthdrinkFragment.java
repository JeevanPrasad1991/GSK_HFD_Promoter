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
import android.widget.ImageView;

import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.tenplusfragment.TenPlusHomeFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllHealthdrinkFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainFragment.OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    ImageView two_year, three_year, five_year, six_year, ten_year, men, women, family,left_btn;

    public AllHealthdrinkFragment() {
        // Required empty public constructor
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragment.OnFragmentInteractionListener) {
            mListener = (MainFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_all_healthdrink, container, false);
        two_year = (ImageView) view.findViewById(R.id.img_2yrs);
        three_year = (ImageView) view.findViewById(R.id.img_3yrs);
        five_year = (ImageView) view.findViewById(R.id.img_5yrs);
        six_year = (ImageView) view.findViewById(R.id.img_6yrs);
        ten_year = (ImageView) view.findViewById(R.id.img_10yrs);
        men = (ImageView) view.findViewById(R.id.img_men);
        women = (ImageView) view.findViewById(R.id.img_women);
        family = (ImageView) view.findViewById(R.id.img_family);
       /* left_btn= (ImageView) view.findViewById(R.id.left_btn);
        left_btn.setOnClickListener(this);*/
        two_year.setOnClickListener(this);
        three_year.setOnClickListener(this);
        five_year.setOnClickListener(this);
        six_year.setOnClickListener(this);
        ten_year.setOnClickListener(this);
        men.setOnClickListener(this);
        women.setOnClickListener(this);
        family.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_2yrs:
                TwoYearFragment fragment = new TwoYearFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_3yrs:
                ThreePlusYearFragment fragment1 = new ThreePlusYearFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment1)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_5yrs:
                FivePlusFragment fragment2 = new FivePlusFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment2)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;
            case R.id.img_6yrs:
                SixPlusFragment fragment3 = new SixPlusFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment3)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;


            case R.id.img_10yrs:
                TenPlusHomeFragment fragment4 = new TenPlusHomeFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment4)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;

            case R.id.img_men:
                MenMenuFragment fragment5 = new MenMenuFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment5)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_women:
                WomenMenuFragment fragment6 = new WomenMenuFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment6).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_family:
                FamilyMenuFragment familyMenuFragment = new FamilyMenuFragment();
                fragmentManager.beginTransaction().replace(R.id.container, familyMenuFragment)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
        }

    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
