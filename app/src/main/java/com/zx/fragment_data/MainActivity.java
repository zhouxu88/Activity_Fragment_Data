package com.zx.fragment_data;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 1、setArgument和getArgument传递数据
 *  只使用于Fragment创建的时候，从Activity给Frament传递数据
 *
 * 2、在Fragment创建成功后，Activity通知Fragment做某些实现
 *   通过FragmentManager找到Fragment实例，然后Fragment实例调用相应方法
 */
public class MainActivity extends AppCompatActivity implements IReduceListener{

    private TextView resultTv;  //显示Fragment传递的数据
    private FragmentManager fragmentManager;
    private int count; //用于向fragment传递数据的计数器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragmentData();
    }

    //初始化传递到fragment的数据
    private void initFragmentData() {
        fragmentManager = getSupportFragmentManager();
        TestFragment fragment = new TestFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment,fragment,"TestFragment");
        transaction.commit();

        //向Fragment传递的数据
        Bundle bundle = new Bundle();
        bundle.putString("key_data","Activity传递的初始化数据");
        fragment.setArguments(bundle);

        fragment.setIReduceListener(this);



    }

    private void initView() {
        resultTv = (TextView) findViewById(R.id.result_tv);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增加
                count++;
                TestFragment testFragment = (TestFragment) fragmentManager.findFragmentByTag("TestFragment");
                testFragment.update(count); //更新Fragment的数据
            }
        });
    }

    //Fragment回传的数据的回调的方法
    @Override
    public void reduce(int count) {
        //设置回传的数据
        resultTv.setText(String.valueOf(count));
    }
}
