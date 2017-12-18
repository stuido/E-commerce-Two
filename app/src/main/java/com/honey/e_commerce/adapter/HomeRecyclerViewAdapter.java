package com.honey.e_commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.HomeRecyclerview;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author forever
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<HomeRecyclerview.DataBean.ItemListBean> data;

    public HomeRecyclerViewAdapter(Context context, List<HomeRecyclerview.DataBean.ItemListBean> data) {
        this.context = context;
        this.data = data;
    }

    //创建view设置给ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //第一种方式 自适应不能填充父窗体 从新设置宽度
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, null);
        //得到WindowManager
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //得到屏幕的宽
        int width = wm.getDefaultDisplay().getWidth();
        //获取LayoutParams
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //把屏幕的宽给view
        params.width = width;
        view.setLayoutParams(params);


        //第二种方式
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);


        final MyViewHolder holder = new MyViewHolder(view);
        //使用view的条目点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己获取position
                int position = holder.getLayoutPosition();
                //设置监听
                if (listener != null) {
                    listener.onRecyclerViewItemClick(position);
                }
            }
        });
        //使用view的长按事件
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //自己获取position
                int position = holder.getLayoutPosition();
                //设置监听
                if (listener != null) {
                    longListener.onRecyclerViewLongItemClick(position);
                }
                //true代表消费事件 不继续传递
                return true;
            }
        });
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            ViewGroup.LayoutParams layoutParams = holder.home_recycler_img.getLayoutParams();
            if (position == 0) {
                layoutParams.height = 400;
            } else {
                layoutParams.height = 500;
            }
            holder.home_recycler_img.setLayoutParams(layoutParams);
            holder.home_recycler_title.setText(data.get(position).getTitle());
        holder.home_recycler_price.setText("¥"+data.get(position).getPrice());
            Picasso.with(context).load(data.get(position).getItemImage()).placeholder(R.mipmap.ic_launcher).into(holder.home_recycler_img);
    }
    //数据长度
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView home_recycler_img;
        private TextView home_recycler_title;
        private TextView home_recycler_price;
        public MyViewHolder(View itemView) {
            super(itemView);
            home_recycler_img = (ImageView) itemView.findViewById(R.id.home_recycler_img);
            home_recycler_price = (TextView) itemView.findViewById(R.id.home_recycler_price);
            home_recycler_title = (TextView) itemView.findViewById(R.id.home_recycler_title);
        }
    }

    /**
     * 条目点击
     */
    //声明接口
    private OnRrecyclerViewItemClickListener listener;

    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 条目长按
     */
    //声明接口
    private OnRecyclerViewLongItemClickListener longListener;

    //定义接口 和抽象方法
    public interface OnRecyclerViewLongItemClickListener {
        void onRecyclerViewLongItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRecyclerViewLongItemClickListener(OnRecyclerViewLongItemClickListener longListener) {
        this.longListener = longListener;
    }

}
