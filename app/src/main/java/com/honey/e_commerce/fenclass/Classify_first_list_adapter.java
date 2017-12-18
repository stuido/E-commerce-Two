package com.honey.e_commerce.fenclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Classify_first_list_adapter extends BaseAdapter {
    private List<Classify_First.DatasBean.ClassListBean> class_list;
    private Context context;

    public Classify_first_list_adapter(Context context,List<Classify_First.DatasBean.ClassListBean> class_list)
    {
        this.context=context;
        this.class_list=class_list;
    }
    @Override
    public int getCount() {
        return class_list.size();
    }
    @Override
    public Classify_First.DatasBean.ClassListBean getItem(int i) {
        return class_list.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Item item;
        if(view==null)
        {
            item=new Item();
            view=View.inflate(context, R.layout.classify_first_item,null);
            item.image= (ImageView) view.findViewById(R.id.classify_first_image);
            item.text= (TextView) view.findViewById(R.id.classify_first_text);
            view.setTag(item);
        }
        else
        {
            item= (Item) view.getTag();
        }
        Classify_First.DatasBean.ClassListBean classListBean=getItem(i);
        item.text.setText(classListBean.getGc_name().substring(0,2));
        if(classListBean.getImage().equals(null)||classListBean.getImage().equals(""))
        {
            item.image.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            Picasso.with(context).load(classListBean.getImage()).into(item.image);
        }
        return view;
    }
    class Item
    {
        ImageView image;
        TextView text;
    }
}
