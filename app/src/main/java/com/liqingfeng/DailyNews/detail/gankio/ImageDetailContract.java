package com.liqingfeng.DailyNews.detail.gankio;

import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

/**
 * Created by lonlife on 2018/1/13.
 */

public interface ImageDetailContract {
    interface Model extends IBaseModel {
    }

    interface View extends IBaseView {
    }

    abstract class Presenter extends IBasePresenter<Model,View> {
    }

}