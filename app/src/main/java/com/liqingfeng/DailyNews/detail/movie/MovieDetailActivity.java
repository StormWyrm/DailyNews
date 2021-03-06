package com.liqingfeng.DailyNews.detail.movie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.bean.douban.movie.PersonBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.util.GlideUtils;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.activity.BaseMvpActivity;
import com.liqingfeng.sdk.utils.ResourcesUtils;
import com.liqingfeng.sdk.utils.StatusBarUtils;
import com.liqingfeng.DailyNews.detail.movie.adapter.MovieDetailAdapter;
import com.liqingfeng.DailyNews.detail.movie.view.CompatNestedScrollView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lonlife on 2018/1/5.
 */

public class MovieDetailActivity extends BaseMvpActivity<MovieDetailContract.Model, MovieDetailContract.Presenter>
        implements MovieDetailContract.View {

    @BindView(R.id.rv_movie_detail)
    RecyclerView rvMovieDetail;
    @BindView(R.id.iv_toolbar_bg)
    ImageView ivToolbarBg;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.iv_header_bg)
    ImageView ivHeaderBg;
    @BindView(R.id.iv_movie_photo)
    ImageView ivMoviePhoto;
    @BindView(R.id.tv_movie_rating_rate)
    TextView tvMovieRatingRate;
    @BindView(R.id.tv_movie_rating_number)
    TextView tvMovieRatingNumber;
    @BindView(R.id.ll_rating)
    LinearLayout llRating;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.tv_movie_directors)
    TextView tvMovieDirectors;
    @BindView(R.id.tv_movie_casts)
    TextView tvMovieCasts;
    @BindView(R.id.tv_movie_genres)
    TextView tvMovieGenres;
    @BindView(R.id.tv_movie_date)
    TextView tvMovieDate;
    @BindView(R.id.tv_movie_city)
    TextView tvMovieCity;
    @BindView(R.id.ll_movie_header)
    LinearLayout llMovieHeader;
    @BindView(R.id.tv_movie_sub_title)
    TextView tvMovieSubTitle;
    @BindView(R.id.tv_moive_summary)
    TextView tvMoiveSummary;
    @BindView(R.id.nsv_scrollView)
    CompatNestedScrollView nsvScrollView;

    private SubjectsBean mSubjectBean;
    private MovieDetailAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mSubjectBean = (SubjectsBean) extras.getSerializable(BundleKeyConstant.BUNDLE_KEY_MOVIE_SUBJECTBEAN);
        }
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        super.initView(saveInstanceState);
        initHeaderView(mSubjectBean);
        initRecylerView(null);
        mPresenter.getMovieDetail(mSubjectBean.getId());

        StatusBarUtils.setTransparent(this);
        addToolBar(mSubjectBean.getTitle(), true);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        nsvScrollView.bindAlphaView(ivToolbarBg);
        nsvScrollView.setNestedScrollingEnabled(false);
    }

    /**
     * @param context      activity
     * @param subjectsBean bean
     * @param imageView    imageView
     */
    public static void start(Activity context, SubjectsBean subjectsBean, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(BundleKeyConstant.BUNDLE_KEY_MOVIE_SUBJECTBEAN, subjectsBean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (context, imageView, ResourcesUtils.getString(R.string.transition_movie_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new MovieDetailPresenter();
    }

    @Override
    public void showMovieDetail(MovieDetailBean movieDetailBean) {
        tvMovieCity.setText("制片国家/地区：" + movieDetailBean.getCountriesString());
        tvMovieSubTitle.setText(movieDetailBean.getAkaString());
        tvMoiveSummary.setText(movieDetailBean.getSummary());
        initRecylerView(movieDetailBean);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showNetworkError() {

    }

    private void initRecylerView(MovieDetailBean movieDetailBean) {
        if (movieDetailBean == null) {
            mAdapter = new MovieDetailAdapter();
            rvMovieDetail.setLayoutManager(new LinearLayoutManager(mActivity));
            rvMovieDetail.setAdapter(mAdapter);
        } else {
            List<PersonBean> list = movieDetailBean.getDirectors();
            list.addAll(movieDetailBean.getCasts());
            mAdapter = new MovieDetailAdapter(list);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemCLick(position, mAdapter.getItem(position));
                }
            });
            rvMovieDetail.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
            rvMovieDetail.setAdapter(mAdapter);
        }

    }

    private void initHeaderView(SubjectsBean subjectsBean) {
        if (subjectsBean != null) {
            tvMovieRatingNumber.setText(String.valueOf(subjectsBean.getRating().getAverage()));
            tvCollectCount.setText(String.valueOf(subjectsBean.getCollect_count()));
            tvMovieDirectors.setText(subjectsBean.getDirectorsString());
            tvMovieCasts.setText(subjectsBean.getActorsString());
            tvMovieGenres.setText(subjectsBean.getGenresString());
            tvMovieDate.setText(subjectsBean.getYear());
        }
        GlideUtils.loadImage(mActivity, ivMoviePhoto, subjectsBean.getImages().getLarge(),R.mipmap.img_default_movie);
        GlideUtils.displayBlurImg(mActivity, subjectsBean.getImages().getLarge(), ivHeaderBg);
        GlideUtils.displayBlurImg(mActivity, subjectsBean.getImages().getLarge(), ivToolbarBg);

        int headerBgHeight;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            headerBgHeight = toolbar.getLayoutParams().height
                    + StatusBarUtils.getStatusBarHeight(mActivity);
        } else {
            headerBgHeight = toolbar.getLayoutParams().height;
        }

        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

    }

}
