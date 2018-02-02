package com.liqingfeng.DailyNews.main.movie.hot;

import android.widget.ImageView;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.detail.movie.MovieDetailActivity;
import com.liqingfeng.DailyNews.main.movie.top.TopMovieFragment;
import com.liqingfeng.sdk.base.IBaseModel;

import io.reactivex.functions.Consumer;


/**
 * Created by lonlife on 2018/1/4.
 */

public class HotMoviePresenter extends HotMovieContract.Presenter {

    @Override
    public IBaseModel getModel() {
        return new HotMovieModel();
    }

    @Override
    void getHotMovie() {
        if(mView == null || mModel == null){
            return;
        }
        mModel.getHotMovie()
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(HotMovieBean hotMovieBean) throws Exception {
                        if(mView == null)
                            return;
                        mView.showHotMovie(hotMovieBean.getSubjects());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView) {
//        Bundle extra = new Bundle();
//        extra.putSerializable("SubjectBean",subjectsBean);
//        mView.startActivity(MovieDetailActivity.class,extra);
        MovieDetailActivity.start(mView.getBindActivity(),subjectsBean,imageView);
    }


    @Override
    void onTopMovieClick() {
        mView.startNewFragment(TopMovieFragment.newInstance());
//        ToastUtil.shortMessage(AppApplication.getInstance(),"Top Movie: ");
    }
}
