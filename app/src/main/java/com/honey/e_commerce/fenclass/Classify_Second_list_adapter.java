package com.honey.e_commerce.fenclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honey.e_commerce.R;

import java.util.ArrayList;
import java.util.List;


public class Classify_Second_list_adapter extends BaseAdapter {
    private List<Classify_Second.DatasBean.ClassListBean> class_list;
    private List<List<Classify_Third.DatasBean.ClassListBean>> thirds;//第三级列表
    private Context context;
    public Classify_Second_list_adapter(Context context,List<Classify_Second.DatasBean.ClassListBean> class_list, List<List<Classify_Third.DatasBean.ClassListBean>> thirds) {
        this.context=context;
        this.class_list = class_list;
        this.thirds = thirds;
    }

    @Override
    public int getCount() {
        return thirds.size();
    }
    @Override
    public Classify_Second.DatasBean.ClassListBean getItem(int i) {
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
            view=View.inflate(context, R.layout.classify_two,null);
            item.title= (TextView) view.findViewById(R.id.classify_second_title);
            item.layout= (ExpandableGridView) view.findViewById(R.id.classify_second_list);
            view.setTag(item);
        }
        else
        {
            item= (Item) view.getTag();
        }
        Classify_Second.DatasBean.ClassListBean classListBean=getItem(i);
        item.title.setText(classListBean.getGc_name());
        //流式布局
        List<Classify_Third.DatasBean.ClassListBean> classListBeanList=thirds.get(i);
        final List<String> items=new ArrayList<>();
        for(int a=0;a<classListBeanList.size();a++)
        {
            Classify_Third.DatasBean.ClassListBean bean=classListBeanList.get(a);
            items.add(bean.getGc_name());
        }
//        item.layout.setAdapter(new FlowAdapter<String>(items) {
//            @Override
//            public View getView(int i) {
//                View item=View.inflate(context,R.layout.special_item,null);
//                TextView text=item.findViewById(R.id.special_item_txt);
//                text.setText(items.get(i));
//                return item;
//            }
//            @Override
//            public int getCount() {
//                Log.i("xxx","item的Size数量:"+items.size());
//                return items.size();
//            }
//        });
        item.layout.setExpanded(true);
        Classify_third_adapter adapter=new Classify_third_adapter(context,items);
        item.layout.setAdapter(adapter);
        return view;
    }
    class Item
    {
        TextView title;
//        AutoFlowLayout<String> layout;
        ExpandableGridView layout;
    }
}
