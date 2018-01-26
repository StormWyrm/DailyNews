package com.liqingfeng.DailyNews.bean.guoke;

import java.io.Serializable;
import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 13:44
 * @DESC: 果壳轮播图
 * @VERSION: V1.0
 */
public class GuokeHotNewsListBean implements Serializable {

    /**
     * now : 2017-04-04T13:43:01.234788+08:00
     * ok : true
     * result : [{"ordinal":0,"picture":"http://1.im.guokr
     * .com/jpWXLL4xFrd0anUZNs9VeG-kDyydzA8ZV65UYKoGPjJKAgAAEgEAAFBO.png","custom_title":"揭开毒品背后的秘密：瘾君子竟是这样诞生的！",
     * "article_id":81658},{"ordinal":1,"picture":"http://1.im.guokr
     * .com/r3vRkTZL8oli7J50xcU3Nj2fw2akKqh1-s9oYeiy2TdpAwAAjAEAAFBO.png",
     * "custom_title":"今年的索尼摄影大赛出图了！镜头下的世界，每一幅都是一个故事","article_id":81572},{"ordinal":2,"picture":"http://3.im.guokr
     * .com/L0uNStEWrZCgqxtpk4wR4ZOh4Qg5Y3H1S1k1sNolDV-VAgAALwEAAFBO.png","custom_title":"震撼美图来袭！围观欧洲最大活火山",
     * "article_id":81482},{"ordinal":3,"picture":"http://2.im.guokr
     * .com/35Udeki_YFoM13N1GrVLkn5F_sBUdQf2RSyP1k-IEA3QAgAAUAEAAEpQ.jpg","custom_title":"实验探秘：如何释放水的洁净能量",
     * "article_id":81258},{"ordinal":4,"picture":"http://3.im.guokr
     * .com/wiLmUw2QVysEixjSu-HyQIEnYCBYr6SKeKxPWZTKkUGXEwAAJAkAAEpQ.jpg","custom_title":"海底世界除了幽灵潜艇，还藏了几个亿",
     * "article_id":81426}]
     */

    public String now;
    public boolean ok;
    public List<GuokeHotNewsItemBean> result;

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

    public List<GuokeHotNewsItemBean> getResult() {
        return result;
    }

    public void setResult(List<GuokeHotNewsItemBean> result) {
        this.result = result;
    }
}
