package com.zero.yoga.internet;

import com.zero.yoga.bean.response.BaseResponse;
import com.zero.yoga.bean.response.CourseAddResponse;
import com.zero.yoga.bean.response.CourseDelResponse;
import com.zero.yoga.bean.response.CourseUpdateResponse;
import com.zero.yoga.bean.response.FeedbackResponse;
import com.zero.yoga.bean.response.HistoryCourseResponse;
import com.zero.yoga.bean.response.LoginResponse;
import com.zero.yoga.bean.response.LogoutResponse;
import com.zero.yoga.bean.response.MerCourseResponce;
import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.bean.response.MyCourseResponse;
import com.zero.yoga.bean.response.SendSmsResponse;
import com.zero.yoga.bean.response.UpdateUserInfoResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public interface YogaAPI {
//    短信获取	/app/auth/sendSmsCode
//            注册接口
//    快捷登录接口	/app/login
//    登出接口	/app/logout
//    密码重置	/app/pwd/resetPwd
//    密码修改	/app/pwd/changePwd
//    场馆查询!A1	/app/merchant/selectByPage
//    场馆排课查询!A1	/app/merCourse/selectByDate
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
    Observable<LoginResponse> login(@Field("phoneNo") String phoneNo, @Field("smsCode") String smsCode);


    @POST("app/merchant/selectByPage")
    @FormUrlEncoded
    Observable<MerchanListResponse> merchantSelectByPage(@Field("merchantName") String merchantName, @Field("username") String username, @Field("offset") int offset, @Field("limit") int limit);


    @POST("app/merCourse/selectByDate")
    @FormUrlEncoded
    Observable<MerCourseResponce> merCourseSelectByDate(@Field("merchantId") long merchantId, @Field("queryDate") String queryDate);


    @POST("app/userCourse/addOne")
    @FormUrlEncoded
    Observable<CourseAddResponse> userCourseAddOne(@Field("merCourId") long merCourId);


    @POST("app/userCourse/deleteById")
    @FormUrlEncoded
    Observable<CourseDelResponse> userCourseDeleteById(@Field("id") long id);

    @POST("app/userCourse/updateStatus")
    @FormUrlEncoded
    Observable<CourseUpdateResponse> userCourseUpdateStatus(@Field("id") long id, @Field("status") String status);


    @POST("app/suggestion/addOne")
    @FormUrlEncoded
    Observable<FeedbackResponse> suggestionAddOne(@Field("feedback") String feedback);

    @POST("app/userCourse/myCourse")
    @FormUrlEncoded
    Observable<MyCourseResponse> userCourseMyCourse(@Field("offset") int offset, @Field("limit") int limit);

    @POST("app/userCourse/hisCourse")
    @FormUrlEncoded
    Observable<HistoryCourseResponse> userCourseHisCourse(@Field("offset") int offset, @Field("limit") int limit);

    @POST("app/logout")
    Observable<LogoutResponse> logout();

//    username	varchar(20)	登录名/账号
//    nickname	varchar(60)	昵称
//    grade	varchar(4)	性别
//    headerPicture 		头像

    @Multipart
    @POST("app/user/updateUserInfo")
    Observable<UpdateUserInfoResponse> updateUserInfo(@Part("username") RequestBody username, @Part("nickname") RequestBody nickname, @Part("grade") RequestBody grade, @Part MultipartBody.Part file);


//    @Multipart
//    @POST("messages/applyFeed")
//    Observable<ApplyFeed> applyFeed(@Part("user_id") RequestBody user_id,
//                                    @Part("df_type") RequestBody df_type,
//                                    @Part("df_content") RequestBody df_content,
//                                    @Part MultipartBody.Part... file);
//
//
//    @FormUrlEncoded
//    @POST("user_center/evaluate")
//    Observable<Evaluate> evaluate(@FieldMap Map<String, String> params);
//
//
//    @Multipart
//    @POST("user_center/editPortrait")
//    Observable<EditPortrait> editPortrait(@Part("user_id") RequestBody user_id, @Part MultipartBody.Part file);

}
