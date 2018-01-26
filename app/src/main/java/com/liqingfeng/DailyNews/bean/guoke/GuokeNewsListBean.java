package com.liqingfeng.DailyNews.bean.guoke;

import java.io.Serializable;
import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/30 11:23
 * @DESC: 果壳新闻的Model
 * @VERSION: V1.0
 */

public class GuokeNewsListBean implements Serializable {

    /**
     * now : 2017-03-30T11:20:50.952812+08:00
     * ok : true
     * result : []
     */
    public String now;
    public boolean ok;
    public List<GuokeNewsItemBean> result;


    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<GuokeNewsItemBean> getResult() {
        return result;
    }

    public void setResult(List<GuokeNewsItemBean> result) {
        this.result = result;
    }
}
