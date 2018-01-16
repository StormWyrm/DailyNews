package com.liqingfeng.DailyNews.main.movie.hot;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.network.DoubanMovieService;
import com.liqingfeng.DailyNews.network.NetworkHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lonlife on 2018/1/4.
 */

public class HotMovieModel implements HotMovieContract.Model {
    private DoubanMovieService mDoubanMovieService;

    public HotMovieModel(){
        mDoubanMovieService = NetworkHelper.getInstance().getService(DoubanMovieService.class,DoubanMovieService.HOST);
    }

    @Override
    public Observable<HotMovieBean> getHotMovie() {
        return mDoubanMovieService.getHotMovie().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
