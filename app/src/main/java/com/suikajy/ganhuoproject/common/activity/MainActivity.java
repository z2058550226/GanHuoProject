package com.suikajy.ganhuoproject.common.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

import com.suikajy.ganhuoproject.R;
import com.suikajy.ganhuoproject.module.home.fragment.HomeFragment;
import com.suikajy.ganhuoproject.module.read.ReadingFragment;
import com.suikajy.library.activity.base.BaseMainActivity;
import com.suikajy.library.utils.ViewUtils;

import java.util.Calendar;

public class MainActivity extends BaseMainActivity {

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // TODO: 2017/3/15 bmob 的相关操作
        mFragmentManager = getSupportFragmentManager();
        switchFragment(0);
    }

    @Override
    protected void setUpView() {
        mBottomNavigationView = $(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        switchFragment(0);
                        break;
                    case R.id.item_collect:
                        switchFragment(2);
                        break;
                    case R.id.item_reading:
                        switchFragment(1);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        $(R.id.tv_today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                // TODO: 2017/3/16 DailyActivity
            }
        });
    }

    @Override
    protected void setUpData() {

    }

    private void switchFragment(int index) {
        Fragment to = mFragmentManager.findFragmentByTag(index + "");
        if (to == null){
            if (index == 0)
                to= ViewUtils.createFragment(HomeFragment.class);
            else if (index==1)
                to=ViewUtils.createFragment(ReadingFragment.class);
        }
    }
}
