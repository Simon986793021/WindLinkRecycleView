package com.wind.windlinkrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wind.windlinkrecycleview.listener.ItemClickListener;

import java.util.List;

/**
 * Created by zhangcong on 2017/7/24.
 * 当item有不同的布局时，抽象出来的类
 */

public abstract class RvAdapter <T> extends RecyclerView.Adapter<RvAdapter.RvHolder> {
    private Context context;
    protected List<T> list;
    protected ItemClickListener listener;
    public RvAdapter(Context context,List<T> list,ItemClickListener listener)
    {
        this.context=context;
        this.list=list;
        this.listener=listener;
    }
    public RvAdapter()
    {

    }
    /*
        called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
         an item.
     */
    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType),parent,false);
        return getHolder(view,viewType);
    }

    protected abstract RvHolder getHolder(View view, int viewType);

    protected abstract int getLayoutId(int viewType);
    /*
    展示数据被回调
     */
    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        holder.bindHolder(list.get(position),position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    public  abstract class RvHolder <T> extends RecyclerView.ViewHolder
    {
        protected ItemClickListener clickListener;
        public RvHolder(View itemView ,int type,ItemClickListener listener) {
            super(itemView);
            clickListener=listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, (int) v.getTag());
                }
            });
        }
        public  abstract  void  bindHolder(T t,int position);
    }
}
