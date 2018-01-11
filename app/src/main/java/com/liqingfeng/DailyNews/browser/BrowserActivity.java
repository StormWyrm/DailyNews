package com.liqingfeng.DailyNews.browser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.util.Constant;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.common.util.SPUtils;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/6 19:43
 * @DESC: 内置浏览器
 * @VERSION: V1.0
 */
public class BrowserActivity extends BaseActivity {
    @BindView(R.id.pb)
    ContentLoadingProgressBar progressBar;
    @BindView(R.id.web_view)
    WebView mWebView;

    private WebSettings mWebSetting;

    @Override
    protected int getViewId() {
        return R.layout.activity_browser;
    }


    @Override
    protected void initData(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            mWebView.restoreState(saveInstanceState);
            return;
        }
        initWebView();
        initWebViewSetting();

        Intent intent = getIntent();
        if (intent != null) {

            mWebView.loadUrl(intent.getStringExtra("url"));
            mWebView.setBackgroundColor(0);
        }

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        addToolBar("", true);
    }

    //初始化WebView
    private void initWebView() {

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mActionBar.setTitle(title);
            }

        });
    }

    //初始化settings
    private void initWebViewSetting() {
        mWebSetting = mWebView.getSettings();
        mWebSetting.setJavaScriptEnabled(true);
        if (NetworkUtil.isWifi(mActivity)) {
            mWebSetting.setBlockNetworkImage(false);
        } else {
            if ((Boolean) SPUtils.get(mActivity, Constant.Config
                    .WAY_OF_IMAGE_SHOW, false)) {
                mWebSetting.setBlockNetworkImage(true);
            } else {
                mWebSetting.setBlockNetworkImage(false);
            }
        }
    }



    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

}
