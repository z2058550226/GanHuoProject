package com.suikajy.library.fragment.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.suikajy.library.R;
import com.suikajy.library.widget.StatusViewLayout;
import com.suikajy.library.widget.loadmore.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by suikajy on 2017/3/15.
 * 默认线性布局的Fragment封装
 */

public abstract class AbsListFragment extends LazyLoadFragment implements IList {
    private StatusViewLayout mStatusViewLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private LoadMoreWrapper mLoadMoreWrapper;
    private int mCurrentPageIndex;
    private List mItems;

    private boolean isCanLoadMore = true;

    public void disAbleLoadMore() {
        isCanLoadMore = false;
        mLoadMoreWrapper.disableLoadMore();
    }

    @Override
    protected final int setLayoutResourceID() {
        return R.layout.fragment_base_recyclerview;
    }

    @Override
    protected final void init() {
        mCurrentPageIndex = getInitPageIndex();
        mItems = new ArrayList<>();
        MultiTypeAdapter mAdapter = getAdapter();
    }


    //region 可以直接调用的方法


    //endregion

    //region 根据具体的情况可以选择性实现下面的方法

    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(mItems);
    }

    protected int getInitPageIndex() {
        return 1;
    }
    //endregion

}
