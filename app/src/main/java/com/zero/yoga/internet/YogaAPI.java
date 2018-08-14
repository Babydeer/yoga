package com.zero.yoga.internet;

import com.zero.yoga.bean.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public interface YogaAPI {

    @POST("auth/sendSmsCode")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("phoneNo") String phoneNo);

}
