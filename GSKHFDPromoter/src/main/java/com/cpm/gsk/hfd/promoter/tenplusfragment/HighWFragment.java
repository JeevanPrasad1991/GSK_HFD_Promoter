package com.cpm.gsk.hfd.promoter.tenplusfragment;

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


public class HighWFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentManager fragmentManager;
    Button low, mediam, high;

    public HighWFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_high_w, container, false);
       /* Button button = (Button) view.findViewById(R.id.nxt_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LowMediamFragment fragment = new LowMediamFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
        return view;
    }
*/

        low = (Button) view.findViewById(R.id.low_btn);
        mediam = (Button) view.findViewById(R.id.mediam_btn);
        high = (Button) view.findViewById(R.id.high_btn);
        low.setOnClickListener(this);
        mediam.setOnClickListener(this);
        high.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.low_btn:
                StaminaFragment fragment1 = new StaminaFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment1).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;
            case R.id.mediam_btn:
                StaminaFragment fragment2 = new StaminaFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment2).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;

            case R.id.high_btn:
                HorlicksImproovFragment fragment = new HorlicksImproovFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();
                break;
        }
    }
}
