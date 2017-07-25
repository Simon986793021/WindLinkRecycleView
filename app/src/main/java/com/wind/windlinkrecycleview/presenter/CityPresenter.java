package com.wind.windlinkrecycleview.presenter;

import com.wind.windlinkrecycleview.CityContract;

/**
 * Created by zhangcong on 2017/7/24.
 */

public class CityPresenter implements CityContract.Presenter {
    private CityContract.View cityView;
    public CityPresenter(CityContract.View cityview)
    {
        this.cityView=cityview;
        cityView.setPresenter(this);
    }

    @Override
    public void start() {
        showCity();
    }

    private void showCity() {
        cityView.showCity();
    }

}
