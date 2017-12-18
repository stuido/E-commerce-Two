package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.TuichuData;
import com.honey.e_commerce.util.SharedUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class TuiChuActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tuichu_username;
    private Button tuichu_login;
    String path="http://169.254.168.158/mobile/index.php?act=logout";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String  obj = (String) msg.obj;
                    Gson gson=new Gson();
                    TuichuData tuichuData = gson.fromJson(obj, TuichuData.class);
                    int code = tuichuData.getCode();
                        SharedUtil instances = SharedUtil.getInstances();
                        instances.clearAllData(TuiChuActivity.this);
                        Intent clearintent=new Intent(TuiChuActivity.this,HomeActivity.class);
                        startActivity(clearintent);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_chu);
        initId();
    }

    private void initId() {
        tuichu_username = (TextView) findViewById(R.id.tuichu_username);
        tuichu_login = (Button) findViewById(R.id.tuichu_login);
        tuichu_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tuichu_login:
                SharedUtil instances = SharedUtil.getInstances();
                boolean config = (boolean) instances.getValueByKey(TuiChuActivity.this, "config", false);
                if (config) {
                    String key = (String) instances.getValueByKey(TuiChuActivity.this, "key", "");
                    String username = (String) instances.getValueByKey(TuiChuActivity.this, "username", "");
                   Log.i("cnm",username);
                    tuichu_username.setText(username);
                    final Map<String, String> map = new HashMap<>();
                    map.put("key", key);
                    map.put("username", username);
                    map.put("client", "android");
                    OkHttp3Utils.doPost(path, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Message message=new Message();
                            message.what=0;
                            message.obj=string;
                            handler.sendMessage(message);
                            Log.i("xxx", string);
                        }
                    });
                }
                break;
        }

    }
}
