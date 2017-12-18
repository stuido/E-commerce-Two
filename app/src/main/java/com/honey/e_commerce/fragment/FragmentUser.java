package com.honey.e_commerce.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.activity.TuiChuActivity;
import com.honey.e_commerce.activity.UserLoginActivity;
import com.honey.e_commerce.common.API;
import com.honey.e_commerce.gaode.GaoDeiActivity;
import com.honey.e_commerce.util.SharedUtil;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class FragmentUser extends Fragment implements View.OnClickListener{
    View view;
    private ImageView headImageView;
    private TextView usernameTextView;
    private TextView settingTextView;
    //头像
    private static final int CHOOSE_PICTURE=0;
    private static final int TAKE_PICTURE=1;
    private static final int CROP_SMALL_PICTURE=2;
    private static Uri tempUri;
    private String username;
    private SharedPreferences sp;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sp = API.sp;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view = View.inflate(getActivity(), R.layout.fragment_user,null);}
        ViewGroup group= (ViewGroup) view.getParent();
        if (group!=null){
            group.removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initId();
        SharedUtil instances = SharedUtil.getInstances();
        final boolean config = (boolean) instances.getValueByKey(getActivity(), "config", false);
        String username = (String) instances.getValueByKey(getActivity(), "username", "");
        if (config){
            usernameTextView.setText(username);
        }
        usernameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (config){
                    Intent intentzx=new Intent(getActivity(), TuiChuActivity.class);
                    startActivityForResult(intentzx,222);
                }
                else {
                    Intent textintent=new Intent(getActivity(), UserLoginActivity.class);
                    startActivity(textintent);
                }
            }
        });

    }

    private void initId() {
        headImageView = (ImageView) view.findViewById(R.id.headImageView);
        usernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
        settingTextView = (TextView) view.findViewById(R.id.settingTextView);
        headImageView.setOnClickListener(this);
        usernameTextView.setOnClickListener(this);
        settingTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //上传头像
            case R.id.headImageView:
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("设置头像");
                String[] items={"选择本地照片","拍照"};
                builder.setNegativeButton("取消",null);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case CHOOSE_PICTURE://选择本地图片
                                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);;
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                                startActivityForResult(intent,CHOOSE_PICTURE);
                                break;
                            case TAKE_PICTURE://拍照
                                Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                tempUri= Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                                intent1.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                                startActivityForResult(intent1,TAKE_PICTURE);
                                break;
                        }
                    }
                });
                builder.create().show();
                break;
            case R.id.usernameTextView:
                Intent imageintent=new Intent(getActivity(), UserLoginActivity.class);
                startActivity(imageintent);
                break;
            case R.id.settingTextView:
                Intent intent=new Intent(getActivity(), GaoDeiActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                startPhotoZoom(data.getData());
                break;
            case CROP_SMALL_PICTURE:
                if(data!=null){
                    setImageToView(data);
                }
                break;
        }
    }
    private void startPhotoZoom(Uri uri) {
        if(uri==null){
            Log.i("tag","The uri is not exist");
        }
        tempUri=uri;
        Intent in=new Intent("com.android.camera.action.CROP");
        in.setDataAndType(uri,"image/*");
        //设置裁剪
        in.putExtra("crop","true");
        in.putExtra("aspectX",1);
        in.putExtra("aspectY",1);
        //宽高
        in.putExtra("outputX",150);
        in.putExtra("outputY",150);
        in.putExtra("return-data",true);
        startActivityForResult(in,CROP_SMALL_PICTURE);
    }
    private void setImageToView(Intent data) {
        Bundle extras=data.getExtras();
        if(extras!=null){
            Bitmap photo=extras.getParcelable("data");
            headImageView.setImageBitmap(photo);
            saveImage(photo);
            File file=new File(getActivity().getCacheDir()+"/aa.jpg");
            OkHttpClient ck=new OkHttpClient();
            MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/*"),file));
            Request request=new Request.Builder().url(API.PHOTO_API).post(builder.build()).build();
            ck.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(getActivity(), "上传头像失败", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        JSONObject json=new JSONObject(response.body().string());
                        final String msg = json.optString("msg");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "上传头像成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    private void saveImage(Bitmap photo) {
        File file=new File(getActivity().getCacheDir()+"/aa.jpg");
        try {
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
