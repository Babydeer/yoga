package com.zero.yoga.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.ViewPagerAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.mine.MineFragment;
import com.zero.yoga.utils.BackUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zero on 2018/8/13.
 */

public class StadiumHistoryActivity extends BaseActivity {

    private static final String TAG = "StadiumHis";


    private ImageView ivBack;
    private TextView tvSubscribed;
    private TextView tvHistory;

    private ViewPager viewPager;


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
                viewPager.setCurrentItem(0);
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelectSubscribed = false;
                setTitleTab();
                viewPager.setCurrentItem(1);
            }
        });

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    isSelectSubscribed = true;
                } else {
                    isSelectSubscribed = false;
                }
                setTitleTab();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(MyCourseFragment.newInstance());
        adapter.addFragment(HistoryCourseFragment.newInstance());
        viewPager.setAdapter(adapter);
    }
}
