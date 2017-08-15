package com.bunny.android.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 时 间: 2017/8/15
 * 作 者: 郑亮
 * Q  Q : 1023007219
 */

public class LoadDataLayout extends FrameLayout {

    private Context context;

    /**
     * 绑定显示数据的控件
     */
    private View bindView;

    /**
     * 空数据时的布局
     */
    private View emptyView;
    private ImageView emptyImg;
    private TextView emptyTv;
    /**
     * 错误时的布局
     */
    private View errorView;
    private ImageView errorImg;
    private TextView errorTv;
    /**
     * 数据加载时的布局
     */
    private View loadingView;
    private ImageView loadingImg;
    private TextView loadingTv;


    public LoadDataLayout(Context context) {
        this(context, null);
    }

    public LoadDataLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadDataLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居中
        params.gravity = Gravity.CENTER;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoadDataLayout, 0, 0);

        /**
         * 数据为空的布局
         */
        int emptyLayoutId = array.getResourceId(R.styleable.LoadDataLayout_ldl_empty_layout, R.layout.layout_empty);
        emptyView = View.inflate(context, emptyLayoutId, null);
        emptyImg = (ImageView) emptyView.findViewById(R.id.empty_img);
        emptyTv = (TextView) emptyView.findViewById(R.id.empty_tv);
        addView(emptyView, params);

        /**
         * 数据错误时的布局
         */
        int errorLayoutId = array.getResourceId(R.styleable.LoadDataLayout_ldl_error_layout, R.layout.layout_error);
        errorView = View.inflate(context, errorLayoutId, null);
        errorImg = (ImageView) emptyView.findViewById(R.id.error_img);
        errorTv = (TextView) emptyView.findViewById(R.id.error_tv);
        addView(errorView, params);

        /**
         * 数据加载时的布局
         */
        int loadingLayoutId = array.getResourceId(R.styleable.LoadDataLayout_ldl_loading_layout, R.layout.layout_loading);
        loadingView = View.inflate(context, loadingLayoutId, null);
        loadingImg = (ImageView) emptyView.findViewById(R.id.loading_img);
        loadingTv = (TextView) emptyView.findViewById(R.id.loading_tv);
        addView(loadingView, params);

        array.recycle();
        setGoneAll();

    }

    /**
     * 隐藏全部
     */
    private void setGoneAll() {
        emptyView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
    }

    /**
     * 显示空布局
     * @param s 空布局中提示文字
     * @param callBack
     */
    public void showEmpty(String s, SetImgCallBack callBack) {
        if (bindView != null) {
            bindView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(s)) {
            emptyTv.setText(s);
        }

        if (callBack != null) {
            callBack.setImg(emptyImg);
        }

        setGoneAll();
        emptyView.setVisibility(VISIBLE);
    }

    public void showEmpty(String s) {
        showEmpty(s, null);
    }

    public void showEmpty() {
        showEmpty(null);
    }

    /**
     * 显示错误布局
     * @param s
     * @param callBack
     */
    public void showError(String s, SetImgCallBack callBack) {
        if (bindView != null) {
            bindView.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(s)) {
            errorTv.setText(s);
        }

        if (callBack != null) {
            callBack.setImg(errorImg);
        }

        setGoneAll();
        errorView.setVisibility(VISIBLE);
    }

    public void showError(String s) {
        showError(s, null);
    }

    public void showError() {
        showError(null);
    }

    /**
     * 显示加载布局
     * @param s
     * @param callBack
     */
    public void showLoading(String s, SetImgCallBack callBack) {
        if (bindView != null) {
            bindView.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(s)) {
            loadingTv.setText(s);
        }

        if (callBack != null) {
            callBack.setImg(loadingImg);
        }
        setGoneAll();
        loadingView.setVisibility(VISIBLE);
    }

    public void showLoading(String s) {
        showLoading(s, null);
    }

    public void showLoading() {
        showLoading(null);
    }

    public interface SetImgCallBack {
        void setImg(ImageView img);
    }

    /**
     * 设置点击屏幕重新加载数据
     * @param listener
     */
    public void setRefreshListener(OnClickListener listener){
        emptyView.setOnClickListener(listener);
        errorView.setOnClickListener(listener);
    }

    /**
     * 设置显示数据的View
     * @param view
     */
    public void setBindView(View view){
        this.bindView = view;
    }

}
