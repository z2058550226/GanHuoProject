package com.suikajy.library.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by suikajy on 2017/1/14.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        setContentView(setLayoutResourceID());
        setUpView();
        setUpData();
        TextView tv = $(android.R.id.text1);
        ImageView iv=$(android.R.id.icon);
    }

    protected void init(Bundle savedInstanceState) {
        //do nothing at this base class
    }

    public abstract int setLayoutResourceID();

    protected abstract void setUpView();

    protected void setUpData() {
        //do nothing at this base class
    }

    protected <T extends View> T $(@IdRes int id) {
        // TODO: 2017/1/14 what is this??
        return (T) super.findViewById(id);
    }

    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 授权时回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:

                break;
            case 2:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2017/1/14 wtf? umeng
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO: 2017/1/14 umeng???
        MobclickAgent.onPause(this);
    }
}
