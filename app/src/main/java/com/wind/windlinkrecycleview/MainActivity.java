package com.wind.windlinkrecycleview;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wind.windlinkrecycleview.adapter.ProvinceRvAdapter;
import com.wind.windlinkrecycleview.listener.ItemClickListener;
import com.wind.windlinkrecycleview.presenter.CityPresenter;
import com.wind.windlinkrecycleview.view.CityFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleview;
    private ProvinceRvAdapter adapter;
    private CityFragment cityFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        new   CityPresenter(cityFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initData() {
        String [] province=getResources().getStringArray(R.array.province);
        final List<String> list= Arrays.asList(province);
        adapter=new ProvinceRvAdapter(this, list, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar snackbar=Snackbar.make(recycleview,list.get(position),Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        recycleview.setAdapter(adapter);
        addRightData();
    }

    private void addRightData() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        cityFragment= new CityFragment();
        ft.add(R.id.fl_right,cityFragment);
        ft.commit();

    }

    private void initView() {
        recycleview= (RecyclerView) findViewById(R.id.rv_left);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(manager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.addItemDecoration(new MyDividerItemDecoration(this));
    }
}
