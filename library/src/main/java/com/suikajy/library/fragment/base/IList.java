package com.suikajy.library.fragment.base;

/**
 * Created by suikajy on 2017/3/15.
 */

public interface IList {

    void loadData(int pageIndex);

    void refreshData();

    void loadMore();

    void showError(Exception e);

    void showLoading();

    void showEmpty(String msg);

    void showContent();
}
