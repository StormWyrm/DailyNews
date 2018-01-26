package com.liqingfeng.DailyNews.bean.douban.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liqingfeng on 2017/3/31.
 */

public class DoubanNewsDetailBean {

    /**
     * display_style : 10003
     * short_url : https://dou.bz/0iu6Sf
     * abstract : 可高纬度的英伦之春来之甚晚，已近惊蛰却还是一片阒寂凋零之色。
     * app_css : 7
     * like_count : 137
     * thumbs : []
     * created_time : 2017-03-14 11:09:27
     * id : 166099
     * is_editor_choice : false
     * original_url : https://www.douban.com/photos/album/1643950619/
     * content :
     * share_pic_url : https://moment.douban.com/share_pic/post/166099.jpg
     * type : 1004
     * is_liked : false
     * photos : []
     * published_time : 2017-03-28 19:00:00
     * url : https://moment.douban.com/post/166099/?douban_rec=1
     * author : {"is_followed":false,"uid":"pingzills","url":"https://www.douban
     * .com/people/pingzills/","avatar":"https://img5.doubanio.com/icon/u2109295-6.jpg",
     * "name":"小力子百合","is_special_user":false,"n_posts":0,"alt":"心晴就好",
     * "large_avatar":"https://img5.doubanio.com/icon/up2109295-6.jpg","id":"2109295",
     * "is_auth_author":false}
     * column : 去远方
     * comments_count : 9
     * title : 寻春
     */

    public int display_style;
    public String short_url;
    @SerializedName("abstract")
    public String abstractX;
    public int app_css;
    public int like_count;
    public String created_time;
    public int id;
    public boolean is_editor_choice;
    public String original_url;
    public String content;
    public String share_pic_url;
    public String type;
    public boolean is_liked;
    public String published_time;
    public String url;
    public AuthorBean author;
    public String column;
    public int comments_count;
    public String title;
    public List<ThumbsBean> thumbs;
    public List<PhotosBean> photos;


    public int getDisplay_style() {
        return display_style;
    }

    public void setDisplay_style(int display_style) {
        this.display_style = display_style;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public int getApp_css() {
        return app_css;
    }

    public void setApp_css(int app_css) {
        this.app_css = app_css;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_editor_choice() {
        return is_editor_choice;
    }

    public void setIs_editor_choice(boolean is_editor_choice) {
        this.is_editor_choice = is_editor_choice;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public boolean isIs_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ThumbsBean> getThumbs() {
        return thumbs;
    }

    public void setThumbs(List<ThumbsBean> thumbs) {
        this.thumbs = thumbs;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }
}
