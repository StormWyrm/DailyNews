package com.liqingfeng.DailyNews.common.util;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/23 10:33
 * @DESC: 保存API常量
 * @VERSION: V1.0
 */
public class Api {
    // 所有的知乎日报API的HTTP METHOD 均为GET
    // 知乎日报base url,将文章id拼接值base url之后即可
    public static final String ZHIHU_DAILY_BASE_URL = "http://news-at.zhihu.com/story/";

    // 获取界面启动图像
    // start_image后面为图像分辨率
    public static final String START_IMAGE = "http://news-at.zhihu.com/api/4/start-image/1080*1776";


    public static final String ZHIHU_BASE = "http://news-at.zhihu.com";

    // 最新消息
    // ZHIHU_NEWS API替代，拼接当日日期后可以获取
    public static final String ZHIHU_LATEST = "http://news-at.zhihu.com/api/4/news/latest";

    // 消息内容获取与离线下载
    // 在最新消息中获取到的id，拼接到这个NEWS之后，可以获得对应的JSON格式的内容
    public static final String ZHIHU_NEWS = "http://news-at.zhihu.com/api/4/news/";

    // 过往消息
    // 若要查询的11月18日的消息，before后面的数字应该为20161118
    public static final String ZHIHU_HISTORY = "http://news.at.zhihu.com/api/4/news/before/";

    // 热门消息
    // 请注意！ 此 API 仍可访问，但是其内容未出现在最新的『知乎日报』 App 中。
    public static final String ZHIHU_HOT = "http://news-at.zhihu.com/api/3/news/hot";


    public static final String GUOKE_BASE = "http://apis.guokr.com";

    // 获取果壳精选的文章列表,通过组合相应的参数成为完整的url
    // Guokr handpick articles. make complete url by combining params
    public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article" +
            ".json?retrieve_type=by_since&category=all&limit=25&ad=1";

    // 获取果壳文章的具体信息 V1
    // specific information of guokr post V1
    public static final String GUOKR_ARTICLE_LINK_V1 = "http://jingxuan.guokr.com/pick/";


    // 获取果壳精选的轮播文章列表
    // carousel posts
    public static final String GUOKR_HANDPICK_CAROUSEL = "http://apis.guokr" +
            ".com/flowingboard/item/handpick_carousel.json";


    public static final String DOUBAN_BASE = "https://moment.douban.com";

    // 豆瓣一刻
    // 根据日期查询消息列表
    // eg:https://moment.douban.com/api/stream/date/2016-08-11
    public static final String DOUBAN_MOMENT = "https://moment.douban.com/api/stream/date/";

    // 获取文章具体内容
    // eg:https://moment.douban.com/api/post/100484
    public static final String DOUBAN_ARTICLE_DETAIL = "https://moment.douban.com/api/post/";
}
