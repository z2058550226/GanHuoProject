package com.suikajy.library.activity.base;

import android.os.Process;

import com.suikajy.library.SuikaApplication;
import com.suikajy.library.utils.ToastUtils;

/**
 * Created by suikajy on 2017/1/14.
 *
 */

public abstract class BaseMainActivity extends BaseActivity {
    private long lastBackKeyDownTick = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1500;

    @Override
    public void onBackPressed() {
        if (beforeOnBackPressed()) {
            final long currentTick = System.currentTimeMillis();
            if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
                ToastUtils.showToast(SuikaApplication.getInstance(),"再按一次退出");
                lastBackKeyDownTick = currentTick;
            }else {
                finish();
                Process.killProcess(Process.myPid());
            }
        }
    }

    protected boolean beforeOnBackPressed() {
        return true;
    }
}
