package com.suikajy.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suikajy.library.R;
import com.suikajy.library.activity.base.BaseActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by suikajy on 2017/1/16.
 * 用于专门展示一张图片的Activity, 可以实现左右翻页, 图片放大伸缩等效果
 * 相册聊天QQ空间朋友圈的图片都有这种效果
 */

public class ViewPicActivity extends BaseActivity {
    public final static String IMG_URLS = "ImageUrls";
    public final static String IMG_INDEX = "ImageIndex";
    private ViewPager mViewPager;
    private TextView mTvIndex;
    // TODO: 2017/1/16 what view?
    private AppCompatImageView mIvDownload;
    private ArrayList<String> mUrlList;
    private int mCurrentIndex = 0;
    private String mSavePath;

    /**
     * 打开当前的Activity
     *
     * @param context  context
     * @param view     共享元素的imageView
     * @param urls     连接, 可能不止一张图
     * @param position 索引
     */
    public static void openActivity(Context context, View view, ArrayList<String> urls, int position) {
        Intent intent = new Intent(context, ViewPicActivity.class);
        intent.putStringArrayListExtra(IMG_URLS, urls);
        intent.putExtra(IMG_INDEX, position);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    /**
     * 打开当前Activity的重载方法, 简化为一个url
     */
    public static void openActivity(Context context, View view, String url) {
        ArrayList<String> urls = new ArrayList<>();
        urls.add(url);
        openActivity(context, view, urls, 0);
    }

    //初始化, 获取url和索引
    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mUrlList = getIntent().getExtras().getStringArrayList(IMG_URLS);
        mCurrentIndex = getIntent().getExtras().getInt(IMG_INDEX);
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_view_pic;
    }

    @Override
    protected void setUpView() {
        mViewPager = $(R.id.viewpager);
        mTvIndex = $(R.id.tv_index);
        mIvDownload = $(R.id.iv_download);
        mTvIndex.setText((mCurrentIndex + 1) + "/" + mUrlList.size());
        mIvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPicture(0);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
                mTvIndex.setText((mCurrentIndex + 1) + "/" + mUrlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void setUpData() {
        mViewPager.setAdapter(new MyViewPager(this));
    }

    private void downloadPicture(int i) {
        // TODO: 2017/1/16 下载图片
    }

    class MyViewPager extends PagerAdapter {
        Context context;

        public MyViewPager(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            photoView.setLayoutParams(layoutParams);
            // TODO: 2017/1/16 封装Glide 加载PhotoView的图片

            container.addView(photoView);
            return photoView;
        }

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
