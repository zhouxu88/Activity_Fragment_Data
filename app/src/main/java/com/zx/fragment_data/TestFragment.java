package com.zx.fragment_data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private TextView resultTv;
    private IReduceListener listener;
    private int count = 10; //传递给Activity的数据


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        initData();
        return view;
    }

    //初始化Fragment的数据
    private void initData() {
        String data = getArguments().getString("key_data");
        if(!TextUtils.isEmpty(data)){
            resultTv.setText(data); //设置Fragment的数据
            Log.i("tag","data----------->"+data);
        }
    }

    //初始化View
    public void initView(View view) {
        resultTv = (TextView) view.findViewById(R.id.fragment_result_tv);
        view.findViewById(R.id.btn_reduce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减少
                count--;
                //数字减1，通知activity接收数据
                listener.reduce(count);
            }
        });
    }

    //供activity调用的方法，用于更新UI
    public void update(int number){
        resultTv.setText(String.valueOf(number));
    }
    //供activity调用的方法
    public void setIReduceListener(IReduceListener listener){
        this.listener = listener;
    }

}
