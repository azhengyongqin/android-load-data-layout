package com.bunny.android.testapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bunny.android.library.LoadDataLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.widget.ListView lv;
    private LoadDataLayout ldl;
    private Handler mHandler = new Handler();
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ldl = (LoadDataLayout) findViewById(R.id.ldl);
        this.lv = (ListView) findViewById(R.id.lv);
        initViews();
        loadData();
    }




    private void initViews() {
        ldl.setBindView(lv);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        //重新加载数据
        ldl.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private int index;//测试用的

    private int run = 0;

    private void loadData() {
        run = 0;
        index++;
        //模拟加载数据
        ldl.showLoading(new LoadDataLayout.SetImgCallBack() {
            @Override
            public void setImg(ImageView img) {
                Glide.with(MainActivity.this)
                        .load(R.mipmap.ajax_loader)
                        .asGif()
                        .into(img);
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index == 1) {// 显示失败界面
                    run = 7;
                    ldl.showError();
                } else if (index == 2) {// 显示空数据界面
                    run = 7;
                    ldl.showEmpty();
                } else {// 显示成功界面
                    run = 7;
                    ldl.showSuccess();
                    for (int i = 0; i < 15; i++) {
                        list.add("测试数据" + i);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }, 3000);
    }
}
