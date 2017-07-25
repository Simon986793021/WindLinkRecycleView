package com.wind.windlinkrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wind.windlinkrecycleview.R;
import com.wind.windlinkrecycleview.listener.ItemClickListener;

import java.util.List;

/**
 * Created by zhangcong on 2017/7/24.
 */

public class ProvinceRvAdapter extends RvAdapter<String> {
    public ProvinceRvAdapter(Context context, List list,ItemClickListener listener) {
        super(context, list,listener);
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ProvinceHolder(view,viewType,listener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_province;
    }
    private class ProvinceHolder extends RvHolder<String>
    {
        private TextView textView;
        public ProvinceHolder(View itemView, int type,ItemClickListener listener) {
            super(itemView,type, listener);
            View view=itemView;
            textView= (TextView) view.findViewById(R.id.tv_province);
        }

        @Override
        public void bindHolder(String s, int position) {
            textView.setText(s);
        }
    }
}
