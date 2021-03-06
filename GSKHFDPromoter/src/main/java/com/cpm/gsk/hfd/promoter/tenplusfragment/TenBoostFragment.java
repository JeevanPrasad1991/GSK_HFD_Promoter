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
import com.cpm.gsk.hfd.promoter.fragment.AdviseFragment;

public class TenBoostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fragmentManager;
    String value="";

    public TenBoostFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                value = bundle.getString("KEY");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_ten_boost, container, false);
        Button button = (Button) view.findViewById(R.id.nxt_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScientificallyPBoostFragent fragent=new ScientificallyPBoostFragent();
                fragmentManager.beginTransaction().replace(R.id.container, fragent).addToBackStack(String.valueOf(fragmentManager.getFragments()))
                        .commit();

                /*if (!value.equalsIgnoreCase("")) {
                    AdviseFragment fragment1 = new AdviseFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment1).commit();
                } else {
                    BagFragment fragment = new BagFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                }*/
            }
        });
        return view;
    }

}
