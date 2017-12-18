package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.LoginData;
import com.honey.e_commerce.util.SharedUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText user_password;
    private Button login_button;
    private ImageView loginleftimg;

    private String path="http://169.254.168.158/mobile/index.php?act=login";
    private TextView userrelign;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String  obj = (String) msg.obj;
                    Gson gson=new Gson();
                    LoginData loginData = gson.fromJson(obj, LoginData.class);
                    SharedUtil instances = SharedUtil.getInstances();
                    int code = loginData.getCode();
                    if (code==200){
                        Toast.makeText(UserLoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        instances.saveDatad(UserLoginActivity.this,"config",true);
                        instances.saveDatad(UserLoginActivity.this,"username",loginData.getDatas().getUsername());
                        instances.saveDatad(UserLoginActivity.this,"userid",loginData.getDatas().getUserid());
                        instances.saveDatad(UserLoginActivity.this,"key",loginData.getDatas().getKey());
                        Intent intentf=new Intent(UserLoginActivity.this,HomeActivity.class);
                        startActivity(intentf);

                    }else {
                        Toast.makeText(UserLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initId();
        loginButton();
    }
    //进行文本的监听事件，改变按钮操作
    private void loginButton() {
        user_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(user_name.getText()) || TextUtils.isEmpty(user_password.getText())) {
                    login_button.setEnabled(Boolean.FALSE);
                    //Toast.makeText(UserLoginActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    login_button.setEnabled(Boolean.TRUE);
                    //Toast.makeText(UserLoginActivity.this, "玩呢！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        user_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(user_name.getText()) || TextUtils.isEmpty(user_password.getText())) {
                    login_button.setEnabled(Boolean.FALSE);
                    //Toast.makeText(UserLoginActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    login_button.setEnabled(Boolean.TRUE);
                   // Toast.makeText(UserLoginActivity.this, "闹呢！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void initId() {
        user_name = (EditText) findViewById(R.id.login_username);
        user_password = (EditText) findViewById(R.id.login_userpassword);
        loginleftimg = (ImageView) findViewById(R.id.loginleftImageView);
        login_button = (Button) findViewById(R.id.login_button);
        userrelign = (TextView) findViewById(R.id.user_relign);
        login_button.setOnClickListener(this);
        loginleftimg.setOnClickListener(this);
        userrelign.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                String username = user_name.getText().toString();
                String userpassword = user_password.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", userpassword);
                    params.put("client", "android");
                    OkHttp3Utils.doPost(path, params, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Message message = new Message();
                            message.what = 0;
                            message.obj = string;
                            handler.sendMessage(message);
                            Log.i("zzz", string);
                        }
                    });

                finish();

                break;
            case R.id.loginleftImageView:
                finish();
                break;
            case R.id.user_relign:
                Intent relignintent=new Intent(UserLoginActivity.this,UserRelignActivity.class);
                startActivityForResult(relignintent, 222);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==202){
            //接受回传的值
            String username=data.getStringExtra("username");
            //设值展示
            user_name.setText(username);
        }

    }
}
