package com.honey.e_commerce.fenclass;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class ClassifyModle implements IClassifyModle{
    @Override
    public void classify_1(final IClassifyView iClassifyView) {
        Log.i("xxx","准备请求");
        OkHttp3Utils.doGet("http://169.254.168.158/mobile/index.php?act=goods_class", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Log.i("xxx","请求成功----->"+json);
                Classify_First first=new Gson().fromJson(json,Classify_First.class);

                List<Classify_First.DatasBean.ClassListBean> class_list=first.getDatas().getClass_list();
                //开始添加适配器
                iClassifyView.firstSuccess(class_list);
            }
        });
    }
    @Override
    public void classify_2() {

    }
}
