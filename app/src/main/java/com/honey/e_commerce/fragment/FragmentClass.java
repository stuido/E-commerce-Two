package com.honey.e_commerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.honey.e_commerce.R;
import com.honey.e_commerce.activity.FindActivity;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class FragmentClass extends Fragment implements View.OnClickListener{
    View view;
    private ImageView leftImageView;
    private ImageView rightImageView;
    private EditText titleEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view = View.inflate(getActivity(), R.layout.fragment_class,null);}
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
    }

    private void initData() {
        leftImageView = (ImageView) view.findViewById(R.id.leftImageView);
        rightImageView = (ImageView) view.findViewById(R.id.rightImageView);
        titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        titleEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftImageView:
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
}
