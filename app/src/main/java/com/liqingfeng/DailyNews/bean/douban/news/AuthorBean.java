package com.liqingfeng.DailyNews.bean.douban.news;

/**
 * Created by lonlife on 2018/1/26.
 */

public class AuthorBean {
    /**
     * is_followed : false
     * uid : pingzills
     * url : https://www.douban.com/people/pingzills/
     * avatar : https://img5.doubanio.com/icon/u2109295-6.jpg
     * name : 小力子百合
     * is_special_user : false
     * n_posts : 0
     * alt : 心晴就好
     * large_avatar : https://img5.doubanio.com/icon/up2109295-6.jpg
     * id : 2109295
     * is_auth_author : false
     */

    public boolean is_followed;
    public String uid;
    public String url;
    public String avatar;
    public String name;
    public boolean is_special_user;
    public int n_posts;
    public String alt;
    public String large_avatar;
    public String id;
    public boolean is_auth_author;

    public boolean isIs_followed() {
        return is_followed;
    }

    public void setIs_followed(boolean is_followed) {
        this.is_followed = is_followed;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_special_user() {
        return is_special_user;
    }

    public void setIs_special_user(boolean is_special_user) {
        this.is_special_user = is_special_user;
    }

    public int getN_posts() {
        return n_posts;
    }

    public void setN_posts(int n_posts) {
        this.n_posts = n_posts;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getLarge_avatar() {
        return large_avatar;
    }

    public void setLarge_avatar(String large_avatar) {
        this.large_avatar = large_avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIs_auth_author() {
        return is_auth_author;
    }

    public void setIs_auth_author(boolean is_auth_author) {
        this.is_auth_author = is_auth_author;
    }
}
