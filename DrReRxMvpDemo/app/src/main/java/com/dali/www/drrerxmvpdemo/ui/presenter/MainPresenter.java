package com.dali.www.drrerxmvpdemo.ui.presenter;

import android.util.Log;

import com.dali.www.drrerxmvpdemo.api.MyApi;
import com.dali.www.drrerxmvpdemo.base.RxPresenter;
import com.dali.www.drrerxmvpdemo.bean.User;
import com.dali.www.drrerxmvpdemo.manager.CacheManager;
import com.dali.www.drrerxmvpdemo.ui.contract.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.data;

/**
 * Created by qinkangli on 2017/2/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {
    private MyApi myApi;
    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(MyApi myApi) {
        this.myApi = myApi;
    }


    @Override
    public void getHot(String action, String doi) {
        Subscription rxSubscription = myApi.getHot(action,doi).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onNext(User user) {
                        Log.i("dali","onNext");
                        if (user != null && mView != null) {
                            mView.showHot(user);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        Log.i("dali","onCompleted");
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
