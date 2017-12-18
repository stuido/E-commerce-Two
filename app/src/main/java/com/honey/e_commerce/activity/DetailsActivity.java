package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.adapter.DetailsAdapter;
import com.honey.e_commerce.javabean.DetailsData;
import com.honey.e_commerce.util.SharedUtil;
import com.honey.e_commerce.view.CartPopupWindow;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView joinCartTextView;
    private RecyclerView xiangqinglistview1;
    private TextView bodyTextView;
    private WebView webView;
    private TextView gdetail_price;
    private ImageView gdetail_image;
    private Button jia;
    private Button jian;
    private TextView textjiajian;
    private Button jiarugouwuche;
    private Button lijigoumai;
    private TextView priceTextView;
    private TextView pricePromotionTextView;
    private TextView jingleTextView;
    private TextView nameTextView;
    private ImageView goodsViewPager;
    String url="http://169.254.168.158/mobile/index.php?act=member_cart&op=cart_add";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String  obj = (String) msg.obj;
                    Gson gson=new Gson();
                    detailsData = gson.fromJson(obj, DetailsData.class);
                    DetailsData.DatasBean datas = detailsData.getDatas();
                    Picasso.with(DetailsActivity.this).load(datas.getGoods_image()).placeholder(R.mipmap.ic_launcher).into(goodsViewPager);
                    nameTextView.setText(datas.getGoods_info().getGoods_name());
                    priceTextView.setText(datas.getGoods_info().getGoods_price());
                    pricePromotionTextView.setText(datas.getGoods_info().getGoods_promotion_price());
                    jingleTextView.setText(datas.getGoods_info().getGoods_jingle());
                    final List<DetailsData.DatasBean.GoodsCommendListBean> goods_commend_list = datas.getGoods_commend_list();
                    Log.i("ssz",goods_commend_list.size()+"");
                    DetailsAdapter detailadapter=new DetailsAdapter(DetailsActivity.this,goods_commend_list);
                    xiangqinglistview1.setAdapter(detailadapter);
                    detailadapter.setOnRrecyclerViewItemClickListener(new DetailsAdapter.OnRrecyclerViewItemClickListener() {
                        @Override
                        public void onRecyclerViewItemClick(int position) {
                            String goods_id = goods_commend_list.get(position).getGoods_id();
                            Intent initinit=new Intent(DetailsActivity.this,DetailsActivity.class);
                            initinit.putExtra("goods_id",goods_id);
                            startActivity(initinit);
                        }
                    });
                    break;
            }
        }
    };
    private DetailsData detailsData;
    private String goods_id;
    private TextView buyTextView;
    private ImageView right1ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        Log.i("cnm", goods_id);
        initId();
        String path="http://169.254.168.158/mobile/index.php?act=goods&op=goods_detail&goods_id="+ goods_id +"";
        initData(path);
    }

    private void initId() {
        goodsViewPager = (ImageView) findViewById(R.id.goodsViewPager);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        jingleTextView = (TextView) findViewById(R.id.jingleTextView);
        pricePromotionTextView = (TextView) findViewById(R.id.pricePromotionTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        bodyTextView = (TextView) findViewById(R.id.bodyTextView);
        buyTextView = (TextView) findViewById(R.id.buyTextView);
        xiangqinglistview1 = (RecyclerView) findViewById(R.id.xiangqingListview);
        joinCartTextView = (TextView) findViewById(R.id.joinCartTextView);
        right1ImageView= (ImageView) findViewById(R.id.right1ImageView);
        joinCartTextView.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DetailsActivity.this);
        xiangqinglistview1.setLayoutManager(linearLayoutManager);
        bodyTextView.setOnClickListener(this);
        buyTextView.setOnClickListener(this);
        right1ImageView.setOnClickListener(this);
    }

    private void initData(String path) {
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String xiangqing = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=xiangqing;
                handler.sendMessage(message);
                Log.i("cnm",xiangqing);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.joinCartTextView:
                SharedUtil instances = SharedUtil.getInstances();
                boolean config = (boolean) instances.getValueByKey(DetailsActivity.this, "config", false);
                if (!config){
                    Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
                }else {
                    final CartPopupWindow cartPopupWindow = new CartPopupWindow(DetailsActivity.this, detailsData);
                    cartPopupWindow.setSum(new CartPopupWindow.OnSum() {
                        @Override
                        public void onSum(int sum) {
                            addData(sum);
                            Toast.makeText(DetailsActivity.this, "添加成功,在购物车等亲亲~", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cartPopupWindow.showAtLocation(this.findViewById(R.id.mainRelativeLayout), Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.bodyTextView:
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                //webView.loadUrl();
                break;
                //分享
            case R.id.right1ImageView:

                break;

        }

    }
    private void addData(int sum) {
        Map<String,String> params=new HashMap<>();
        SharedUtil instances = SharedUtil.getInstances();
        String key = (String) instances.getValueByKey(this, "key", "");
        Log.i("xxx",key+""+goods_id);
        params.put("key",key);
        params.put("goods_id",goods_id);
        params.put("quantity",sum+"");
        OkHttp3Utils.doPost(url, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String sss = response.body().string();
                Log.i("sss",sss);

            }
        });


    }
}
