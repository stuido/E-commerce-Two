package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.adapter.MyItemRecyclerViewAdapter;
import com.honey.e_commerce.javabean.SearchDataBean;
import com.honey.e_commerce.view.LoadMoreRecyclerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;
public class Search_FindActivity extends AppCompatActivity {
    private int mColumnCount = 1;
    private int page=0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;

                    Gson gson=new Gson();
                    SearchDataBean searchDataBean = gson.fromJson(obj, SearchDataBean.class);
                    final List<SearchDataBean.DatasBean.GoodsListBean> goods_list = searchDataBean.getDatas().getGoods_list();
                    Log.i("xss",goods_list.get(3).getGoods_name());
                    myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(goods_list,Search_FindActivity.this);
                    recyclerView.setAdapter(myItemRecyclerViewAdapter);
                    //recyclerView.setAutoLoadMoreEnable(true);
                  myItemRecyclerViewAdapter.setOnRrecyclerViewItemClickListener(new MyItemRecyclerViewAdapter.OnRrecyclerViewItemClickListener() {
                      @Override
                      public void onRecyclerViewItemClick(int position) {
                          Toast.makeText(Search_FindActivity.this, "点击"+position, Toast.LENGTH_SHORT).show();
                          Intent details=new Intent(Search_FindActivity.this,DetailsActivity.class);
                          String goods_id = goods_list.get(position).getGoods_id();
                          details.putExtra("goods_id",goods_id);
                          startActivity(details);
                      }
                  });
                    myItemRecyclerViewAdapter.setOnRecyclerViewLongItemClickListener(new MyItemRecyclerViewAdapter.OnRecyclerViewLongItemClickListener() {
                        @Override
                        public void onRecyclerViewLongItemClick(int position) {
                            Toast.makeText(Search_FindActivity.this, "点击"+position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    };
    private LoadMoreRecyclerView recyclerView;
    private List<SearchDataBean.DatasBean.GoodsListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__find);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        Log.i("xxx",search);
        final String path="http://169.254.168.158/mobile/index.php?act=goods&op=goods_list&page=100&key=1&keyword="+search+"";
        initData(path);
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        findViewById(R.id.mode_switch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == mColumnCount) {
                    mColumnCount = 2;
                   // ((TextView) v).setText(R.string.list_mode_stagger);
                    myItemRecyclerViewAdapter.switchMode(true);
                    recyclerView.switchLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
                } else {
                    mColumnCount = 1;
                   // ((TextView) v).setText(R.string.list_mode_list);
                    myItemRecyclerViewAdapter.switchMode(false);
                    recyclerView.switchLayoutManager(new LinearLayoutManager(Search_FindActivity.this));
                }
            }
        });

        if (1 >= mColumnCount) {
            recyclerView.setLayoutManager(new LinearLayoutManager(Search_FindActivity.this));
        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
        }
    }

    private void initData(String path) {
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });
    }


}
