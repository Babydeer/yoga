package com.zero.yoga.internet.interceptor;


import com.zero.yoga.utils.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zero on 2017/11/30.
 * Describe: function
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
//        builder.addHeader("cookie", Config.getCookie());
        builder.addHeader("X-version", "1.0.0");
        builder.addHeader("X-token", Config.geToken());
        builder.addHeader("X-appOS", "0");
        builder.addHeader("Content-type", "application/json;charset=UTF-8");
        return chain.proceed(builder.build());
    }
}