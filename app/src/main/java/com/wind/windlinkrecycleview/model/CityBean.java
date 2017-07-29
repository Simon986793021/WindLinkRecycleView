package com.wind.windlinkrecycleview.model;

/**
 * Created by zhangcong on 2017/7/24.
 */

public class CityBean {
    public String city;
    public boolean isTitle;//判断是否为省份，来进行加载数据
    public String province;
    public String tag;//一个position，同时将城市与省份绑定
    public void setTitle(boolean title)
    {
        isTitle=title;
    }
    public void setProvince (String province)
    {
        this.province=province;

    }
    public String getProvince()
    {
        return province;
    }
    public boolean isTitle()
    {
        return  isTitle;
    }
    public void setCity(String city)
    {
        this.city=city;
    }
    public String getCity()
    {
        return city;
    }
    public void setTag(String tag)
    {
        this.tag=tag;
    }
    public String getTag()
    {
        return tag;
    }
}
