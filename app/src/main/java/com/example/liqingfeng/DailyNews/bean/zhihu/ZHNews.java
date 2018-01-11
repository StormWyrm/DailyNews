package com.example.liqingfeng.DailyNews.bean.zhihu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liqingfeng on 2017/3/23.
 */

public class ZHNews {

    /**
     * date : 20170322
     * stories : [{"images":["https://pic3.zhimg.com/v2-6140ccce950121546d97c95db2b5d86e.jpg"],"type":0,"id":9296054,"ga_prefix":"032222","title":"小事 · 时间是怎么样划过了我皮肤"},{"images":["https://pic2.zhimg.com/v2-6bf5a0bf4db233631d95da4d5f75bbe9.jpg"],"type":0,"id":9306934,"ga_prefix":"032221","title":"世上哪有不寂寞的大人"},{"title":"逐个认一遍，《迁徙的鸟》里面都有哪些鸟儿？","ga_prefix":"032220","images":["https://pic4.zhimg.com/v2-3d2de246b2257927248bd93203359e9f.jpg"],"multipic":true,"type":0,"id":9304536},{"images":["https://pic1.zhimg.com/v2-9f1b1a63ab13f1b9a68f82a6c1ffd864.jpg"],"type":0,"id":9306748,"ga_prefix":"032219","title":"马拉松比赛越来越多，人们都要跑不过来了"},{"images":["https://pic1.zhimg.com/v2-a3cbc46cf4282ecf49cd43aeb174357c.jpg"],"type":0,"id":9307467,"ga_prefix":"032218","title":"连片名都一样，《疯狂动物城》是抄袭的吗？"},{"images":["https://pic4.zhimg.com/v2-1a1810de1c1993c24f5f904c2600c2bf.jpg"],"type":0,"id":9303284,"ga_prefix":"032218","title":"中国的自动驾驶技术会超过美国吗？"},{"images":["https://pic2.zhimg.com/v2-70ee89e447ccf1fa54a8e59a72a7f011.jpg"],"type":0,"id":9305722,"ga_prefix":"032217","title":"也许我们终会抛弃电影，就像抛弃一件老旧不堪实用的家具"},{"images":["https://pic3.zhimg.com/v2-3281114637dc2444b3903eb36ff36576.jpg"],"type":0,"id":9305624,"ga_prefix":"032216","title":"刚进入职场，我应该如何理财？"},{"images":["https://pic3.zhimg.com/v2-b254a278198e6cc8fd635bc5599c0c66.jpg"],"type":0,"id":9306992,"ga_prefix":"032215","title":"为什么美国的这种门把手和门一样宽？"},{"images":["https://pic1.zhimg.com/v2-ebc1a630b861bdf62396876543291a34.jpg"],"type":0,"id":9305774,"ga_prefix":"032213","title":"宇宙是怎么开始的？嗯\u2026\u2026宇宙其实没有开端"},{"images":["https://pic3.zhimg.com/v2-af80fdd343ac8afdfd052543e3e1975a.jpg"],"type":0,"id":9306667,"ga_prefix":"032212","title":"苹果发布了红色的 iPhone 7，和更便宜的 iPad"},{"images":["https://pic3.zhimg.com/v2-42b00ef141a5224f9c77f4524899cd42.jpg"],"type":0,"id":9306599,"ga_prefix":"032212","title":"大误 · 穿搭还是得学时尚博主"},{"images":["https://pic2.zhimg.com/v2-252653b7eb8368a12b1b49514f2f45d9.jpg"],"type":0,"id":9305940,"ga_prefix":"032210","title":"天天都在用的插头和插座，里面藏着多少电气知识？"},{"images":["https://pic1.zhimg.com/v2-7b57ccdf54d7646355b497a42cf3ce08.jpg"],"type":0,"id":9304650,"ga_prefix":"032209","title":"腌笃鲜，属于春天的鲜味"},{"images":["https://pic3.zhimg.com/v2-eb6f9ddb6772c03bd7ee89240572fbd6.jpg"],"type":0,"id":9305960,"ga_prefix":"032208","title":"孩子从幼儿园回来后，哭着喊着再也不要去怎么办？"},{"images":["https://pic1.zhimg.com/v2-d1084e43f3ab29afb5f2c6aa8a79414c.jpg"],"type":0,"id":9305884,"ga_prefix":"032207","title":"粗看起来就像个普通白炽灯，一口价 3000 美元"},{"images":["https://pic1.zhimg.com/v2-33ca73bf70dfe30eb2634101702b2e38.jpg"],"type":0,"id":9305808,"ga_prefix":"032207","title":"在线棋牌游戏真的不会被认定为「开设赌场」吗？"},{"images":["https://pic1.zhimg.com/v2-4afaba535a1b40c5463986cb79a4f4b0.jpg"],"type":0,"id":9305686,"ga_prefix":"032207","title":"男人应如何自拍（才能得到妹子喜欢）？"},{"images":["https://pic3.zhimg.com/v2-3c21c7ff71f8317cddb42b79eaf7ee7e.jpg"],"type":0,"id":9304247,"ga_prefix":"032206","title":"瞎扯 · 如何正确地吐槽"}]
     */
    public String date;
    public List<StoriesBean> stories;

    public static class StoriesBean implements Serializable {
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

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }
    }
    @Override
    public String toString() {
        return "ZHNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }
}
