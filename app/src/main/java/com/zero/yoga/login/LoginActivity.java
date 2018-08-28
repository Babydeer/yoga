package com.zero.yoga.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zero.yoga.MainActivity;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.bean.response.LoginResponse;
import com.zero.yoga.bean.response.SendSmsResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.utils.Config;
import com.zero.yoga.utils.InputUtils;
import com.zero.yoga.utils.ToastUtils;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zero on 2018/8/13.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginAct";

    private static final int COUNT_TV = 1;

    private TextView tvGetIdentifyCode;
    private Button btnLogin;

    private EditText etPhoneNo;
    private EditText etIdentifyCode;

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
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etIdentifyCode = findViewById(R.id.etIdentifyCode);

        tvGetIdentifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNo = etPhoneNo.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNo) || !InputUtils.isPhone(phoneNo)) {
                    ToastUtils.showShortToast("手机号码格式不对");
                    return;
                }

                if (mCount == 60) {
                    mCount--;
                    doGetIndentifyCode(phoneNo);
                    mHandler.sendEmptyMessageDelayed(COUNT_TV, 1000);
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNo = etPhoneNo.getText().toString().trim();
                final String smsCode = etIdentifyCode.getText().toString().trim();

                //TODO: 调试
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);

                if (TextUtils.isEmpty(phoneNo) || !InputUtils.isPhone(phoneNo)) {
                    ToastUtils.showShortToast("手机号码格式不对");
                    return;
                }
                if (TextUtils.isEmpty(smsCode) || smsCode.length() != 6) {
                    ToastUtils.showShortToast("验证码输入有误");
                    return;
                }
                doLogin(phoneNo, smsCode);
            }
        });


    }

    private void doGetIndentifyCode(final String phoneNo) {
        Logger.t(TAG).d("获取验证码...");
        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).sendSmsCode(phoneNo)
                .compose(new RxHelper<SendSmsResponse>().io_main(LoginActivity.this, true))
                .subscribe(new RxObserver<SendSmsResponse>() {
                    @Override
                    public void _onNext(SendSmsResponse response) {
                        Logger.t(TAG).i(response.toString());
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t(TAG).e(msg);
                        ToastUtils.showShortToast(msg);
                    }
                });
    }

    private void doLogin(final String phoneNo, final String smsCode) {
        Logger.t(TAG).i("doLogin...");

        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).login(phoneNo, smsCode)
                .compose(new RxHelper<LoginResponse>().io_main(LoginActivity.this, true))
                .subscribe(new RxObserver<LoginResponse>() {
                    @Override
                    public void _onNext(LoginResponse response) {
                        Logger.t(TAG).i(response.toString());
                        Config.setToken(response.getData().getToken());
                        Config.UserInfo.setHeaderPicture(response.getData().getHeaderPicture());
                        Config.UserInfo.setGrade(response.getData().getGrade());
                        Config.UserInfo.setNickname(response.getData().getNickname());
                        Config.UserInfo.setPhoneNo(response.getData().getPhoneNo());
                        Config.UserInfo.setUsername(response.getData().getUsername());
                        Config.UserInfo.setLogin(true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t(TAG).e(msg);
                        ToastUtils.showShortToast(msg);
                    }
                });


    }
}
