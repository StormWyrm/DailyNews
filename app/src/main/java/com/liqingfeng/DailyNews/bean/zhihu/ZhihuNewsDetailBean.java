package com.liqingfeng.DailyNews.bean.zhihu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liqingfeng on 2017/3/29.
 */

public class ZhihuNewsDetailBean implements Serializable{

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
