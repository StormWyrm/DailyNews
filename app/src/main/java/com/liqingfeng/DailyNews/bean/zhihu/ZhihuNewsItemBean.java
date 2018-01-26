package com.liqingfeng.DailyNews.bean.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lonlife on 2018/1/26.
 */

public class ZhihuNewsItemBean implements Serializable,MultiItemEntity {
    public static final int ZHIHU_NEWS_NORMAL = 0;
    public static final int ZHIHU_NEWS_NO_IMAGE = 1;
    /**
     * images : ["https://pic3.zhimg.com/v2-6140ccce950121546d97c95db2b5d86e.jpg"]
     * type : 0
     * id : 9296054
     * ga_prefix : 032222
     * title : 小事 · 时间是怎么样划过了我皮肤
     * multipic : true
     */

    public int type;
    public int id;
    public String ga_prefix;
    public String title;
    public boolean multipic;
    public List<String> images;
    public int itemType;

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

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ZhihuNewsItemBean{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                ", multipic=" + multipic +
                ", images=" + images +
                '}';
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
