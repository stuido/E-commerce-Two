package com.honey.e_commerce.fenclass;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.honey.e_commerce.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class Fragment_Right extends Fragment {

    private List<Classify_Second.DatasBean.ClassListBean> class_list;
    private List<List<Classify_Third.DatasBean.ClassListBean>> thirds;//第三级列表

    private List<String> strings;
    private static String gc_id;

    private ListView listView;

    private Classify_Second_list_adapter adapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0x501:
                    adapter=new Classify_Second_list_adapter(getContext(),class_list,thirds);
                    //添加适配器
                    listView.setAdapter(adapter);
                    break;
                case 0x502:
                    //刷新适配器
                    adapter.notifyDataSetChanged();
                    break;
                case 0x603:
                    getClassify_3_data();
                    break;
            }
        }
    };
    public static Fragment_Right getNewInstance(String id)
    {
        gc_id=id;
        Fragment_Right right=new Fragment_Right();
        return right;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_right,container,false);
        initView(view);
        Log.i("xxx","right gc_id    :"+gc_id);
        //进行网络请求
        getClassify_2_data();
        return view;
    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.fragment_right_list);
    }

    private void getClassify_2_data() {
        String url= "http://169.254.168.158/mobile/index.php?act=goods_class&gc_id="+gc_id;
        OkHttp3Utils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Log.i("xxx","第二级列表"+json);
                Classify_Second second=new Gson().fromJson(json,Classify_Second.class);
                //根据第二级列表来实例化第三级列表
                class_list=second.getDatas().getClass_list();
                handler.sendEmptyMessage(0x603);
            }
        });
    }
    int start=0;
    int end=0;
    private void getClassify_3_data()
    {

        strings=new ArrayList<>();
        thirds=new ArrayList<>();

        //获取第三级列表
        for(int i=0;i<class_list.size();i++)
        {
            Classify_Second.DatasBean.ClassListBean classListBean=class_list.get(i);
            strings.add(classListBean.getGc_id());
        }
        end=strings.size()-1;

        final Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //开始请求
                String url = "http://169.254.168.158/mobile/index.php?act=goods_class&gc_id=" + strings.get(start);

                OkHttp3Utils.doGet(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String third_json=response.body().string();
                        Log.i("xxx","third_json:"+third_json);
                        //解析third_json
                        Classify_Third third_class=new Gson().fromJson(third_json,Classify_Third.class);
                        List<Classify_Third.DatasBean.ClassListBean> classListBeen=third_class.getDatas().getClass_list();
                        thirds.add(classListBeen);
                        if(start==0)
                        {
                            handler.sendEmptyMessage(0x501);
                        }
                        else
                        {
                            handler.sendEmptyMessage(0x502);
                        }
                        start++;
                        if(start>end)
                        {
                            //线程终止
                            timer.cancel();
                        }
                    }
                });
            }
        },0,300);
    }
}
