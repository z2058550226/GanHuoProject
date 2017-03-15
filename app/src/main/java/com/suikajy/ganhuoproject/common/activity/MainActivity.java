package com.suikajy.ganhuoproject.common.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.suikajy.ganhuoproject.R;
import com.suikajy.library.activity.base.BaseMainActivity;
import com.suikajy.library.utils.ViewUtils;

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

    }

    private void switchFragment(int index) {
        Fragment to = mFragmentManager.findFragmentByTag(index + "");
        if (to == null){
            if (index == 0)
                to= ViewUtils.createFragment(HomeFragment.class)
        }
    }
}
