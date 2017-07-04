package com.cpm.gsk.hfd.promoter.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cpm.gsk.hfd.promoter.DashBoardActivity;
import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.StartActivity;
import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.database.GSKDatabase;
import com.cpm.gsk.hfd.promoter.fragment_6.SubStartFragment;
import com.cpm.gsk.hfd.promoter.gettersetter.CoverageBean;
import com.cpm.gsk.hfd.promoter.skufragment.SkuFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class AdviseFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1, cycle_count = "0";
    private String mParam2;
    FragmentManager fragmentManager;
    String value = "", longt, lat, store_id, process_id, emp_id, pack_enable, user_id, visit_date, intime;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    GSKDatabase database;
    ArrayList<CoverageBean> coverage;

    public AdviseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
           /* Bundle bundle = this.getArguments();
            if (bundle!=null){
                value = bundle.getString("KEY");
            }else {
                value="";
            }*/

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Button btn_start_call = (Button) view.findViewById(R.id.btn_yes);
        Button btn_practice_session = (Button) view.findViewById(R.id.btn_no);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();
        intime = preferences.getString(CommonString.KEY_STORE_INTIME, null);
        cycle_count = preferences.getString(CommonString.KEY_CYCLE_COUNT, null);
        if (cycle_count==null){
            cycle_count="0";
        }
        longt = preferences.getString(CommonString.KEY_LONGITUDE, null);
        lat = preferences.getString(CommonString.KEY_LATITUDE, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
        emp_id = preferences.getString(CommonString.KEY_EMP_ID, null);
        pack_enable = preferences.getString(CommonString.KEY_PACK_ENABLE, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        user_id = preferences.getString(CommonString.KEY_USERNAME, "");
       /* user_id = preferences.getString(CommonString.KEY_USER_ID, null);
        visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, null);*/
        btn_start_call.setOnClickListener(this);
        btn_practice_session.setOnClickListener(this);
        database = new GSKDatabase(getActivity());
        database.open();
        coverage = database.getCoverageData(visit_date);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_yes:
                AllHealthdrinkFragment fragment = new AllHealthdrinkFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;
            case R.id.btn_no:
                int count = Integer.parseInt(cycle_count);
                if (count == 0 || count > 0) {
                    count++;
                    editor = preferences.edit();
                    editor.putString(CommonString.KEY_CYCLE_COUNT, String.valueOf(count));
                    editor.commit();
                }
               /* AlertDialog.Builder builder = new AlertDialog.Builder(
                        DashBoardActivity.this);
                builder.setMessage("Do you want to save the data ")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);*/


                if (coverage.size() == 0) {
                    CoverageBean cdata = new CoverageBean();
                    cdata.setStore_id(store_id);
                    cdata.setVisit_date(visit_date);
                    cdata.setUser_id(user_id);
                    cdata.setIntime(intime);
                    cdata.setProcess_id(process_id);
                    cdata.setEmp_id(emp_id);
                    cdata.setPakd_enable(pack_enable);
                    cdata.setOut_time(getCurrentTime());
                    cdata.setLatitute(lat);
                    cdata.setLongitute(longt);
                    cdata.setStatus(CommonString.KEY_CHECK_IN);
                    database.InsertCoverageData(cdata);
                }
                if (coverage.size() > 0) {
                    coverage = database.getCoverageData(visit_date);
                    if (visit_date.equalsIgnoreCase(coverage.get(0).getVisit_date())) {
                        database.updateCoverageOutTime(store_id, getCurrentTime());
                    }
                    /*if (!visit_date.equalsIgnoreCase(coverage.get(0).getVisit_date())) {
                        database.deleteCoverageData();
                    }*/


                }
                SkuFragment fragment1=new SkuFragment();
               // SkuFragment fragment1 = new SkuFragment();
                fragmentManager.beginTransaction().replace(R.id.container, fragment1).
                        addToBackStack(String.valueOf(fragmentManager.getFragments())).commit();
                break;

                             /*       }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int id) {
                                        dialog.cancel();
                                    }
                                });

                alert = builder.create();
                alert.show();*/

        }
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":" + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);
        return intime;

    }
}
