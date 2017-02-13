package com.dali.www.drrerxmvpdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dali.www.drrerxmvpdemo.R;
import com.dali.www.drrerxmvpdemo.base.BaseActivity;
import com.dali.www.drrerxmvpdemo.base.BaseRVActivity;
import com.dali.www.drrerxmvpdemo.bean.User;
import com.dali.www.drrerxmvpdemo.component.AppComponent;
import com.dali.www.drrerxmvpdemo.component.DaggerMainComponent;
import com.dali.www.drrerxmvpdemo.manager.CacheManager;
import com.dali.www.drrerxmvpdemo.ui.contract.MainContract;
import com.dali.www.drrerxmvpdemo.ui.easyadapter.MainAdapter;
import com.dali.www.drrerxmvpdemo.ui.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import static android.R.attr.type;
import static android.R.id.list;

public class MainActivity extends BaseRVActivity<User.DataBean> implements MainContract.View{

    @Inject
    MainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    protected void configViews() {
        initAdapter(MainAdapter.class, true, true);
        loaddingError();
        onRefresh();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getHot("Kecheng","hotType");
    }

    @Override
    public void showHot(User user) {
        mAdapter.addAll(user.getData());
    }

    @Override
    public void onLoadMore() {
        mPresenter.getHot("Kecheng","hotType");
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {

    }
}
