package com.liqingfeng.DailyNews.main.gankio.tabs;


import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;



import java.util.List;



import io.reactivex.functions.Consumer;


/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioDayPresenter extends GankioDayContract.Presenter{
    private String year = "2016";
    private String month = "11";
    private String day ="24";

    public GankioDayPresenter() {
//        year = Calendar.getInstance().get(Calendar.YEAR)+"";
//        month = Calendar.getInstance().get(Calendar.MONTH) + 1 +"";
//        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"";
    }

    @Override
    public IBaseModel getModel() {
        return new GankioDayModel();
    }

    @Override
    void loadLastestList() {
        if(mView == null || mModel == null){
            return;
        }
        mModel.getGankIoDay(year,month,day)
                .subscribe(new Consumer<List<GankIoDayItemBean>>() {
                    @Override
                    public void accept(List<GankIoDayItemBean> gankIoDayItemBeans) throws Exception {
                        mView.updateContentList(gankIoDayItemBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void onItemClick(int position, GankIoDayItemBean item) {

    }

    @Override
    void onRefreshItemClick(int position, GankIoDayItemBean item) {

    }

    @Override
    void onGetMoreClick(int position,GankIoDayItemBean item) {
    }
}
