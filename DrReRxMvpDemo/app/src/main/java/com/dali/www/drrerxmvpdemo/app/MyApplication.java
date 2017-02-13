/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dali.www.drrerxmvpdemo.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.dali.www.drrerxmvpdemo.component.AppComponent;
import com.dali.www.drrerxmvpdemo.component.DaggerAppComponent;
import com.dali.www.drrerxmvpdemo.module.ApiModule;
import com.dali.www.drrerxmvpdemo.module.AppModule;
import com.dali.www.drrerxmvpdemo.utils.AppUtils;
import com.dali.www.drrerxmvpdemo.utils.LogUtils;
import com.dali.www.drrerxmvpdemo.utils.SharedPreferencesUtil;


/**
 * @author yuyh.
 * @date 2016/8/3.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        initCompoent();
        AppUtils.init(this);
        initPrefs();
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }




}
