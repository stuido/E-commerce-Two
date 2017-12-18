package com.honey.e_commerce.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.activity.AnotherActivity;
import com.honey.e_commerce.activity.FindActivity;
import com.honey.e_commerce.adapter.Grid_Home_Adapter;
import com.honey.e_commerce.adapter.HomeRecyclerViewAdapter;
import com.honey.e_commerce.javabean.GridHome;
import com.honey.e_commerce.javabean.HomeRecyclerview;
import com.honey.e_commerce.javabean.HomeViewpager;
import com.honey.e_commerce.util.MyStream;
import com.honey.e_commerce.view.MarqueeTextView;
import com.honey.e_commerce.view.MarqueeTextViewClickListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class FragmentHome extends Fragment implements View.OnClickListener{
    View view;
    private ImageView leftImageView;
    private ImageView rightImageView;
    private EditText titleEditText;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ArrayList<String> url_list;
    private String [] textArrays = new String[]{"孩子不爱吃饭怎么办！","睡前跟孩子说这两句话，孩子受益一生！","孩子打王者荣耀不爱吃饭怎么办！","孩子喝牛奶好，还是和豆浆好！"};
 private String path="http://qiang.mogujie.com/jsonp/actGroupItem/1?callback=jQuery21104587953138117029_1504264031748&groupKey=11q&_=1504264031749";
    List<ImageView> image_list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(++currentItem);
            handler.sendEmptyMessageDelayed(1,3000);
        }
    };
    private Handler handler_data=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String gsonData = (String) msg.obj;
                    String subData = gsonData.substring(41, gsonData.length() - 1);
                    Log.i("====",subData);
                    Gson gson=new Gson();
                    HomeRecyclerview homeRecyclerview = gson.fromJson(subData, HomeRecyclerview.class);
                    List<HomeRecyclerview.DataBean.ItemListBean> itemList = homeRecyclerview.getData().getItemList();
                    HomeRecyclerViewAdapter homerecyclerAdapter=new HomeRecyclerViewAdapter(getActivity(),itemList);
                    recyclerView_home.setAdapter(homerecyclerAdapter);
                    break;
            }
        }
    };
    private GridView gridview_home;
    private MarqueeTextView marqueeTv;
    private RecyclerView recyclerView_home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view = View.inflate(getActivity(), R.layout.fragment_home,null);
        }
        ViewGroup group= (ViewGroup) view.getParent();
        if (group!=null){
            group.removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initGridData();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            //让小圆点与轮播的图片关联
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <url_list.size() ; i++) {
                    if ((position%url_list.size())==i){
                        ((View)image_list.get(i)).setBackgroundResource(R.drawable.dot_normal);
                    }else {
                        ((View)image_list.get(i)).setBackgroundResource(R.drawable.dot_focus);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        marqueeTv.setTextArraysAndClickListener(textArrays, new MarqueeTextViewClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AnotherActivity.class));
            }
        });
        serverData();
        getData();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //把布局管理器给RecyclerView
        recyclerView_home.setLayoutManager(staggeredGridLayoutManager);
        handler.sendEmptyMessageDelayed(1,3000);
    }

    private void initGridData() {
        List<GridHome> grid_Home_list=new ArrayList<>();
        grid_Home_list.add(new GridHome("天猫",R.mipmap.g1));
        grid_Home_list.add(new GridHome("聚划算",R.mipmap.g2));
        grid_Home_list.add(new GridHome("天猫国际",R.mipmap.g3));
        grid_Home_list.add(new GridHome("外卖",R.mipmap.g4));
        grid_Home_list.add(new GridHome("天猫超市",R.mipmap.g5));
        grid_Home_list.add(new GridHome("充值中心",R.mipmap.g6));
        grid_Home_list.add(new GridHome("飞猪旅行",R.mipmap.g7));
        grid_Home_list.add(new GridHome("领金币",R.mipmap.g8));
        grid_Home_list.add(new GridHome("拍卖",R.mipmap.g9));
        grid_Home_list.add(new GridHome("分类",R.mipmap.g10));
        gridAdapter(grid_Home_list);

    }

    private void gridAdapter(List<GridHome> grid_home_list) {
        Grid_Home_Adapter adapter=new Grid_Home_Adapter(getActivity(),grid_home_list);
        gridview_home.setAdapter(adapter);
    }

    private void addPoint(ArrayList<String> url_list) {
        for (int i = 0; i <url_list.size() ; i++) {
            ImageView pointimage=new ImageView(getActivity());
            LinearLayout.LayoutParams parms=new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //为小圆点设置距离
            parms.leftMargin=10;
            parms.rightMargin=10;
            //为小圆点添加宽高
            parms.width=15;
            parms.height=15;
            if (i==0){
                pointimage.setBackgroundResource(R.drawable.dot_normal);
            }else {
                pointimage.setBackgroundResource(R.drawable.dot_focus);

            }
            //为LinearLayout添加图片
            linearLayout.addView(pointimage,parms);
            image_list.add(pointimage);

        }
    }
    private void initData() {
        leftImageView = (ImageView) view.findViewById(R.id.leftImageView);
        rightImageView = (ImageView) view.findViewById(R.id.rightImageView);
        titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_home);
        gridview_home = (GridView) view.findViewById(R.id.gridview_home);
        marqueeTv = (MarqueeTextView) view.findViewById(R.id.marqueeTv);
        recyclerView_home = (RecyclerView) view.findViewById(R.id.recyclerview_home);
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        titleEditText.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //扫一扫
            case R.id.leftImageView:
                Intent in = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(in, 2);
                break;
            case R.id.rightImageView:
                break;
            case R.id.titleEditText:
                Intent intent=new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
              getActivity().overridePendingTransition(R.anim.left_into, R.anim.right_into);
                break;
        }

    }
    private void serverData() {
        String path="http://m.yunifang.com/yunifang/mobile/home";
        MyTask mytask=new MyTask();
        mytask.execute(path);
    }

    public void getData() {
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String shouyeData = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=shouyeData;
                handler_data.sendMessage(message);
            }
        });
    }


    private class MyTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                if (connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    String json = MyStream.getInputStream(inputStream);
                    return json;
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("xxx",s);
                Gson gson = new Gson();
            HomeViewpager homeViewpager = gson.fromJson(s, HomeViewpager.class);
            List<HomeViewpager.DataBean.SubjectsBean> subjects = homeViewpager.getData().getSubjects();
            url_list = new ArrayList<>();
                //添加滑动图片数据
                for (int i = 0; i < subjects.size(); i++) {
                    url_list.add(subjects.get(i).getImage());
                }
                viewPager.setCurrentItem(url_list.size() * 1000);
                MyViewPager myviewpager = new MyViewPager();
                viewPager.setAdapter(myviewpager);
                addPoint(url_list);
        }
    }
    private class MyViewPager extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position=position%url_list.size();
            ImageView image=new ImageView(getActivity());
            Picasso.with(getActivity()).load(url_list.get(position)).placeholder(R.mipmap.ic_launcher).into(image);
            container.addView(image);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            return image;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    @Override
    public void onDestroy() {
        marqueeTv.releaseResources();
        super.onDestroy();

    }

    /**
     * 扫一扫
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
