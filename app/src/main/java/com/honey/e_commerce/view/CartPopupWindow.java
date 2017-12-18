package com.honey.e_commerce.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.honey.e_commerce.R;
import com.honey.e_commerce.javabean.DetailsData;
import com.squareup.picasso.Picasso;

public class CartPopupWindow extends PopupWindow {
    private View mView;

    private ImageView imageView;
    private TextView price,goodnum;
    private Button add,jian,ok;

    private int sum=0;

    public CartPopupWindow(Activity context, DetailsData detail) {
        super(context);

        mView = View.inflate(context, R.layout.addcart_pop, null);
        //初始化控件
        initView(mView);

        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(400);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Picasso.with(context).load(detail.getDatas().getGoods_image()).placeholder(R.mipmap.ic_launcher).into(imageView);
        price.setText(detail.getDatas().getGoods_info().getGoods_price()+"");
        createEvent(context);
    }
    private void initView(View view)
    {
        imageView= (ImageView) view.findViewById(R.id.addcart_pop_image);
        price= (TextView) view.findViewById(R.id.addcart_pop_price);
        goodnum= (TextView) view.findViewById(R.id.addcart_pop_goodnum);
        add= (Button) view.findViewById(R.id.addcart_pop_add);
        jian= (Button) view.findViewById(R.id.addcart_pop_jian);
        ok= (Button) view.findViewById(R.id.addcart_pop_ok);

    }
    private void createEvent(final Activity context) {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(goodnum.getText().toString());
                num++;
                goodnum.setText(num+"");

            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(goodnum.getText().toString());
                num--;
                if(num<1)
                {
                    Toast.makeText(context, "数量不能小于1哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                goodnum.setText(num+"");
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加购物车
                int num=Integer.parseInt(goodnum.getText().toString());
                //添加购物车
                onSum.onSum(num);
                dismiss();

            }
        });
    }

    private OnSum onSum;
    public  interface OnSum{
        void onSum(int sum);
    }

    public void setSum(OnSum onSum) {
        this.onSum = onSum;
    }
}
