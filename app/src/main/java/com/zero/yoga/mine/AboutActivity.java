package com.zero.yoga.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.yoga.BuildConfig;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.utils.BackUtils;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zero on 2018/8/13.
 */

public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutAct";


    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvVersion;


    public static void jump2AboutActivity(final Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvVersion = findViewById(R.id.tvVersion);

        tvVersion.setText(BuildConfig.BUILD_NAME);

        tvTitle.setText("关于");
        BackUtils.onBackPress(this, ivBack);
    }

}
