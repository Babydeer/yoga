package com.zero.yoga.internet;

import android.content.Context;

import com.yunnex.commonlib.utils.L;
import com.yunnex.commonlib.utils.ProgressDialogUtil;
import com.yunnex.commonlib.utils.ToastUtils;
import com.yunnex.smartcanteenpad.R;
import com.yunnex.smartcanteenpad.bean.response.BaseResponse;
import com.yunnex.smartcanteenpad.repository.DataSourceRepository;
import com.yunnex.smartcanteenpad.utils.Config;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zero on 2017/11/30.
 * Describe: function
 */

public class RxObserver<T extends BaseResponse> implements Observer<T> {

    private static int mCount = 0;

    private Context mContext;

    public RxObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
//        L.getLogger().tag("Zero").i(d);
    }

    @Override
    public void onComplete() {
        ProgressDialogUtil.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        mCount++;
        if (mCount > 5) {
            switchOfflineTip();
        }
        L.getLogger().tag("Zero").i(mCount + " " + e);
        ProgressDialogUtil.dismiss();
        e.printStackTrace();
        _onError(e.toString());

    }

    private void switchOfflineTip() {
        if (Config.isAndServ()) {
            ToastUtils.showLongToast(R.string.network_not_connect_tip_1);
        } else {
            ToastUtils.showLongToast(R.string.network_not_connect_tip_2);
        }
    }

    @Override
    public void onNext(T t) {
        L.getLogger().tag("Zero").e("t.getCode() : " + t.getCode());
        if (t.getCode() == BaseResponse.CODE_SUCCESS) {
            mCount = 0;
            _onNext(t);
        } else if (t.getCode() == DataSourceRepository.GET_FROM_DB_SUCCESS) {
            _onNext(t);
        } else if (t.getCode() == BaseResponse.CODE_NO_PERMISSION) {
            //跳转到登录页面
        } else {//除了返回200之外，其他的都当成错误统一处理
            _onError(t.getMessage());
        }
    }

    public void _onNext(T t) {
    }

    public void _onError(String msg) {
    }
}
