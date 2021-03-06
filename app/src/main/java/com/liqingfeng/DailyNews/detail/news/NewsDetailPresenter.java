package com.liqingfeng.DailyNews.detail.news;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsDetailBean;
import com.liqingfeng.DailyNews.bean.douban.news.ThumbsBean;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsDetailBean;
import com.liqingfeng.DailyNews.browser.BrowserActivity;
import com.liqingfeng.DailyNews.AppApplication;
import com.liqingfeng.DailyNews.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.constant.SPConstant;
import com.liqingfeng.DailyNews.util.NetworkUtil;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.volley.HttpRequestCallback;
import com.liqingfeng.sdk.volley.HttpRequestManager;
import com.liqingfeng.sdk.volley.HttpRequestByVolley;
import com.liqingfeng.DailyNews.util.SPUtils;
import com.liqingfeng.DailyNews.util.ToastUtil;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 23:26
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public class NewsDetailPresenter extends NewsDetailContract.Presenter {
    private static final int TYPE_ZHIHU = 0;
    private static final int TYPE_GUOKE = 1;
    private static final int TYPE_DOUBAN = 2;

    private HttpRequestManager mManager = new HttpRequestByVolley();
    private Gson mGson = new Gson();
    private String mDetailUrl;
    private ZhihuNewsDetailBean zhNewsDetail;
    private DoubanNewsDetailBean dbNewsDetail;


    @Override
    public IBaseModel getModel() {
        return new NewsDetailModel();
    }

    @Override
    public void loadPage(final int type, String mDetailUrl) {
        this.mDetailUrl = mDetailUrl;
        if (!NetworkUtil.isNetworkAvailable((Context) mView)) {
            mView.showError();
            return;
        }
        mManager.getRequest(mDetailUrl, new HttpRequestCallback() {
            @Override
            public void onSuccess(String result) {
                switch (type) {
                    case TYPE_ZHIHU:
                        showZHPage(result);
                        break;
                    case TYPE_GUOKE:
                        showGKPage(result);
                        break;
                    case TYPE_DOUBAN:
                        showDBPage(result);
                        break;
                }
            }
        });
    }

    @Override
    public void jumpToBrowser(String url) {
        if ((Boolean) SPUtils.get((Context) mView, SPConstant.WAY_OF_BROWSER, true)) {
            Bundle extra = new Bundle();
            extra.putString(BundleKeyConstant.BUNDLE_KEY_BROWSER_URL, url);

            mView.startNewActivity(BrowserActivity.class, extra);
        } else {
            ((Context) mView).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    @Override
    public void share(int type) {
        String shareText = mView.getActionBarTitle();
        if (type == 0) {
            shareText += zhNewsDetail.share_url + "分享至 DailyNews";
        }
        if (type == 1) {
            shareText += mDetailUrl + "分享至 DailyNews";
        }
        if (type == 2) {
            shareText += dbNewsDetail.short_url + "分享至 DailyNews";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        intent.setType("text/plain");
        ((Context) mView).startActivity(intent);
    }


    @Override
    public void copyLink(int type) {
        ClipboardManager cmb = (ClipboardManager) AppApplication.getInstance().getSystemService(CLIPBOARD_SERVICE);

        String shareText = "";
        if (type == 0) {
            shareText = zhNewsDetail.share_url;
        }
        if (type == 1) {
            shareText = mDetailUrl;
        }
        if (type == 2) {
            shareText = dbNewsDetail.short_url;
        }
        cmb.setText(shareText.trim());
        ToastUtil.shortMessage(AppApplication.getInstance(),
                AppApplication.getInstance().getString(R.string.detail_copy_link_message));
    }

    @Override
    public void openLinkInBrowser(int type) {
        String shareText = "";
        if (type == 0) {
            shareText = zhNewsDetail.share_url;
        }
        if (type == 1) {
            shareText = mDetailUrl;
        }
        if (type == 2) {
            shareText = dbNewsDetail.short_url;
        }
        ((Context) mView).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(shareText)));
    }

    //显示知乎详细页面数据
    private void showZHPage(String result) {

        Log.d(TAG, "onSuccess: " + result);
        Type type = new TypeToken<ZhihuNewsDetailBean>() {
        }.getType();
        zhNewsDetail = mGson.fromJson(result, type);
        mView.showImage(zhNewsDetail.image);
        mView.loadDataToWebView(convertZhihuContent(zhNewsDetail.body));

    }

    //显示果壳精选详细页面数据
    private void showGKPage(String result) {
        mView.loadDataToWebView(convertGuokrContent(result));
    }

    //显示豆瓣一刻页面数据
    private void showDBPage(String result) {

        Type type = new TypeToken<DoubanNewsDetailBean>() {
        }.getType();
        dbNewsDetail = mGson.fromJson(result, type);
        if (dbNewsDetail.thumbs != null && dbNewsDetail.thumbs.size() != 0) {
            ThumbsBean thumbsBean = dbNewsDetail.thumbs.get(0);
            if (thumbsBean.small != null) {
                mView.showImage(thumbsBean.small.url);
            }
        }
        mView.loadDataToWebView(convertDoubanContent());

    }

    //修改知乎详情的样式
    private String convertZhihuContent(String preResult) {

        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((AppApplication.getInstance().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
    }

    //修改果壳详情的样式
    private String convertGuokrContent(String content) {
        // 简单粗暴的去掉下载的div部分
        content.replace("<div class=\"down\" id=\"down-footer\">\n" +
                "        <img src=\"http://static.guokr.com/apps/handpick/images/c324536d.jingxuan-logo.png\" " +
                "class=\"jingxuan-img\">\n" +
                "        <p class=\"jingxuan-txt\">\n" +
                "            <span class=\"jingxuan-title\">果壳精选</span>\n" +
                "            <span class=\"jingxuan-label\">早晚给你好看</span>\n" +
                "        </p>\n" +
                "        <a href=\"\" class=\"app-down\" id=\"app-down-footer\">下载</a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"down-pc\" id=\"down-pc\">\n" +
                "        <img src=\"http://static.guokr.com/apps/handpick/images/c324536d.jingxuan-logo.png\" " +
                "class=\"jingxuan-img\">\n" +
                "        <p class=\"jingxuan-txt\">\n" +
                "            <span class=\"jingxuan-title\">果壳精选</span>\n" +
                "            <span class=\"jingxuan-label\">早晚给你好看</span>\n" +
                "        </p>\n" +
                "        <a href=\"http://www.guokr.com/mobile/\" class=\"app-down\">下载</a>\n" +
                "    </div>", "");

        // 替换css文件为本地文件
        content.replace("<link rel=\"stylesheet\" href=\"http://static.guokr.com/apps/handpick/styles/d48b771f" +
                        ".article.css\" />",
                "<link rel=\"stylesheet\" href=\"file:///android_asset/guokr.article.css\" />");

        // 替换js文件为本地文件
        content.replace("<script src=\"http://static.guokr.com/apps/handpick/scripts/9c661fc7.base.js\"></script>",
                "<script src=\"file:///android_asset/guokr.base.js\"></script>");
        if ((AppApplication.getInstance().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            content.replace("<div class=\"article\" id=\"contentMain\">",
                    "<div class=\"article \" id=\"contentMain\" style=\"background-color:#212b30; color:#878787\">");
        }
        return content;
    }

    //修改豆瓣详情的样式
    private String convertDoubanContent() {

        if (dbNewsDetail.content == null) {
            return null;
        }
        String css;
        if ((AppApplication.getInstance().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_dark.css\" type=\"text/css\">";
        } else {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
        }
        String content = dbNewsDetail.content;
        List<ThumbsBean> imageList = dbNewsDetail.thumbs;
        for (int i = 0; i < imageList.size(); i++) {
            String old = "<img id=\"" + imageList.get(i).tag_name + "\" />";
            String newStr = "<img id=\"" + imageList.get(i).tag_name + "\" "
                    + "src=\"" + imageList.get(i).medium.url + "\"/>";
            content = content.replace(old, newStr);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n");
        builder.append("<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        builder.append("<head>\n<meta charset=\"utf-8\" />\n");
        builder.append(css);
        builder.append("\n</head>\n<body>\n");
        builder.append("<div class=\"container bs-docs-container\">\n");
        builder.append("<div class=\"post-container\">\n");
        builder.append(content);
        builder.append("</div>\n</div>\n</body>\n</html>");

        return builder.toString();
    }


}
