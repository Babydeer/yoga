package com.zero.yoga.internet;

import com.orhanobut.logger.Logger;
import com.zero.yoga.bean.response.BaseResponse;
import com.zero.yoga.utils.ProgressDialogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zero on 2017/11/30.
 * Describe: function
 */

public class RxObserver<T extends BaseResponse> implements Observer<T> {

    private static final String TAG = "RxObserver";

    public RxObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        Logger.t(TAG).d(d.toString());
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
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
