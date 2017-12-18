package com.honey.e_commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.SearchDataBean;
import com.honey.e_commerce.view.LoadMoreRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SearchDataBean.DatasBean.GoodsListBean> mValues;
    private boolean mIsStagger;
    private Context context;

    public MyItemRecyclerViewAdapter(List<SearchDataBean.DatasBean.GoodsListBean> items,Context context) {
        mValues = items;
        this.context = context;
    }

    public void switchMode(boolean mIsStagger) {
        this.mIsStagger = mIsStagger;
    }

    public void setData(List<SearchDataBean.DatasBean.GoodsListBean> datas) {
        mValues = datas;
    }

    public void addDatas(List<SearchDataBean.DatasBean.GoodsListBean> datas) {
        mValues.addAll(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LoadMoreRecyclerView.TYPE_STAGGER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchgrid_item, parent, false);
            final StaggerViewHolder staggerViewHolder=new StaggerViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = staggerViewHolder.getLayoutPosition();
                    if (listener!=null){
                        listener.onRecyclerViewItemClick(position);
                    }

                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = staggerViewHolder.getLayoutPosition();
                    if (listener!=null) {
                        longListener.onRecyclerViewLongItemClick(position);
                    }
                    return true;
                }
            });
            return staggerViewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchlist_item, parent, false);
            final ViewHolder viewh=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewh.getLayoutPosition();
                    if (listener!=null){
                        listener.onRecyclerViewItemClick(position);
                    }

                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = viewh.getLayoutPosition();
                    if (listener!=null) {
                        longListener.onRecyclerViewLongItemClick(position);
                    }
                    return true;
                }
            });
            return viewh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mIsStagger) {
            StaggerViewHolder staggerViewHolder = (StaggerViewHolder) holder;
            staggerViewHolder.search_title.setText(mValues.get(position).getGoods_name());
            staggerViewHolder.search_price.setText("¥"+mValues.get(position).getGoods_price()+"");
            Picasso.with(context).load(mValues.get(position).getGoods_image_url()).placeholder(R.mipmap.ic_launcher).into(staggerViewHolder.search_image);

        } else {
            ViewHolder mHolder = (ViewHolder) holder;
           // mHolder.mItem = mValues.get(position);
            mHolder.search_title.setText(mValues.get(position).getGoods_name());
            mHolder.search_price.setText(mValues.get(position).getGoods_price()+"");
            Picasso.with(context).load(mValues.get(position).getGoods_image_url()).placeholder(R.mipmap.ic_launcher).into(mHolder.search_image);

        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public final TextView search_title;
        public final TextView search_price;
        public final ImageView search_image;

        public StaggerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            search_title = (TextView) mView.findViewById(R.id.search_title);
            search_price = (TextView) mView.findViewById(R.id.search_price);
            search_image= (ImageView) mView.findViewById(R.id.search_image);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView search_title;
        public final TextView search_price;
        public final ImageView search_image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            search_title = (TextView) view.findViewById(R.id.search_title);
            search_price = (TextView) view.findViewById(R.id.search_price);
            search_image= (ImageView) view.findViewById(R.id.search_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + search_title.getText() + "'";
        }
    }

    private OnRrecyclerViewItemClickListener listener;

    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

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
