package com.wind.windlinkrecycleview.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wind.windlinkrecycleview.CityContract;
import com.wind.windlinkrecycleview.R;
import com.wind.windlinkrecycleview.Utils;
import com.wind.windlinkrecycleview.adapter.CityRvAdapter;
import com.wind.windlinkrecycleview.listener.ItemClickListener;
import com.wind.windlinkrecycleview.model.CityBean;
import com.wind.windlinkrecycleview.presenter.CityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcong on 2017/7/24.
 */

public class CityFragment extends Fragment implements CityContract.View{
    private CityContract.Presenter mpresenter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CityRvAdapter adapter;
    private List<CityBean> list=new ArrayList<>();
    @Override
    public void onResume() {
        super.onResume();
        mpresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_city,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_city);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setPresenter(CityContract.Presenter presenter) {
        mpresenter=presenter;
    }

    @Override
    public void showSnackBar() {
      //  Utils.showSnackBar(view,);
    }

    @Override
    public void showCity() {
        Context context=getActivity();
        initData(context.getResources().getStringArray(R.array.province));

        gridLayoutManager=new GridLayoutManager(context,3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return list.get(position).isTitle ? 3 : 1 ;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new CityRvAdapter(context, list, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId())
                {case R.id.root:
                    Utils.showSnackBar(recyclerView,list.get(position).getProvince());//需要传入的是父view
                    break;
                    case R.id.ll_city:
                      Utils.showSnackBar(recyclerView,list.get(position).getCity());
                        break;
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData(final String[] province ) {
        List<String[]> citylist=new ArrayList<>();
        citylist.add(new String[]{"深圳","东莞","广州","韶关"});
        citylist.add(new String[]{"哈尔滨","尚志","五常","海伦"});
        citylist.add(new String[]{"南昌","赣州","宜春","吉安"});
        citylist.add(new String[]{"沈阳","大连","鞍山","丹东"});
        citylist.add(new String[]{"呼和浩特","包头","赤峰","鄂尔多斯"});
        citylist.add(new String[]{"银川","石嘴山","吴忠","中卫"});
        citylist.add(new String[]{"太原","大同","运城","临汾"});
        citylist.add(new String[]{"西安","宝鸡","咸阳","延安"});
        citylist.add(new String[]{"拉萨","日喀则","那曲","巴青"});
        citylist.add(new String[]{"昆明","大理","丽江","普洱"});
        citylist.add(new String[]{"兰州","天水","白银","平凉"});
        citylist.add(new String[]{"南宁","柳州","桂林","钦州"});
        citylist.add(new String[]{"海口","三亚","三沙","琼海"});
            for (int i=0;i<province.length;i++)
            {
                CityBean titleBean=new CityBean();
                titleBean.setProvince(province[i]);
                titleBean.setTitle(true);
                list.add(titleBean);

                for (int j=0;j<citylist.get(i).length;j++)
                {
                    CityBean cityBean=new CityBean();
                    cityBean.setCity(citylist.get(i)[j]);
                    list.add(cityBean);
                }


            }
        Log.i(">>>>>>",list.get(2).city.toString());
        Log.i(">>>>>>",citylist.size()+"");
        Log.i(">>>>>>",list.size()+"");
    }
}
