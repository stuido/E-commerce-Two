package com.honey.e_commerce.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.fenclass.Classify_First;
import com.honey.e_commerce.fenclass.Classify_first_list_adapter;
import com.honey.e_commerce.fenclass.Fragment_Right;
import com.honey.e_commerce.fenclass.IClassifyPresenter;
import com.honey.e_commerce.fenclass.IClassifyView;

import java.util.List;

public class FragmentTwo extends Fragment implements IClassifyView {
    private IClassifyPresenter iClassifyPresenter;

    private List<Classify_First.DatasBean.ClassListBean> class_list;

    private View view;
    private ListView mFragmentTwoListview;
    private FrameLayout mFragmentTwoFrame;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0x409:
                    //添加适配器
                    final Classify_first_list_adapter adapter=new Classify_first_list_adapter(getContext(),class_list);
                    mFragmentTwoListview.setAdapter(adapter);
                    //添加条目点击事件
                    mFragmentTwoListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //改变右侧的Fragment
                            Classify_First.DatasBean.ClassListBean classListBean = adapter.getItem(i);
                            Log.i("xxx","gc_id    :"+classListBean.getGc_id()+"");
                            //获取二级列表的值
                            classify_2(classListBean.getGc_id());
                        }
                    });
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, null);
        initView(view);
        classify_1();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_two_frame, Fragment_Right.getNewInstance("1")).commit();
        return view;
    }

    @Override
    public void firstSuccess(List<Classify_First.DatasBean.ClassListBean> class_list) {
        this.class_list=class_list;
        handler.sendEmptyMessage(0x409);
    }
    @Override
    public void classify_1() {
        iClassifyPresenter.setClassifyFirst(this);
    }
    @Override
    public void classify_2(String gc_id) {
        //替换Fragment
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_two_frame,Fragment_Right.getNewInstance(gc_id)).commit();
    }

    @Override
    public void SecondSuccess() {

    }

    private void initView(View view) {
        mFragmentTwoListview = (ListView) view.findViewById(R.id.fragment_two_listview);
        mFragmentTwoFrame = (FrameLayout) view.findViewById(R.id.fragment_two_frame);

        iClassifyPresenter=new IClassifyPresenter(this);
    }
}
