package com.zero.yoga.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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
import com.zero.yoga.stadiums.DateBean;
import com.zero.yoga.stadiums.DateFactory;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class UserCenterActivity extends BaseActivity {

    private static final String TAG = "UserCenter";


    private ImageView ivBack;
    private TextView tvTitle;

    private ImageView ivPortrait;

    private EditText etName;
    private EditText etSex;
    private EditText etPhoneNo;

    private Button btnLogout;


    public static void jump2UserCenterActivity(final Context context) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_usercenter);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);

        ivPortrait = findViewById(R.id.ivPortrait);
        etName = findViewById(R.id.etName);
        etSex = findViewById(R.id.etSex);
        etPhoneNo = findViewById(R.id.etPhoneNo);

        btnLogout = findViewById(R.id.btnLogout);

        tvTitle.setText("个人中心");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogout();
            }
        });

    }


    private void doLogout() {

    }


}
