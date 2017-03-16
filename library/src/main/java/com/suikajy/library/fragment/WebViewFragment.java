package com.suikajy.library.fragment;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.suikajy.library.R;
import com.suikajy.library.fragment.base.BaseFragment;

/**
 * Created by suikajy on 2017/3/15.
 */

public class WebViewFragment extends BaseFragment {

    protected WebView mWebView;
    protected ProgressBar mProgressBar;
    protected String mUrl;

    public static WebViewFragment newInstance(final String url) {
        WebViewFragment fragment = new WebViewFragment();
        fragment.mUrl = url;
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_webview;
    }

    /**
     * 需要加载的Url<br/>
     * assert中的文件: file:///android_asset/about.htm<br/>
     * 网页: http://www.jianshu.com/users/6725c8e8194f/<br/>
     * <p/>
     *
     * @return 需要加载的Url
     */
    protected String getLoadUrl() {
        return mUrl;
    }

    @Override
    protected void setUpView() {
        mProgressBar = (ProgressBar) getContentView().findViewById(R.id.progressBar);
        mWebView = (WebView) getContentView().findViewById(R.id.webView);
        initWebViewSettings();
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

        mProgressBar.setMax(100);
        mWebView.loadUrl(getLoadUrl());
    }

    @Override
    protected void setUpData() {

    }

    private void initWebViewSettings() {
        WebSettings webSettings = mWebView.getSettings();

        //支持获取收拾焦点, 输入用户名,密码或其他
        mWebView.requestFocusFromTouch();
        webSettings.setJavaScriptEnabled(true);//支持js
        //设置自适应屏幕, 两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webView的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件
        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。

        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        // webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    public boolean canGoBack() {
        return mWebView != null && mWebView.canGoBack();
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }

    //WebViewClient就是帮助WebView处理各种通知, 请求事件的.
    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            onPageLoadFinished(view, url);
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 0) {
                loadStart();
            } else if (newProgress > 90) {
                loadJs();//这种方式并不可取
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    protected void loadStart() {

    }

    protected void loadJs() {

    }

    protected void onPageLoadFinished(WebView view, String url) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null)
            mWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null)
            mWebView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null)
            mWebView.destroy();
    }
}
