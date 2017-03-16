package com.suikajy.ganhuoproject.module.home.fragment;

import android.os.Bundle;

import com.suikajy.library.fragment.base.AbsListFragment;

/**
 * Created by suikajy on 2017/3/16.
 */

public class MeiZhiFragment extends AbsListFragment {

    public static MeiZhiFragment newInstance() {
        Bundle args = new Bundle();
        MeiZhiFragment fragment = new MeiZhiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void loadData(int pageIndex) {

    }


}
