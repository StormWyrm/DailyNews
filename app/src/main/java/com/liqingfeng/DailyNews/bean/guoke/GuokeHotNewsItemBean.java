package com.liqingfeng.DailyNews.bean.guoke;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by lonlife on 2018/1/26.
 */

public class GuokeHotNewsItemBean implements Serializable,MultiItemEntity {
    /**
     * ordinal : 0
     * picture : http://1.im.guokr.com/jpWXLL4xFrd0anUZNs9VeG-kDyydzA8ZV65UYKoGPjJKAgAAEgEAAFBO.png
     * custom_title : 揭开毒品背后的秘密：瘾君子竟是这样诞生的！
     * article_id : 81658
     */

    public int ordinal;
    public String picture;
    public String custom_title;
    public int article_id;

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCustom_title() {
        return custom_title;
    }

    public void setCustom_title(String custom_title) {
        this.custom_title = custom_title;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
