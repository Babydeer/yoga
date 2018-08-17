package com.zero.yoga.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.utils.BackUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class StadiumHistoryActivity extends BaseActivity {

    private static final String TAG = "StadiumDetails";


    private ImageView ivBack;
    private TextView tvSubscribed;
    private TextView tvHistory;


    private TabLayout tl;
    private XRecyclerView xrecyclerView;
    private List<DateBean> tabs;
    private TBaseRecyclerAdapter datesAdapter;


    private boolean isSelectSubscribed = true;

    public static void jump2StadiumHistoryActivity(final Context context) {
        Intent intent = new Intent(context, StadiumHistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stadiumhistory);
        ivBack = findViewById(R.id.ivBack);
        tvSubscribed = findViewById(R.id.tvSubscribed);
        tvHistory = findViewById(R.id.tvHistory);
        BackUtils.onBackPress(this, ivBack);
        tvSubscribed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelectSubscribed = true;
                setTitleTab();
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelectSubscribed = false;
                setTitleTab();
            }
        });

        tl = findViewById(R.id.tl);
        xrecyclerView = findViewById(R.id.xrecycler_view);
        initXRecyclerView();


    }

    private void setTitleTab() {
        if (isSelectSubscribed) {//选择已约
            tvSubscribed.setBackground(getResources().getDrawable(R.drawable.shape_left_black));
            tvSubscribed.setTextColor(getResources().getColor(R.color.c_ffffff));
            tvHistory.setBackground(getResources().getDrawable(R.drawable.shape_right_transparent));
            tvHistory.setTextColor(getResources().getColor(R.color.c_121212));
        } else {
            tvSubscribed.setBackground(getResources().getDrawable(R.drawable.shape_left_transparent));
            tvSubscribed.setTextColor(getResources().getColor(R.color.c_121212));
            tvHistory.setBackground(getResources().getDrawable(R.drawable.shape_right_black));
            tvHistory.setTextColor(getResources().getColor(R.color.c_ffffff));
        }
    }

    private void initXRecyclerView() {

    }

    private void updateXRecyclerView(final int position) {
        if (position < 0 || position >= tabs.size()) {
            return;
        }
        final DateBean dateBean = tabs.get(position);
        Logger.t(TAG).i(dateBean.toString());
    }


}
