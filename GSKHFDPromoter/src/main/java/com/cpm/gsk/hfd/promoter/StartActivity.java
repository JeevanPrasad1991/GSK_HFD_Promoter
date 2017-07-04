package com.cpm.gsk.hfd.promoter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cpm.gsk.hfd.promoter.fragment.HelpRightHealthDrinkFragment;
import com.cpm.gsk.hfd.promoter.fragment.MainFragment;

import java.util.List;

import static android.R.attr.type;

public class StartActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener,
        HelpRightHealthDrinkFragment.OnFragmentInteractionListener{
    FragmentManager fragmentManager;
    protected OnBackPressedListener onBackPressedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        fragmentManager = getSupportFragmentManager();
        MainFragment mainfrag = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.container, mainfrag).commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
    public interface OnBackPressedListener {
        public void doBack();
    }
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            startActivity(new Intent(StartActivity.this,DashBoardActivity.class));
           /* MainFragment fragment=new MainFragment();
            fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();*/
          //  super.onBackPressed();
        }
       // returnToPreviousFragment();
       /* if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();*/
      /*  int count = getSupportFragmentManager().getBackStackEntryCount();
        List<Fragment> frags = getSupportFragmentManager().getFragments();
        Fragment lastFrag = getLastNotNull(frags);
        //nothing else in back stack || nothing in back stack is instance of our interface
        if (count == 0 || !(lastFrag instanceof FragmentOnBackClickInterface)) {
fragmentManager.popBackStack();
        } else {
            ((FragmentOnBackClickInterface) lastFrag).onClick();
        }

    }
    private Fragment getLastNotNull(List<Fragment> list){
        Fragment frag = null;
        for (int i= list.size()-1;i>=0;i--){
             frag = list.get(i);
            if (frag != null){
                return frag;
            }
        }
        return frag ;
    }
    public interface FragmentOnBackClickInterface {
        void onClick();
    }*/
    }
   private Fragment returnToPreviousFragment() {

        FragmentManager fm = getSupportFragmentManager();

        Fragment topFrag = null;

        int idx = fm.getBackStackEntryCount();
        if (idx > 1) {
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(idx - 2);
            topFrag = fm.findFragmentByTag(entry.getName());
        }

        fm.popBackStack();

        return topFrag;
    }


}
