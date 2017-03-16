package com.suikajy.library.fragment.base;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.suikajy.library.R;
import com.suikajy.library.utils.ToastUtils;
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
        mAdapter.applyGlobalMultiTypePool();
        mLoadMoreWrapper = new LoadMoreWrapper(getContext(), mAdapter);
        mLoadMoreWrapper.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {
                loadData(mCurrentPageIndex);
            }

            @Override
            public void onLoadMore() {
                if (isCanLoadMore)
                    AbsListFragment.this.loadMore();
            }
        });
    }

    @Override
    protected final void setUpView() {
        mStatusViewLayout = $(R.id.status_view_layout);
        mSwipeRefreshLayout = $(R.id.swipe_refresh_layout);
        mRecyclerView = $(R.id.recyclerview);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        customConfig();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mStatusViewLayout.setmOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatusViewLayout.showLoading();
                loadData(getInitPageIndex());
            }
        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected void lazyLoad() {
        showLoading();
        loadData(getInitPageIndex());
    }

    @Override
    public void refreshData() {
        mCurrentPageIndex = getInitPageIndex();
        if (isCanLoadMore)
            mLoadMoreWrapper.showLoadMore();
        loadData(getInitPageIndex());
    }

    @Override
    public void loadMore() {
        loadData(++mCurrentPageIndex);
    }

    @Override
    public abstract void loadData(int pageIndex);

    //region 可以直接调用的方法

    /**
     * 列表数据接收成功时调用(相关的实现类需要手动区调用此方法)
     *
     * @param pageIndex 当前请求的页数
     * @param items     返回的数据
     */
    @SuppressWarnings("unchecked")
    protected final void onDataSuccessReceived(int pageIndex, List items) {
        showContent();
        if (pageIndex == getInitPageIndex() && items.size() <= 0) {//无数据显示空状态
            showEmpty(getEmptyMsg());
        } else if (pageIndex == getInitPageIndex()) {//刷新
            mItems.clear();
            mItems.addAll(items);
        } else if (items != null && items.size() != 0) {//加载更多
            mItems.addAll(items);
        } else {//没有更多数据了
            mCurrentPageIndex--;
            mLoadMoreWrapper.showLoadComplete();
        }

        mLoadMoreWrapper.notifyDataSetChanged();
    }

    /**
     * 返回当前列表的数据
     */
    protected final List getItems() {
        return mItems;
    }

    /**
     * 添加分割线
     */
    protected final void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (mRecyclerView != null)
            mRecyclerView.addItemDecoration(itemDecoration);
    }

    //endregion

    //region 根据具体的情况可以选择性实现下面的方法

    protected void customConfig() {
    }

    protected int getInitPageIndex() {
        return 1;
    }

    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(mItems);
    }

    /**
     * 默认使用线性布局的recycler view, 可以更改为其他的
     */
    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @NonNull
    protected String getEmptyMsg() {
        return "无数据";
    }
    //endregion

    //region 数据加载状态的处理

    @Override
    public void showError(Exception e) {
        if (mCurrentPageIndex == getInitPageIndex()){
            mStatusViewLayout.showError(e.getMessage());
        }else {
            mLoadMoreWrapper.showLoadError();
            ToastUtils.showToast(getMContext(),e.getMessage());
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmpty(String msg) {
        mStatusViewLayout.showEmpty(msg);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        mStatusViewLayout.showLoading();
    }

    @Override
    public void showContent() {
        mStatusViewLayout.showContent();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    //endregion

}
