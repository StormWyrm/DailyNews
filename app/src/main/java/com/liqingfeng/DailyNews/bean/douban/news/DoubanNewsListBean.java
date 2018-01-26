package com.liqingfeng.DailyNews.bean.douban.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 20:41
 * @DESC: 豆瓣新闻的Modle
 * @VERSION: V1.0
 */
public class DoubanNewsListBean {

    /**
     * count : 0
     * posts :
     * offset : 7200
     * date : 2017-03-28
     * total : 16
     */

    public int count;
    public int offset;
    public String date;
    public int total;
    public List<DoubanNewsItemBean> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DoubanNewsItemBean> getPosts() {
        return posts;
    }

    public void setPosts(List<DoubanNewsItemBean> posts) {
        this.posts = posts;
    }
}
