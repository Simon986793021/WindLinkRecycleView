package com.wind.windlinkrecycleview;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wind.windlinkrecycleview.adapter.ProvinceRvAdapter;
import com.wind.windlinkrecycleview.listener.ItemClickListener;
import com.wind.windlinkrecycleview.presenter.CityPresenter;
import com.wind.windlinkrecycleview.view.CityFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CheckListener{
    private RecyclerView recycleview;
    public ProvinceRvAdapter adapter;
    private CityFragment cityFragment;
    private LinearLayoutManager manager;
    private int mposition;
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
                Utils.showSnackBar(recycleview,list.get(position));
                mposition=position;
                startMove(position,true);
                Log.i(">>>>>>","position:"+position);
                moveToCenter(position);
            }
        });
        recycleview.setAdapter(adapter);
        addRightData();

    }
    //将当前选中的item居中
    public   void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        Log.i(">>>>>>>>>",position - manager.findFirstVisibleItemPosition()+"eeeee");
        int itemPosition=position-manager.findFirstVisibleItemPosition();
        /*
        当往上滑动太快，会出现itemPosition为-1的情况。做下判断
         */
        if (0<itemPosition&&itemPosition<manager.getChildCount())
        {
            View childAt = recycleview.getChildAt(position - manager.findFirstVisibleItemPosition());
            Log.i("<<<<<<",position - manager.findFirstVisibleItemPosition()+"");

            int y = (childAt.getTop() - recycleview.getHeight() / 2);
            Log.i("<<<<<<",childAt.getTop()+"ssssss");
            Log.i("<<<<<<", y+"");
            recycleview.smoothScrollBy(0, y);
        }

    }
    private void startMove(int position, boolean isLeft) {
        int counts=0;
        for (int i=0;i<position;i++)
        {
            Log.i("<<<<<<",i+":"+cityFragment.citylist.get(i).length);
            counts+=cityFragment.citylist.get(i).length;
        }
        if (isLeft)
        {
            cityFragment.setCounts(counts+position);//加上title数目
        }
        else {
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
        }
        adapter.setClickPositon(position);//设置点击的位置，改变省份点击背景
    }

    private void addRightData() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        cityFragment= new CityFragment();
        cityFragment.setCheck(this);
        ft.add(R.id.fl_right,cityFragment);
        ft.commit();

    }

    private void initView() {
        recycleview= (RecyclerView) findViewById(R.id.rv_left);
        manager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(manager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.addItemDecoration(new MyDividerItemDecoration(this));
    }

    @Override
    public void check(int position, boolean isScroll) {
        startMove(position,isScroll);
        moveToCenter(position);
    }
}
