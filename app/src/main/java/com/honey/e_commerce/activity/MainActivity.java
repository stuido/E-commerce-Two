package com.honey.e_commerce.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.honey.e_commerce.R;

public class MainActivity extends AppCompatActivity {

    private int time = 3;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //time--;
                    //timerView.setText("还有"+ time+"秒跳转");
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化一个sp对象
        SharedPreferences sp = getSharedPreferences("USER",MODE_PRIVATE);
        //得到一个编辑器
        SharedPreferences.Editor edit = sp.edit();
        //得到一个bool值
        boolean first = sp.getBoolean("first", true);
        if (first){
            //如果是第一次就给他put一个值 改变状态为false
            edit.putBoolean("first",false);
            edit.commit();
            initview();//初始化数据
            handler.sendEmptyMessageDelayed(0,3000);
        }else {
            //直接跳转
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }

    private void initview() {
        //实例化一个MyCount：
        MyCount myCount = new MyCount(3000,1000);
        // 开始倒计时：
        myCount.start();

    }
    //创建一个类继承CountDownTimer

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {

        }
        @Override
        public void onTick(long millisUntilFinished) {

        }
    }
}
