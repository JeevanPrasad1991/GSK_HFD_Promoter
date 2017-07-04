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
import com.cpm.gsk.hfd.promoter.fragment_6.NineHomeFragment;
import com.cpm.gsk.hfd.promoter.menfragment.MenWorryFragment;

public class MenMenuFragment extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fragmentManager;
    ImageView img_pedisure, img_champ, img_complain, img_horlicks, img_other, img_firsttime;
    public MenMenuFragment() {
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
        View view= inflater.inflate(R.layout.fragment_men_menu, container, false);
        img_pedisure = (ImageView) view.findViewById(R.id.img_pedisure4);
        img_champ = (ImageView) view.findViewById(R.id.img_champ4);
        img_complain = (ImageView) view.findViewById(R.id.img_complain4);
        img_horlicks = (ImageView) view.findViewById(R.id.img_horlicks4);
        img_other = (ImageView) view.findViewById(R.id.img_other4);
        img_firsttime = (ImageView) view.findViewById(R.id.img_firsttime4);
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
            case R.id.img_pedisure4:
                MenWorryFragment fragment1=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment1).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_champ4:
                MenWorryFragment fragment2=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment2)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_complain4:
                MenWorryFragment fragment3=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment3).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_other4:
                MenWorryFragment fragment4=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment4).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_firsttime4:
                MenWorryFragment fragment6=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment6).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.img_horlicks4:
                MenWorryFragment fragment5=new MenWorryFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment5).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;

        }
    }
}
