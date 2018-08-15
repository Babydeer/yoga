package com.zero.yoga.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.ViewPagerAdapter;
import com.zero.yoga.adapter.DatesAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.mine.MineFragment;
import com.zero.yoga.utils.BottomNavigationViewHelper;
import com.zero.yoga.utils.ItemClickSupport;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class StadiumDetailsActivity extends BaseActivity {

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

    public static void jump2StadiumDetailsActivity(final Context context) {
        Intent intent = new Intent(context, StadiumDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statium_details);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);

        ivIcon = findViewById(R.id.ivIcon);
        tvStadiumName = findViewById(R.id.tvStadiumName);
        tvStadiumTel = findViewById(R.id.tvStadiumTel);
        tvStadiumLoc = findViewById(R.id.tvStadiumLoc);
        tvStadiumDis = findViewById(R.id.tvStadiumDis);

        tl = findViewById(R.id.tl);
        xrecyclerView = findViewById(R.id.xrecycler_view);


        tvTitle.setText("详情");
        initTabLayout();

    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode(TabLayout.MODE_FIXED);
        //指示条的颜色
        tl.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        tl.setSelectedTabIndicatorHeight(0);
        tabs = DateFactory.getWeekDates();
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_date, tl, false);
            TextView tvMonth = view.findViewById(R.id.tvMonth);
            TextView tvWeek = view.findViewById(R.id.tvWeek);
            tvMonth.setText(tabs.get(i).getDateStr());
            tvWeek.setText(tabs.get(i).getWeekStr());
            tab.setCustomView(view);
        }
    }


}
