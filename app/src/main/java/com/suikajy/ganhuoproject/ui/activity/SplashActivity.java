package com.suikajy.ganhuoproject.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.suikajy.ganhuoproject.R;
import com.suikajy.library.activity.base.BaseActivity;

/**
 *
 * Created by suikajy on 2017/1/14.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setUpView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityWithoutExtras(MainActivity.class);
                finish();
            }
        }, 1500);
    }
}
