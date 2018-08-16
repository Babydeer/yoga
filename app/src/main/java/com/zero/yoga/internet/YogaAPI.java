package com.zero.yoga.internet;

import com.zero.yoga.bean.response.BaseResponse;
import com.zero.yoga.bean.response.LoginResponse;
import com.zero.yoga.bean.response.SendSmsResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public interface YogaAPI {
//    短信获取	/app/auth/sendSmsCode
//            注册接口
//    快捷登录接口	/app/login
//    密码重置	/app/pwd/resetPwd
//    密码修改	/app/pwd/changePwd
//    场馆查询!A1	/app/merchant/selectByPage
//    场馆排课查询!A1
//    授课老师信息查询!A1	/app/terchant/selectById
//    课程信息查询!A1	/app/course/selectById
//    课程预约!A1	/app/userCourse/addOne
//    课程取消!A1	/app/userCourse/deleteById
//    课程签到!A1	/app/userCourse/updateStatus
//    用户信息查询!A1	/app/user/getByUsername
//    用户信息修改!A1	/app/user/updateUserInfo
//    我的课程-已约'!A1	/app/userCourse/myCourse
//    我的课程-历史'!A1	/app/userCourse/hisCourse
//    意见反馈!A1	/app/suggestion/addOne


    @POST("auth/sendSmsCode")
    @FormUrlEncoded
    Observable<SendSmsResponse> sendSmsCode(@Field("phoneNo") String phoneNo);

    @POST("app/login")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("phoneNo") String phoneNo,@Field("smsCode") String smsCode);


    @POST("app/merchant/selectByPage")
    @FormUrlEncoded
    Observable<BaseResponse> merchantSelectByPage(@Field("merchantName") String merchantName,@Field("username") String username,@Field("offset") int offset,@Field("limit") int limit);

    @POST("app/terchant/selectById")
    @FormUrlEncoded
    Observable<BaseResponse> selectById(@Field("phoneNo") String phoneNo);

}
