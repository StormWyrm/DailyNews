package com.liqingfeng.DailyNews.bean.douban.news;

/**
 * Created by lonlife on 2018/1/26.
 */

public class ThumbsBean {
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
