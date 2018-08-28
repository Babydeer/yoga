package com.zero.yoga.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.CourseDelResponse;
import com.zero.yoga.bean.response.FeedbackResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.stadiums.DateBean;
import com.zero.yoga.stadiums.DateFactory;
import com.zero.yoga.utils.BackUtils;
import com.zero.yoga.utils.ToastUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class FeedBackActivity extends BaseActivity {

    private static final String TAG = "StadiumDetails";


    private ImageView ivBack;
    private TextView tvTitle;

    private EditText etFeedBack;
    private Button btnSubmit;


    public static void jump2FeedBackActivity(final Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        BackUtils.onBackPress(this, ivBack);
        etFeedBack = findViewById(R.id.etFeedBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        tvTitle.setText("意见反馈");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String feedback = etFeedBack.getText().toString().trim();
                if (TextUtils.isEmpty(feedback)) {
                    return;
                }

                HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).suggestionAddOne(feedback)
                        .compose(new RxHelper<FeedbackResponse>().io_main(FeedBackActivity.this, true))
                        .subscribe(new RxObserver<FeedbackResponse>(FeedBackActivity.this) {
                            @Override
                            public void _onNext(FeedbackResponse response) {
                                ToastUtils.showShortToast(response.getMsg());
                                finish();
                            }

                            @Override
                            public void _onError(String msg) {
                                ToastUtils.showShortToast(msg);
                            }
                        });

            }
        });

    }


}
