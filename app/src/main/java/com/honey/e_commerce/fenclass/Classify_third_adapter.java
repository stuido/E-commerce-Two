package com.honey.e_commerce.fenclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honey.e_commerce.R;

import java.util.List;


public class Classify_third_adapter extends BaseAdapter {
    private Context context;
    private List<String> items;
    public Classify_third_adapter(Context context,List<String> items)
    {
        this.context=context;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        item item;
        if(view==null)
        {
            item=new item();
            view=View.inflate(context, R.layout.special_item,null);
            item.textView= (TextView) view.findViewById(R.id.special_item_txt);
            view.setTag(item);
        }
        else
        {
            item = (Classify_third_adapter.item) view.getTag();
        }
        String text=getItem(i);
        item.textView.setText(text);
        return view;
    }
    class item
    {
        TextView textView;
    }
}
