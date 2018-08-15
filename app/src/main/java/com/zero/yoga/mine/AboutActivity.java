package com.zero.yoga.mine;

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
import com.zero.yoga.stadiums.DateBean;
import com.zero.yoga.stadiums.DateFactory;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class AboutActivity extends BaseActivity {

    private static final String TAG = "StadiumDetails";


    private ImageView ivBack;
    private TextView tvTitle;

    private ImageView ivIcon;
    private TextView tvStadiumName;
    private TextView tvStadiumTel;
    private TextView tvStadiumLoc;
    private TextView tvStadiumDis;

    private TabLayout tl;
    private XRecyclerView xrecyclerView;
    private List<DateBean> tabs;
    private TBaseRecyclerAdapter datesAdapter;

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

        ivIcon = findViewById(R.id.ivIcon);
        tvStadiumName = findViewById(R.id.tvStadiumName);
        tvStadiumTel = findViewById(R.id.tvStadiumTel);
        tvStadiumLoc = findViewById(R.id.tvStadiumLoc);
        tvStadiumDis = findViewById(R.id.tvStadiumDis);

        tl = findViewById(R.id.tl);
        xrecyclerView = findViewById(R.id.xrecycler_view);
        initXRecyclerView();

        tvTitle.setText("意见反馈");
        initTabLayout();

    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode(TabLayout.MODE_FIXED);
        //指示条的颜色
        tl.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        tl.setSelectedTabIndicatorHeight(0);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Logger.t(TAG).i("onTabSelected tab: " + tab);
                final int position = (Integer) tab.getTag();
                View view = tab.getCustomView();
                TextView tvMonth = view.findViewById(R.id.tvMonth);
                TextView tvWeek = view.findViewById(R.id.tvWeek);
                view.setBackgroundColor(getResources().getColor(R.color.c_121212));
                tvMonth.setTextColor(getResources().getColor(R.color.c_ffffff));
                tvWeek.setTextColor(getResources().getColor(R.color.c_ffffff));
                updateXRecyclerView(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Logger.t(TAG).i("onTabUnselected tab: " + tab);
                View view = tab.getCustomView();
                TextView tvMonth = view.findViewById(R.id.tvMonth);
                TextView tvWeek = view.findViewById(R.id.tvWeek);
                view.setBackgroundColor(getResources().getColor(R.color.c_f5f5f5));
                tvMonth.setTextColor(getResources().getColor(R.color.c_333333));
                tvWeek.setTextColor(getResources().getColor(R.color.c_333333));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Logger.t(TAG).i("onTabReselected tab: " + tab);
            }
        });
        tabs = DateFactory.getWeekDates();
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tl.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.item_date, tl, false);
            TextView tvMonth = view.findViewById(R.id.tvMonth);
            TextView tvWeek = view.findViewById(R.id.tvWeek);
            Logger.t(TAG).i(tabs.get(i).toString());
            tvMonth.setText(tabs.get(i).getDateStr());
            tvWeek.setText(tabs.get(i).getWeekStr());
            tab.setCustomView(view);
            tab.setTag(i);
            tl.addTab(tab, i);
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
