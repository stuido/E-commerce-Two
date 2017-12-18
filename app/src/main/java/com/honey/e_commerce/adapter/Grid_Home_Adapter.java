package com.honey.e_commerce.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.GridHome;

import java.util.List;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class Grid_Home_Adapter extends BaseAdapter {
    private Context context;
    private List<GridHome> data;

    public Grid_Home_Adapter(Context context, List<GridHome> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
           convertView=View.inflate(context, R.layout.grid_home_item, null);
           viewHolder.grid_home_title= (TextView) convertView.findViewById(R.id.grid_home_title);
            viewHolder.grid_home_image= (ImageView) convertView.findViewById(R.id.grid_home_img);
          convertView.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.grid_home_image.setBackgroundResource(data.get(position).getHomeimage());
        viewHolder.grid_home_title.setText(data.get(position).getHome_title());
        return convertView;
    }
    class ViewHolder{
        TextView grid_home_title;
        ImageView grid_home_image;
    }
}
