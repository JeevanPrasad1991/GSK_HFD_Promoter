package com.cpm.gsk.hfd.promoter.fragment_6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cpm.gsk.hfd.promoter.DashBoardActivity;
import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.StartActivity;
import com.cpm.gsk.hfd.promoter.fragment.AllHealthdrinkFragment;
import com.cpm.gsk.hfd.promoter.fragment.MainFragment;

public class SubStartFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentManager fragmentManager;


    public SubStartFragment() {
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
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_sub_start, container, false);
        Button btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_next:
                startActivity(new Intent(getActivity(), DashBoardActivity.class));
               /* MainFragment mainfrag = new MainFragment();
                fragmentManager.beginTransaction().replace(R.id.container, mainfrag)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();*/
                break;

        }
    }
}


