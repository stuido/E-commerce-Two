package com.honey.e_commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.LoginData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttplibrary.utils.OkHttp3Utils;

public class UserRelignActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText relignusername;
    private EditText relignuserpassword;
    private EditText relignpassword;
    private Button relignbutton;
    private EditText relignuseremail;
    private String path="http://169.254.168.158/mobile/index.php?act=login&op=register";
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   String  obj = (String) msg.obj;
                   Gson gson=new Gson();
                   LoginData loginData = gson.fromJson(obj, LoginData.class);
                   int code = loginData.getCode();
                   if (code==200){
                       Intent intent=new Intent();
                       intent.putExtra("username",loginData.getDatas().getUsername());
                       setResult(202,intent);
                       finish();
                   }
                   else {
                       Toast.makeText(UserRelignActivity.this, "注册有误", Toast.LENGTH_SHORT).show();
                   }
                   break;
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_relign);
        initId();

    }
    private void initId() {
        relignusername = (EditText) findViewById(R.id.relign_username);
        relignuserpassword = (EditText) findViewById(R.id.relign_userpassword);
        relignpassword = (EditText) findViewById(R.id.relign_password);
        relignuseremail = (EditText) findViewById(R.id.relign_useremail);
        relignbutton = (Button) findViewById(R.id.relign_button);
        relignbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relign_button:
                String username = relignusername.getText().toString();
                String userpassword = relignuserpassword.getText().toString();
                String password = relignpassword.getText().toString();
                String useremail = relignuseremail.getText().toString();
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                params.put("password",userpassword);
                params.put("password_confirm",password);
                params.put("email",useremail);
                params.put("client","android");
                OkHttp3Utils.doPost(path, params, new Callback() {
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
                        Log.i("hhh",string);
                    }
                });
                break;
        }

    }
}
