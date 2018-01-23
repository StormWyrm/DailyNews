package com.liqingfeng.DailyNews.bean;

import android.net.Uri;

/**
 * Created by lonlife on 2018/1/23.
 */

public class RxEventHeadBean {
    private Uri uri;

    public RxEventHeadBean(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "RxEventHeadBean{" +
                "uri=" + uri +
                '}';
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
