package com.honey.e_commerce.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 小傻瓜 on 2017/9/30.
 */

public class API {
    //头像接口
    public  static  final String PHOTO_API="http://120.27.23.105/file/upload";

    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    public static String username;
    public static String uid;
    public static String nickname;
    public static String icon;
    public static void init(Context context){
        sp=context.getSharedPreferences("gss", Context.MODE_PRIVATE);
        editor=sp.edit();
//        username=sp.getString("username","未登录");
//        username=sp.getString("nickname","未登录");
//        username=sp.getString("uid","未登录");
//        username=sp.getString("icon","");
    }
}
