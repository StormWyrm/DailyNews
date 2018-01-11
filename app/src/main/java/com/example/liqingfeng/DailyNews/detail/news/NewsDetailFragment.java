package com.example.liqingfeng.DailyNews.detail.news;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.example.liqingfeng.DailyNews.common.util.Constant;
import com.example.liqingfeng.DailyNews.common.util.GlideUtils;
import com.example.liqingfeng.DailyNews.common.util.NetworkImageUtil;
import com.example.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.example.liqingfeng.DailyNews.common.util.SPUtils;
import com.example.liqingfeng.DailyNews.common.util.SnackBarUtil;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 22:26
 * @DESC: Detaily页面---Fragment
 * @VERSION: V1.0
 */
public class NewsDetailFragment extends BaseFragment implements NewsDetailContract.View {
    private static final String TAG = "NewsDetailActivity";
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.fab_share)
    FloatingActionButton fabShare;

    private NewsDetailContract.Presenter mPresenter;
    private WebSettings mWebSetting;
    private String mDetailUrl;
    private int type;//链接过来的地址类型
    @Override
    protected int getViewId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.share(type);
            }
        });
    }

    @Override
    protected void initData() {
        setHasOptionsMenu(true);
        initToolBar();
        initWebViewSetting();
        initWebView();
        mPresenter.loadPage(type, mDetailUrl);
    }

    //初始化ToolBar
    private void initToolBar() {
        Intent intent = mActivity.getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
            mDetailUrl = intent.getStringExtra("url");
            addToolBar(intent.getStringExtra("title"));
            String imageUrl = intent.getStringExtra("image");
            if (!TextUtils.isEmpty(imageUrl)) {
                NetworkImageUtil.loadNetImageDependNet(mActivity, imageView, imageUrl, R
                        .drawable.bg_main_nav_header);
            } else {
                GlideUtils.loadPlaceHolder(mActivity, imageView, R.drawable.bg_main_nav_header);
            }
        }
    }


    private void addToolBar(String title) {
        mActivity.setSupportActionBar(toolBar);

        ActionBar mActionBar = mActivity.getSupportActionBar();
        if (!TextUtils.isEmpty(title)) {
            mActionBar.setTitle(title);
        } else {
            mActionBar.setTitle(R.string.app_name);
        }

        mActionBar.setDisplayHomeAsUpEnabled(true);
        //设置返回键点击功能
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    private void initWebViewSetting() {
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

    //初始化WebView
    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mPresenter.jumpToBrowser(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                stopRefresh();
            }
        });

    }

    //初始化WebView的设置
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
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


    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getActionBarTitle() {
        return toolBar.getTitle().toString();
    }

    @Override
    public void showImage(String imageUrl) {
        NetworkImageUtil.loadNetImageDependNet(mActivity, imageView, imageUrl);
    }


    @Override
    public void loadDataToWebView(String data) {
        webView.loadDataWithBaseURL("x-data://base", data, "text/html",
                "utf-8", null);
        webView.setBackgroundColor(0);
    }

    @Override
    public void startRefresh() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void stopRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        SnackBarUtil.showMessage(mActivity.getWindow().getDecorView(), getString(R.string.load_error_message));
    }

}
