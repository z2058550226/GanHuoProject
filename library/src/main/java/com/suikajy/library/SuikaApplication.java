package com.suikajy.library;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Method;

import retrofit2.Retrofit;

/**
 * Created by suikajy on 2017/1/14.
 */

public class SuikaApplication extends Application {
    private static SuikaApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    //单例
    public static SuikaApplication getInstance() {
        return mInstance;
    }

    /**
     * 获取外部存储的缓存, 如果没有挂载, 则使用内部存储
     * @return 存储路径
     */
    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
