package com.liqingfeng.DailyNews.detail.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseMvpActivity;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.GlideUtils;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.common.util.StatusBarUtils;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/31 19:52
 * @DESC: 消息详情界面
 * @VERSION: V1.0
 */
public class NewsDetailActivity
        extends BaseMvpActivity<NewsDetailContract.Model, NewsDetailContract.Presenter>
        implements NewsDetailContract.View {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.fab_share)
    FloatingActionButton fabShare;
    @BindView(R.id.pb)
    ContentLoadingProgressBar pb;

    private String title;
    private int type;//链接过来的地址类型
    private String imageUrl;
    private String mDetailUrl;
    private WebSettings mWebSetting;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            type = intent.getIntExtra("type", 0);
            title = intent.getStringExtra("title");
            imageUrl = intent.getStringExtra("image");
            mDetailUrl = intent.getStringExtra("url");
        }

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        super.initView(saveInstanceState);
        StatusBarUtils.setTransparent(this);
        addToolBar(title, true);
        initWebView();

        GlideUtils.loadImage(mActivity, imageView, imageUrl, R.mipmap.bg_main_nav_header);
        mPresenter.loadPage(type, mDetailUrl);

    }

    @Override
    protected void initListener() {
        super.initListener();
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.share(type);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_copy_link:
                mPresenter.copyLink(type);
                break;
            case R.id.detail_open_link_in_browser:
                mPresenter.openLinkInBrowser(type);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public String getActionBarTitle() {
        return toolBar.getTitle().toString();
    }

    @Override
    public void showImage(String imageUrl) {
        GlideUtils.loadImage(mActivity, imageView, imageUrl, R.mipmap.bg_main_nav_header);
    }

    @Override
    public void loadDataToWebView(String data) {
        webView.loadDataWithBaseURL("x-data://base", data, "text/html",
                "utf-8", null);
        webView.setBackgroundColor(0);
    }

    @Override
    public void showError() {
        SnackBarUtil.showMessage(mActivity.getWindow().getDecorView(), getString(R.string.load_error_message));
    }

    //初始化WebView
    private void initWebView() {

        mWebSetting = webView.getSettings();

        //能够和js交互
        mWebSetting.setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        mWebSetting.setBuiltInZoomControls(false);
        //缓存
        mWebSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        mWebSetting.setDomStorageEnabled(true);
        //开启application Cache功能
        mWebSetting.setAppCacheEnabled(false);

        mWebSetting.setBlockNetworkImage(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mPresenter.jumpToBrowser(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (pb != null)
                    pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (pb != null)
                    pb.setVisibility(View.GONE);
                mToolBar.setTitle(title);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (pb != null)
                    pb.setProgress(newProgress);
            }
        });
    }

}
