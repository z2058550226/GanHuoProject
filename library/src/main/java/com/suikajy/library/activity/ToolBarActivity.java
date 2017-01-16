package com.suikajy.library.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.suikajy.library.R;
import com.suikajy.library.activity.base.BaseActivity;

/**
 * Created by suikajy on 2017/1/16.
 * 事先写好一个toolbar布局, 布局中有一个FrameLayout用来装Fragment.
 * 然后将需要添加的界面添加到帧布局中的fragment中
 *
 * 主要需要重写的函数是设置toolbar的标题
 * 设置当前页面的Fragment
 */

public abstract class ToolBarActivity extends BaseActivity {
    protected Toolbar mToolbar;
    protected AppBarLayout mAppBarLayout;
    protected FragmentManager mFragmentManager;
    protected boolean mIsHidden = false;

    @Override
    protected void init(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void setUpView() {
        mAppBarLayout = $(R.id.appbar_layout);
        mToolbar = $(R.id.toolbar);
        mToolbar.setTitle(getToolbarTitle());
        setUpToolBar();
    }

    private void setUpToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected abstract int getToolbarTitle();

    @Override
    protected void setUpData() {
        mFragmentManager.beginTransaction().replace(R.id.fl_content, setFragment()).commit();
    }

    protected abstract Fragment setFragment();

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_content;
    }
    //设置透明度
    protected void setAppBarAlpha(float alpha) {
        mAppBarLayout.setAlpha(alpha);
    }
    //隐藏或显示, 带动画
    public void hideOrShowToolBar() {
        mAppBarLayout.animate()
                .translationY(mIsHidden ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }
    //设置可见, 无动画
    protected void setAppBarVisiblity(int visiblity) {
        mAppBarLayout.setVisibility(visiblity);
    }
}
