package com.liqingfeng.DailyNews.bean.douban.news;

/**
 * Created by lonlife on 2018/1/26.
 */

public class PhotosBean {
    /**
     * medium : {"url":"https://img3.doubanio.com/view/presto/medium/public/542043.jpg","width":960,"height":960}
     * description :
     * large : {"url":"https://img3.doubanio.com/view/presto/large/public/542043.jpg","width":1000,"height":1000}
     * tag_name : img_11
     * small : {"url":"https://img3.doubanio.com/view/presto/small/public/542043.jpg","width":320,"height":320}
     * id : 542043
     */

    public MediumPhotoBean medium;
    public String description;
    public LargePhotoBean large;
    public String tag_name;
    public SmallPhotoBean small;
    public int id;

    public MediumPhotoBean getMedium() {
        return medium;
    }

    public void setMedium(MediumPhotoBean medium) {
        this.medium = medium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LargePhotoBean getLarge() {
        return large;
    }

    public void setLarge(LargePhotoBean large) {
        this.large = large;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public SmallPhotoBean getSmall() {
        return small;
    }

    public void setSmall(SmallPhotoBean small) {
        this.small = small;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class MediumPhotoBean {
        /**
         * url : https://img3.doubanio.com/view/presto/medium/public/542043.jpg
         * width : 960
         * height : 960
         */

        public String url;
        public int width;
        public int height;
    }

    public static class LargePhotoBean {
        /**
         * url : https://img3.doubanio.com/view/presto/large/public/542043.jpg
         * width : 1000
         * height : 1000
         */

        public String url;
        public int width;
        public int height;
    }

    public static class SmallPhotoBean {
        /**
         * url : https://img3.doubanio.com/view/presto/small/public/542043.jpg
         * width : 320
         * height : 320
         */

        public String url;
        public int width;
        public int height;
    }
}
