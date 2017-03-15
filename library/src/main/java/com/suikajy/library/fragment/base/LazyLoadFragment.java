package com.suikajy.library.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by suikajy on 2017/3/15.
 */

public abstract class LazyLoadFragment extends BaseFragment {

    protected boolean isViewCreated = false;
    protected boolean isFirstLoad = true;
    protected boolean isNeedInitView = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        if (isNeedInitView) {
            lazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirstLoad) {
            if (isViewCreated) {
                isFirstLoad = false;
                lazyLoad();
            } else {
                isNeedInitView = true;
            }
        }
    }

    protected abstract void lazyLoad();
}
