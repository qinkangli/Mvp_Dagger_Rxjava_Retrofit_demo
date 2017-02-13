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
package com.dali.www.drrerxmvpdemo.module;


import com.dali.www.drrerxmvpdemo.api.MyApi;
import com.dali.www.drrerxmvpdemo.api.support.LoggingInterceptor;
import com.dali.www.drrerxmvpdemo.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected MyApi provideBookService(OkHttpClient okHttpClient) {
        return MyApi.getInstance(okHttpClient);
    }
    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }
}