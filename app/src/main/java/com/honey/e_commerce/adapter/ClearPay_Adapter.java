package com.honey.e_commerce.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.GoodsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class ClearPay_Adapter extends BaseAdapter {
    private Context context;
    private List<GoodsBean>  data;

    public ClearPay_Adapter(Context context, List<GoodsBean> data) {
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
            convertView=View.inflate(context, R.layout.clearpay_item,null);
            viewHolder=new ViewHolder();
            viewHolder.pay_image= (ImageView) convertView.findViewById(R.id.pay_image);
            viewHolder.pay_price= (TextView) convertView.findViewById(R.id.pay_price);
            viewHolder.pay_sum= (TextView) convertView.findViewById(R.id.pay_sum);
            viewHolder.pay_title= (TextView) convertView.findViewById(R.id.pay_title);

            convertView.setTag(viewHolder);
        }else {
         viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.pay_sum.setText(data.get(position).getCount()+"");
        viewHolder.pay_title.setText(data.get(position).getName());
        viewHolder.pay_price.setText(data.get(position).getPrice()+"");
        Picasso.with(context).load(data.get(position).getImageLogo()).placeholder(R.mipmap.ic_launcher).into(viewHolder.pay_image);
        return convertView;
    }
  class   ViewHolder{
      TextView pay_title;
      TextView pay_price;
      TextView pay_sum;
      ImageView pay_image;

  }
}
