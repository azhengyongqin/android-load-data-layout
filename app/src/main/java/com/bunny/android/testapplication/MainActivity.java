package com.bunny.android.testapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bunny.android.library.LoadDataLayout;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.widget.ListView lv;
    private LoadDataLayout ldl;
    private Handler mHandler = new Handler();
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private com.cjj.MaterialRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
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
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.finishRefresh();
                    }
                },2000);
            }
        });

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:{
                        if(getScrollY() <= 0){
                            refresh.setEnabled(true);
                        } else {
                            refresh.setEnabled(false);
                        }
                    }
                    default:
                        break;

                }
                return false;
            }
        });

    }

    public int getScrollY() {
        View c = lv.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = lv.getFirstVisiblePosition();
        int top = c.getTop();
        System.out.println("高度:"+(-top + firstVisiblePosition * c.getHeight()));
        return -top + firstVisiblePosition * c.getHeight() ;
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
