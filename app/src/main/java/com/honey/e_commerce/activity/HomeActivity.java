package com.honey.e_commerce.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.honey.e_commerce.R;
import com.honey.e_commerce.fragment.FragmentTwo;
import com.honey.e_commerce.fragment.FragmentCar;
import com.honey.e_commerce.fragment.FragmentHome;
import com.honey.e_commerce.fragment.FragmentUser;

public class HomeActivity extends FragmentActivity implements View.OnClickListener{

    private FragmentManager supportFragmentManager;
    private RadioGroup group;
    private RadioButton radioButtonhome;
    private RadioButton radioButtoncar;
    private RadioButton radioButtonclass;
    private RadioButton radioButtonuser;
    public static Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //获取资源id
        initData();
        supportFragmentManager = getSupportFragmentManager();
        addFragment("fragmentHome",new FragmentHome());
    }
    private void initData() {
        group = (RadioGroup) findViewById(R.id.group);
        radioButtonhome = (RadioButton) findViewById(R.id.homeRadioButton);
        radioButtoncar = (RadioButton) findViewById(R.id.carRadioButton);
        radioButtonclass = (RadioButton) findViewById(R.id.classRadioButton);
        radioButtonuser = (RadioButton) findViewById(R.id.userRadioButton);
        radioButtoncar.setOnClickListener(this);
        radioButtonclass.setOnClickListener(this);
        radioButtonhome.setOnClickListener(this);
        radioButtonuser.setOnClickListener(this);
    }
    private void  addFragment(String tag, Fragment fragment) {
        FragmentTransaction beginTransaction = supportFragmentManager
                .beginTransaction();
        beginTransaction.replace(R.id.framelayout, fragment,tag);
        //需要确定的tag,需要fragment 在commit之前,添加到回退栈
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homeRadioButton:
                addFragment("fragmentHome",new FragmentHome());
                radioButtonhome.setTextColor(getResources().getColor(R.color.main));
               radioButtonuser.setTextColor(getResources().getColor(R.color.nav));
                radioButtonclass.setTextColor(getResources().getColor( R.color.nav));
                radioButtoncar.setTextColor(getResources().getColor(R.color.nav));

                break;
            case R.id.classRadioButton:
                addFragment("fragmentClass",new FragmentTwo());
                radioButtonclass.setTextColor(getResources().getColor(R.color.main));
                radioButtonuser.setTextColor(getResources().getColor( R.color.nav));
                radioButtonhome.setTextColor(getResources().getColor(R.color.nav));
                radioButtoncar.setTextColor(getResources().getColor( R.color.nav));
                break;
            case R.id.carRadioButton:
                addFragment("fragmentCar",new FragmentCar());
                radioButtoncar.setTextColor(getResources().getColor(R.color.main));
                radioButtonuser.setTextColor(getResources().getColor( R.color.nav));
                radioButtonclass.setTextColor(getResources().getColor( R.color.nav));
                radioButtonhome.setTextColor(getResources().getColor(R.color.nav));
                break;
            case R.id.userRadioButton:
                addFragment("fragmentUser",new FragmentUser());
                radioButtonuser.setTextColor(getResources().getColor(R.color.main));
                radioButtonhome.setTextColor(getResources().getColor( R.color.nav));
                radioButtonclass.setTextColor(getResources().getColor( R.color.nav));
                radioButtoncar.setTextColor(getResources().getColor(R.color.nav));
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
            if (backStackEntryCount>1){
             while (supportFragmentManager.getBackStackEntryCount()>1){
                 supportFragmentManager.popBackStackImmediate();
                 radioButtonhome.setChecked(true);
             }
            }else {
                finish();
            }
        }
        return true;
    }
}
