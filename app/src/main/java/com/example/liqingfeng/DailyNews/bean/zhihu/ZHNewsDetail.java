package com.example.liqingfeng.DailyNews.bean.zhihu;

import java.util.List;

/**
 * Created by liqingfeng on 2017/3/29.
 */

public class ZHNewsDetail {

    /**
     * body :
     * image_source : 《熔炉》
     * title : 「从 3 岁到 50 岁，我还是没能摆脱被性侵的噩梦」
     * image : https://pic2.zhimg.com/v2-8ed181075dcf469d40486b1980618b09.jpg
     * share_url : http://daily.zhihu.com/story/9318085
     * js : []
     * ga_prefix : 033118
     * images : ["https://pic4.zhimg.com/v2-13226032cca018ce16db5e92db247787.jpg"]
     * type : 0
     * id : 9318085
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    public String body;
    public String image_source;
    public String title;
    public String image;
    public String share_url;
    public String ga_prefix;
    public int type;
    public int id;
    public List<?> js;
    public List<String> images;
    public List<String> css;
}
