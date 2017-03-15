package com.suikajy.library.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suikajy.library.R;

/**
 * Created by suikajy on 2017/3/15.
 */

public abstract class BaseFragment extends Fragment {

    private View mContentView;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(getMContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    protected abstract int setLayoutResourceID();

    protected void init(){

    }

    protected abstract void setUpView();

    protected abstract void setUpData();

    protected <T extends View> T $(int id){return (T) mContentView.findViewById(id);}

    protected View getContentView() {return mContentView;}

    public Context getMContext(){ return mContext;}

    protected ProgressDialog getmProgressDialog(){return mProgressDialog;}
}
