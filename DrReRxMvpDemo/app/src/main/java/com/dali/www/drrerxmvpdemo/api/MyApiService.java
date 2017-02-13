package com.dali.www.drrerxmvpdemo.api;

import com.dali.www.drrerxmvpdemo.bean.User;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qinkangli on 2016/12/2.
 */
public interface MyApiService {

    @GET("API/index.php")
    Observable<User> getHot(@Query("action") String action,@Query("do") String doi);
}
