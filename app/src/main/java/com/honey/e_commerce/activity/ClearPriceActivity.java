package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.adapter.ClearPay_Adapter;
import com.honey.e_commerce.aliplay.PayDemoActivity;
import com.honey.e_commerce.javabean.GoodsBean;
import com.honey.e_commerce.javabean.ReceivingData;
import com.honey.e_commerce.util.SharedUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class ClearPriceActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private TextView calcTextView;
    private TextView addressTitleTextView;
    private TextView addressTrueNameTetView;
    private TextView addressPhoneTextView;
    private TextView addressContentTextView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String  obj = (String) msg.obj;
                    Gson gson=new Gson();
                    ReceivingData receivingData = gson.fromJson(obj, ReceivingData.class);
                    List<ReceivingData.DatasBean.AddressListBean> address_list = receivingData.getDatas().getAddress_list();
                    for (int i = 0; i <address_list.size() ; i++) {
                        if (address_list.get(i).getIs_default().equals("1")){
                            addressTrueNameTetView.setText(address_list.get(i).getTrue_name());
                            addressPhoneTextView.setText(address_list.get(i).getMob_phone());
                            addressContentTextView.setText(address_list.get(i).getArea_info()+address_list.get(i).getAddress());
                        }
                    }
                    break;
            }
        }
    };
    private ImageView leftimageView;
    private TextView confirmTextView;
    private List<GoodsBean> goodsBean;

    @Override
    protected void onResume() {
        super.onResume();
        addressData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_price);
        initId();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        goodsBean = (List<GoodsBean>) bundle.getSerializable("goodsBean");
        initData(goodsBean);
        Log.i("act", goodsBean.toString());

    }

    private void addressData() {
        String path="http://169.254.168.158/mobile/index.php?act=member_address&op=address_list";
        SharedUtil instances = SharedUtil.getInstances();
        String key = (String) instances.getValueByKey(ClearPriceActivity.this, "key", "");
        HashMap<String,String> params=new HashMap<>();
        params.put("key",key);
        OkHttp3Utils.doPost(path, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("son",json);
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });

    }

    private void initId() {
        listView = (ListView) findViewById(R.id.buypricelistview);
        calcTextView = (TextView) findViewById(R.id.calcTextView);
        leftimageView = (ImageView) findViewById(R.id.leftImageView);
        addressTitleTextView = (TextView) findViewById(R.id.addressTitleTextView);
        addressTrueNameTetView = (TextView) findViewById(R.id.addressTrueNameTetView);
        addressPhoneTextView = (TextView) findViewById(R.id.addressPhoneTextView);
        addressContentTextView = (TextView) findViewById(R.id.addressContentTextView);
        confirmTextView = (TextView) findViewById(R.id.confirmTextView);
        addressTitleTextView.setOnClickListener(this);
        leftimageView.setOnClickListener(this);
        confirmTextView.setOnClickListener(this);

    }

    private void initData(List<GoodsBean> goodsBean) {
        double sum=0;
        for (int i = 0; i <goodsBean.size() ; i++) {

            double v = goodsBean.get(i).getPrice() * goodsBean.get(i).getCount();
            sum=sum+v;
        }
        calcTextView.setText("实付款"+sum+"");
        ClearPay_Adapter adapter=new ClearPay_Adapter(ClearPriceActivity.this,goodsBean);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //收货地址
            case R.id.addressTitleTextView:
                Intent adressintent=new Intent(ClearPriceActivity.this,DetailedAdressActivity.class);
                startActivity(adressintent);
                break;
            case R.id.leftImageView:
                finish();
                break;
            case R.id.confirmTextView:
                confirmData();
                Intent intent=new Intent(ClearPriceActivity.this, PayDemoActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void confirmData() {
        String url="http://169.254.168.158/mobile/index.php?act=member_buy&op=buy_step1";
        SharedUtil instances = SharedUtil.getInstances();
        String key = (String) instances.getValueByKey(ClearPriceActivity.this, "key", "");
        HashMap<String,String> param=new HashMap<>();
        param.put("key",key);
        String cat_id = null;
        for (int i = 0; i <goodsBean.size() ; i++) {
            String cart_id = goodsBean.get(i).getId();
            int count = goodsBean.get(i).getCount();
            if (i==0){
                cat_id=cart_id+"|"+count;
            }else {
                cat_id+=","+cart_id+"|"+count;
            }
        }
        param.put("cart_id",cat_id);
        param.put("ifcart","1");
        OkHttp3Utils.doPost(url, param, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String zss = response.body().string();
                Log.i("gso",zss);
            }
        });



    }
}
