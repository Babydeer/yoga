package com.zero.yoga.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.adapter.CourseListAdapter;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.bean.response.MerCourseResponce;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.utils.BackUtils;
import com.zero.yoga.utils.MapUtils;
import com.zero.yoga.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

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
    private TextView tvStadiumTime;

    private TabLayout tl;
    private RecyclerView xrecyclerView;
    private List<DateBean> tabs;
    private CourseListAdapter courseListAdapter;

    private MerchanModel mMerchanModel;

    public static void jump2StadiumDetailsActivity(final Context context, MerchanModel merchanModel) {
        Intent intent = new Intent(context, StadiumDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("merchanModel", merchanModel);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMerchanModel = getIntent().getExtras().getParcelable("merchanModel");
        if (mMerchanModel == null) {
            finish();
        }

        setContentView(R.layout.activity_statium_details);
        AutoUtils.auto(getWindow().getDecorView());
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        BackUtils.onBackPress(this, ivBack);
        ivIcon = findViewById(R.id.ivIcon);
        tvStadiumName = findViewById(R.id.tvStadiumName);
        tvStadiumTel = findViewById(R.id.tvStadiumTel);
        tvStadiumLoc = findViewById(R.id.tvStadiumLoc);
        tvStadiumTime = findViewById(R.id.tvStadiumTime);

        tvStadiumTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(mMerchanModel.getPhoneNo());
            }
        });

        tvStadiumLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapUtils.goToMap(StadiumDetailsActivity.this, mMerchanModel.getLatitude(), mMerchanModel.getLongitude(), mMerchanModel.getAddress());
            }
        });

        tl = findViewById(R.id.tl);
        xrecyclerView = findViewById(R.id.xrecycler_view);
        initXRecyclerView();

        tvTitle.setText("详情");
        initData();
        initTabLayout();
        updateXRecyclerView(0);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    private void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private void initData() {
        Glide.with(this).load(mMerchanModel.getMerchantPictureURL()).placeholder(R.drawable.list_pic)
                .crossFade()
                .into(ivIcon);
        tvStadiumName.setText(mMerchanModel.getMerchantName());
        tvStadiumTel.setText(mMerchanModel.getPhoneNo());
        tvStadiumLoc.setText(mMerchanModel.getAddress() + " " + mMerchanModel.getDistanceStr());
        tvStadiumTime.setText(mMerchanModel.getBusinessHours());
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

        courseListAdapter = new CourseListAdapter(StadiumDetailsActivity.this);
        xrecyclerView.setLayoutManager(new LinearLayoutManager(StadiumDetailsActivity.this));
        xrecyclerView.addItemDecoration(new com.zero.yoga.view.DividerItemDecoration(StadiumDetailsActivity.this, com.zero.yoga.view.DividerItemDecoration.VERTICAL_LIST));
        xrecyclerView.setAdapter(courseListAdapter);

    }

    private void updateXRecyclerView(final int position) {
        if (position < 0 || position >= tabs.size()) {
            return;
        }
        final DateBean dateBean = tabs.get(position);
        Logger.t(TAG).i(dateBean.toString());

        HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).merCourseSelectByDate(mMerchanModel.getId(), dateBean.getDateformat())
                .compose(new RxHelper<MerCourseResponce>().io_main(StadiumDetailsActivity.this, true))
                .subscribe(new RxObserver<MerCourseResponce>() {
                    @Override
                    public void _onNext(MerCourseResponce response) {
                        if (xrecyclerView == null || response == null || response.getData() == null || courseListAdapter == null) {
                            return;
                        }
                        courseListAdapter.setDataList(response.getData().getRows());
                    }

                    @Override
                    public void _onError(String msg) {
                        Logger.t(TAG).e(msg);
                        ToastUtils.showShortToast(msg);
                    }
                });


    }


}
