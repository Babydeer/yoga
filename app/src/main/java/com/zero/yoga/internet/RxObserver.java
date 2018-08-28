package com.zero.yoga.internet;

import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.bean.response.BaseResponse;
import com.zero.yoga.login.LoginActivity;
import com.zero.yoga.utils.Config;
import com.zero.yoga.utils.ProgressDialogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zero on 2017/11/30.
 * Describe: function
 */

public class RxObserver<T extends BaseResponse> implements Observer<T> {

    private static final String TAG = "RxObserver";

    private BaseActivity activity;

    public RxObserver(BaseActivity activity) {
        this.activity = activity;
    }

    public RxObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        Logger.t(TAG).d(d.toString());
    }

    @Override
    public void onNext(T t) {
        if (t.getCode() == 401) {
            get401Process();
            return;
        }
        if (t.isSuccess()) {
            _onNext(t);
        } else {
            _onError(t.getMsg());
        }
    }

    private void get401Process() {
        if (activity == null) return;
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        Config.UserInfo.clear();
        activity.finish();

    }

    @Override
    public void onComplete() {
        ProgressDialogUtil.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogUtil.dismiss();
        e.printStackTrace();
        _onError(e.toString());

    }


    public void _onNext(T t) {
    }

    public void _onError(String msg) {
    }
}
