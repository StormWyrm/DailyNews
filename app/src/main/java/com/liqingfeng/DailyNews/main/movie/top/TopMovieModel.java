package com.liqingfeng.DailyNews.main.movie.top;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.network.DoubanMovieService;
import com.liqingfeng.sdk.helper.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMovieModel implements TopMovieContract.Model {
    private DoubanMovieService doubanMovieService;

    public TopMovieModel() {
        this.doubanMovieService = RetrofitHelper.getInstance()
                .getService(DoubanMovieService.class, DoubanMovieService.HOST);
    }

    @Override
    public Observable<HotMovieBean> getMovieTop250(int start, int end) {
        return doubanMovieService.getMovieTop250(start,end)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
