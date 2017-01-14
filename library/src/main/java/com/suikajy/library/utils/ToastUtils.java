package com.suikajy.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by suikajy on 2017/1/14.
 *
 */

public final class ToastUtils {
    private static Toast sToast;

    /**
     * 该方法可在Application里面继续封装
     * 只使用Application的Context可以在任何主线程执行的地方弹出吐司
     * @param context 吐司上下文
     * @param text 吐司text
     */
    public static void showToast(Context context, String text) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_LONG);
        }
        sToast.show();
    }

    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
        }
    }
}
