package com.suikajy.ganhuoproject.module.home.fragment;

import android.os.Bundle;

import com.suikajy.library.fragment.base.AbsListFragment;

/**
 * Created by suikajy on 2017/3/16.
 */

public class CategoryListFragment extends AbsListFragment {

    private String mType;

    public static CategoryListFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void loadData(final int pageIndex) {

    }

    @Override
    protected int getInitPageIndex() {
        return 1;
    }
}
