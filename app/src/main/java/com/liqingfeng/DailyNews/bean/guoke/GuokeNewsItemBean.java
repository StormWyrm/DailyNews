package com.liqingfeng.DailyNews.bean.guoke;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lonlife on 2018/1/26.
 */

public class GuokeNewsItemBean implements Serializable,MultiItemEntity{
    public static final int GUOKE_NEWS_NORMAL = 0;
    public static final int GUOKE_NEWS_NO_IMAGE = 1;
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
    public GuokeNewsItemBean.SourceDataBean source_data;
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

    private int itemType;

    public String getLink_v2_sync_img() {
        return link_v2_sync_img;
    }

    public void setLink_v2_sync_img(String link_v2_sync_img) {
        this.link_v2_sync_img = link_v2_sync_img;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public boolean isCurrent_user_has_collected() {
        return current_user_has_collected;
    }

    public void setCurrent_user_has_collected(boolean current_user_has_collected) {
        this.current_user_has_collected = current_user_has_collected;
    }

    public int getLikings_count() {
        return likings_count;
    }

    public void setLikings_count(int likings_count) {
        this.likings_count = likings_count;
    }

    public Object getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(Object video_duration) {
        this.video_duration = video_duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GuokeNewsItemBean.SourceDataBean getSource_data() {
        return source_data;
    }

    public void setSource_data(GuokeNewsItemBean.SourceDataBean source_data) {
        this.source_data = source_data;
    }

    public String getHeadline_img_tb() {
        return headline_img_tb;
    }

    public void setHeadline_img_tb(String headline_img_tb) {
        this.headline_img_tb = headline_img_tb;
    }

    public String getLink_v2() {
        return link_v2;
    }

    public void setLink_v2(String link_v2) {
        this.link_v2 = link_v2;
    }

    public double getDate_picked() {
        return date_picked;
    }

    public void setDate_picked(double date_picked) {
        this.date_picked = date_picked;
    }

    public boolean isIs_top() {
        return is_top;
    }

    public void setIs_top(boolean is_top) {
        this.is_top = is_top;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHeadline_img() {
        return headline_img;
    }

    public void setHeadline_img(String headline_img) {
        this.headline_img = headline_img;
    }

    public Object getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(Object replies_count) {
        this.replies_count = replies_count;
    }

    public boolean isCurrent_user_has_liked() {
        return current_user_has_liked;
    }

    public void setCurrent_user_has_liked(boolean current_user_has_liked) {
        this.current_user_has_liked = current_user_has_liked;
    }

    public String getPage_source() {
        return page_source;
    }

    public void setPage_source(String page_source) {
        this.page_source = page_source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getReply_root_id() {
        return reply_root_id;
    }

    public void setReply_root_id(int reply_root_id) {
        this.reply_root_id = reply_root_id;
    }

    public double getDate_created() {
        return date_created;
    }

    public void setDate_created(double date_created) {
        this.date_created = date_created;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setItemType(int itemType){
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return 0;
    }

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
