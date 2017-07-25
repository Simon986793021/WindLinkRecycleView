package com.wind.windlinkrecycleview;

/**
 * Created by zhangcong on 2017/7/24.
 */

public interface CityContract {
    interface View
    {
        void setPresenter(Presenter presenter);
        void showSnackBar();
        void showCity();
    }
    interface Presenter {
        void start();
    }
}
