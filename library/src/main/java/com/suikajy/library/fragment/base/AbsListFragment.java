package com.suikajy.library.fragment.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.suikajy.library.widget.StatusViewLayout;

/**
 * Created by suikajy on 2017/3/15.
 */

public abstract class AbsListFragment extends LazyLoadFragment implements IList {
    private StatusViewLayout mStatusViewLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private LoadMoreW
}
