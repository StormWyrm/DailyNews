package com.example.liqingfeng.DailyNews.bean.douban.news;

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
public class DBNews{

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
    public List<PostsBean> posts;

    public static class PostsBean {
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
        public List<ThumbsBean> thumbs;

        public static class AuthorBean {
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
        }

        public static class ThumbsBean {
            /**
             * medium : {"url":"https://img3.doubanio.com/view/presto/medium/public/t123462.jpg","width":960,"height":960}
             * description :
             * large : {"url":"https://img3.doubanio.com/view/presto/large/public/t123462.jpg","width":1000,"height":1000}
             * tag_name : img_1
             * small : {"url":"https://img3.doubanio.com/view/presto/small/public/t123462.jpg","width":320,"height":320}
             * id : 123462
             */

            public MediumBean medium;
            public String description;
            public LargeBean large;
            public String tag_name;
            public SmallBean small;
            public int id;

            public static class MediumBean {
                /**
                 * url : https://img3.doubanio.com/view/presto/medium/public/t123462.jpg
                 * width : 960
                 * height : 960
                 */

                public String url;
                public int width;
                public int height;
            }

            public static class LargeBean {
                /**
                 * url : https://img3.doubanio.com/view/presto/large/public/t123462.jpg
                 * width : 1000
                 * height : 1000
                 */

                public String url;
                public int width;
                public int height;
            }

            public static class SmallBean {
                /**
                 * url : https://img3.doubanio.com/view/presto/small/public/t123462.jpg
                 * width : 320
                 * height : 320
                 */

                public String url;
                public int width;
                public int height;
            }
        }
    }
}
