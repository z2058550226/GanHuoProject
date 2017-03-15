package com.suikajy.library.imageloader;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.suikajy.library.R;

/**
 * Created by suikajy on 2017/1/16.
 * the request of image
 */

public class ImageRequest {
    private String url;
    private int placeHolder;
    private ImageView imageView;

    public ImageRequest(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imageView = builder.imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public static class Builder {
        private String url;
        private int placeHolder;
        private ImageView imageView;

        public Builder() {
            this.url = "";
            this.placeHolder = R.drawable.default_load_img;
            this.imageView = null;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(@DrawableRes int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imageView = imgView;
            return this;
        }

        public ImageRequest create() {
            if (imageView == null) throw new IllegalArgumentException("the imageView required");
            if ("".equals(url)) throw new IllegalArgumentException("the url cannot be empty");
            return new ImageRequest(this);
        }
    }
}
