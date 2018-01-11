package com.example.liqingfeng.DailyNews.bean.gk;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/30 11:23
 * @DESC: 果壳新闻的Model
 * @VERSION: V1.0
 */

public class GKNews {

    /**
     * now : 2017-03-30T11:20:50.952812+08:00
     * ok : true
     * result : []
     */
    public String now;
    public boolean ok;
    public List<ResultBean> result;


    /**
     * link_v2_sync_img : http://jingxuan.guokr.com/pick/v2/79676/sync/
     * source_name : 站外用户
     * video_url :
     * current_user_has_collected : false
     * likings_count : 35
     * images : []
     * video_duration : null
     * id : 79676
     * category : ad
     * style : article
     * title : 如果游戏中的闪避技能进入生活…
     * source_data : {"image":"http://1.im.guokr.com/VY8V8kDDMj0gIX3ekpdauZDShgEgciXqL-J-0mXOAYQ8AAAAPAAAAFBO.png","title":"果壳小伙伴","id":"None","key":"站外用户","summary":"果壳精选欢迎自媒体入驻，Email联系media@guokr.com，请注明\u201c果壳精选自媒体合作\u201d。"}
     * headline_img_tb : http://1.im.guokr.com/2rZgl7-eOiGGwKfTMjqrygrWvXU4wzubKXd0pSxNo0iUAgAAjAEAAEpQ.jpg?imageView2/1/w/288/h/172
     * link_v2 : http://jingxuan.guokr.com/pick/v2/79676/
     * date_picked : 1.490837442E9
     * is_top : false
     * link : http://jingxuan.guokr.com/pick/79676/
     * headline_img : http://1.im.guokr.com/2rZgl7-eOiGGwKfTMjqrygrWvXU4wzubKXd0pSxNo0iUAgAAjAEAAEpQ.jpg?imageView2/1/w/640/h/480
     * replies_count : null
     * current_user_has_liked : false
     * page_source : http://jingxuan.guokr.com/pick/79676/?ad=1
     * author : 站外用户
     * summary : 在游戏中有着各种各样的闪避、回溯、逃生技能，这些技能使得我们在操作失误或者面对危险状况时能够躲避伤害或降低自己的伤害，化险为夷甚至
     * source : external
     * reply_root_id : 0
     * date_created : 1.48842793E9
     */
    public static class ResultBean {
        public String link_v2_sync_img;
        public String source_name;
        public String video_url;
        public boolean current_user_has_collected;
        public int likings_count;
        public Object video_duration;
        public int id;
        public String category;
        public String style;
        public String title;
        public SourceDataBean source_data;
        public String headline_img_tb;
        public String link_v2;
        public double date_picked;
        public boolean is_top;
        public String link;
        public String headline_img;
        public Object replies_count;
        public boolean current_user_has_liked;
        public String page_source;
        public String author;
        public String summary;
        public String source;
        public int reply_root_id;
        public double date_created;
        public List<String> images;

        public static class SourceDataBean {
            /**
             * image : http://1.im.guokr.com/VY8V8kDDMj0gIX3ekpdauZDShgEgciXqL-J-0mXOAYQ8AAAAPAAAAFBO.png
             * title : 果壳小伙伴
             * id : None
             * key : 站外用户
             * summary : 果壳精选欢迎自媒体入驻，Email联系media@guokr.com，请注明“果壳精选自媒体合作”。
             */

            public String image;
            public String title;
            public String id;
            public String key;
            public String summary;
        }
    }
}
