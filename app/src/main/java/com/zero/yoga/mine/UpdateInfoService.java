package com.zero.yoga.mine;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.zero.yoga.bean.response.FeedbackResponse;
import com.zero.yoga.bean.response.UpdateUserInfoResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.utils.Config;
import com.zero.yoga.utils.ToastUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zero on 2018/8/21.
 */

public class UpdateInfoService extends IntentService {

    public UpdateInfoService() {
        super("UpdateInfoService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        updateInfo();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateInfo() {
        File file = new File(Config.UserInfo.getPhotoPath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("headerPicture", "headerPicture", requestFile);
        RequestBody username = RequestBody.create(MediaType.parse("multipart/form-data"), Config.UserInfo.getUsername());
        RequestBody nickname = RequestBody.create(MediaType.parse("multipart/form-data"), Config.UserInfo.getNickname());
        RequestBody grade = RequestBody.create(MediaType.parse("multipart/form-data"), Config.UserInfo.getGrade() + "");
        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).updateUserInfo(username, nickname, grade, body)
                .subscribe(new RxObserver<UpdateUserInfoResponse>() {
                    @Override
                    public void _onNext(UpdateUserInfoResponse response) {
                        Logger.t("update").i(response.getMsg());
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t("update").e(msg);
                        Config.UserInfo.setPhotoPath("");
                    }
                });
    }
}
