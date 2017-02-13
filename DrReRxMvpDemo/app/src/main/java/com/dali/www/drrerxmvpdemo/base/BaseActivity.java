package com.dali.www.drrerxmvpdemo.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.dali.www.drrerxmvpdemo.R;
import com.dali.www.drrerxmvpdemo.app.MyApplication;
import com.dali.www.drrerxmvpdemo.component.AppComponent;
import com.dali.www.drrerxmvpdemo.utils.StatusBarCompat;
import com.dali.www.drrerxmvpdemo.widget.dialog.CustomDialog;

import butterknife.ButterKnife;

/**
 * Created by qinkangli on 2016/12/2.
 */
public abstract class BaseActivity extends AppCompatActivity{

    public Toolbar mCommonToolbar;

    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;
    private CustomDialog dialog;

    protected abstract int getLayoutId();
    protected abstract void initToolBar();
    protected abstract void initDatas();
    protected abstract void configViews();
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (statusBarColor == 0){
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }else if (statusBarColor != -1){
            statusBarView = StatusBarCompat.compat(this,statusBarColor);
        }
        transparent19and20();
        mContext = this;
        ButterKnife.bind(this);
        //这一行为Dragger配置
        setupActivityComponent(MyApplication.getsInstance().getAppComponent());
        mCommonToolbar = ButterKnife.findById(this,R.id.common_toolbar);
        if (mCommonToolbar != null){
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        initDatas();
        configViews();


    }


    protected void transparent19and20(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        dissmissDialog();
    }



    /**
     * 隐藏指定的view
     * @param views
     */
    protected void gone(final View...views){
        if (views != null && views.length > 0){
            for (View view : views){
                if (view != null){
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示指定的view
     * @param views
     */
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view){
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * 初始化对话框
     * @return
     */
    public CustomDialog getDialog(){
        if (dialog == null){
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    /**
     * 展示对话框
     */
    public void showDialog(){
        getDialog().show();
    }

    /**
     * 隐藏对话框
     */
    public void dissmissDialog(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar(){
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null){
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if(statusBarView != null){
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }



}
