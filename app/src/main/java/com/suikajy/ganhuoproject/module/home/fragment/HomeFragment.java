package com.suikajy.ganhuoproject.module.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.suikajy.library.fragment.base.AbsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suikajy on 2017/3/15.
 */

public class HomeFragment extends AbsListFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUserVisibleHint(true);
    }

    @Override
    public void loadData(final int pageIndex) {
        final List data = new ArrayList();
        if (pageIndex == getInitPageIndex()){
            //CategoryList
        }
    }

    @Override
    protected int getInitPageIndex() {
        return 1;
    }
}
