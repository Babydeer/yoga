package com.zero.yoga.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zero.yoga.MainActivity;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.bean.response.BaseResponse;
import com.zero.yoga.bean.response.LoginResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zero on 2018/8/13.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private static final int COUNT_TV = 1;

    private TextView tvGetIdentifyCode;
    private Button btnLogin;

    private int mCount = 60;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COUNT_TV:
                    mCount--;
                    if (mCount <= 0) {
                        mCount = 60;
                        tvGetIdentifyCode.setText("获取验证码");
                    } else {
                        mHandler.sendEmptyMessageDelayed(COUNT_TV, 1000);
                        tvGetIdentifyCode.setText("剩余" + mCount + "秒");
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        tvGetIdentifyCode = findViewById(R.id.tvGetIdentifyCode);
        btnLogin = findViewById(R.id.btnLogin);

        tvGetIdentifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCount == 60) {
                    mCount--;
                    doGetIndentifyCode();
                    mHandler.sendEmptyMessageDelayed(COUNT_TV, 1000);
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });


    }

    private void doGetIndentifyCode() {
        Logger.t(TAG).d("获取验证码...");
    }

    private void doLogin() {

        Logger.t("Zero").i("doLogin...");
        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).login("18670301864")
                .compose(new RxHelper<LoginResponse>().io_main(LoginActivity.this, true))
                .subscribe(new RxObserver<LoginResponse>() {
                    @Override
                    public void _onNext(LoginResponse response) {
                        Logger.t("Zero").i(response.toString());

                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });


        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
