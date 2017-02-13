package com.dali.www.drrerxmvpdemo.api;

import com.dali.www.drrerxmvpdemo.base.Constant;
import com.dali.www.drrerxmvpdemo.bean.User;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by qinkangli on 2016/12/2.
 */
public class MyApi {
    public static MyApi instance;
    private MyApiService service;
    public MyApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(MyApiService.class);
    }
    public static MyApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new MyApi(okHttpClient);
        return instance;
    }

    /**
     * 测试
     * @param action
     * @param doi
     * @return
     */
    public Observable<User> getHot(String action,String doi) {
        return service.getHot(action,doi);
    }

}
