package com.liqingfeng.DailyNews.bean.douban.news;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * Created by lonlife on 2018/1/26.
 */

public class DoubanNewsItemBean implements Serializable,MultiItemEntity{
    public static final int DOUBAN_NEW_NOMAL = 0;
    public static final int DOUBAN_NEW_NO_IMAGE = 1;
    /**
     * display_style : 10003
     * is_editor_choice : false
     * published_time : 2017-03-28 19:00:00
     * original_url : https://www.douban.com/photos/album/1643950619/
     * url : https://moment.douban.com/post/166099/?douban_rec=1
     * short_url : https://dou.bz/0iu6Sf
     * is_liked : false
     * author : {}
     * column : 去远方
     * app_css : 7
     * abstract : 可高纬度的英伦之春来之甚晚，已近惊蛰却还是一片阒寂凋零之色。
     * date : 2017-03-28
     * like_count : 25
     * comments_count : 1
     * thumbs : []
     * created_time : 2017-03-14 11:09:27
     * title : 寻春
     * share_pic_url : https://moment.douban.com/share_pic/post/166099.jpg
     * type : 1004
     * id : 166099
     */

    public int display_style;
    public boolean is_editor_choice;
    public String published_time;
    public String original_url;
    public String url;
    public String short_url;
    public boolean is_liked;
    public AuthorBean author;
    public String column;
    public int app_css;
    @SerializedName("abstract")
    public String abstractX;
    public String date;
    public int like_count;
    public int comments_count;
    public String created_time;
    public String title;
    public String share_pic_url;
    public String type;
    public int id;
    public List<PhotosBean> thumbs;

    private int itemType;

    public int getDisplay_style() {
        return display_style;
    }

    public void setDisplay_style(int display_style) {
        this.display_style = display_style;
    }

    public boolean isIs_editor_choice() {
        return is_editor_choice;
    }

    public void setIs_editor_choice(boolean is_editor_choice) {
        this.is_editor_choice = is_editor_choice;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public boolean isIs_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getApp_css() {
        return app_css;
    }

    public void setApp_css(int app_css) {
        this.app_css = app_css;
    }

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShare_pic_url() {
        return share_pic_url;
    }

    public void setShare_pic_url(String share_pic_url) {
        this.share_pic_url = share_pic_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PhotosBean> getThumbs() {
        return thumbs;
    }

    public void setThumbs(List<PhotosBean> thumbs) {
        this.thumbs = thumbs;
    }

    public void setItemType(int itemType){
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return 0;
    }


}
