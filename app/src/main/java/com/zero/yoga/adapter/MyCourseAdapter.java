package com.zero.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.CourseDelResponse;
import com.zero.yoga.bean.response.CourseUpdateResponse;
import com.zero.yoga.bean.response.MerCourseResponce;
import com.zero.yoga.bean.response.MyCourseResponse;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.stadiums.MerchanModel;
import com.zero.yoga.utils.InputUtils;
import com.zero.yoga.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by zero on 2018/8/14.
 */

public class MyCourseAdapter extends TBaseRecyclerAdapter<MyCourseResponse.DataBean.RowsBean> {


    public MyCourseAdapter(Context context) {
        super(context);
    }

    private static String getTime(long start, long end) {
        String startTime = InputUtils.getDataTime("HH:mm", start);
        String endTime = InputUtils.getDataTime("HH:mm", end);
        return startTime + "-" + endTime;
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_stadiumhistory_list, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void BindData(RecyclerView.ViewHolder holder, MyCourseResponse.DataBean.RowsBean data, int position) {
        final ViewHolder vholder = (ViewHolder) holder;
        Glide.with(mContext).load(data.getCourseUrl()).placeholder(R.drawable.list_pic)
                .crossFade()
                .into(vholder.ivIcon);

        vholder.tvCourseName.setText(data.getCourseName());
        vholder.tvCoachName.setText(data.getTeacherName());
        vholder.tvStudentNum.setText(data.getSignNum() + "");
        vholder.tvCourseTime.setText(getTime(data.getStartTime(), data.getEndTime()));
        vholder.btnQian.setTag(data);
        if (data.getSignFlag() == 0) {//未签到
            vholder.btnQian.setVisibility(View.VISIBLE);
            vholder.ivQian.setVisibility(View.GONE);
        } else {
            vholder.btnQian.setVisibility(View.GONE);
            vholder.ivQian.setVisibility(View.VISIBLE);
        }
        vholder.btnQian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyCourseResponse.DataBean.RowsBean data = (MyCourseResponse.DataBean.RowsBean) view.getTag();
                if (!(mContext instanceof BaseActivity)) {
                    return;
                }
                BaseActivity baseActivity = (BaseActivity) mContext;
                if (baseActivity.isFinishing()) {
                    return;
                }
                if (data == null || mContext == null) {
                    return;
                }
                vholder.ivQian.setVisibility(View.VISIBLE);
                HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).userCourseUpdateStatus(data.getId(), "1")
                        .compose(new RxHelper<CourseUpdateResponse>().io_main((BaseActivity) mContext, true))
                        .subscribe(new RxObserver<CourseUpdateResponse>() {
                            @Override
                            public void _onNext(CourseUpdateResponse response) {
                                ToastUtils.showShortToast(response.getMsg());
                                vholder.btnQian.setVisibility(View.GONE);
                            }

                            @Override
                            public void _onError(String msg) {
                                ToastUtils.showShortToast(msg);
                                vholder.ivQian.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcon;
        public TextView tvCourseName;
        public TextView tvCourseTime;
        public TextView tvCoachName;
        public TextView tvStudentNum;
        public ImageView ivQian;

        public Button btnQian;

        ViewHolder(View view) {
            super(view);
            AutoUtils.auto(view);
            ivIcon = view.findViewById(R.id.ivIcon);
            tvCourseName = view.findViewById(R.id.tvCourseName);
            tvCourseTime = view.findViewById(R.id.tvCourseTime);
            tvCoachName = view.findViewById(R.id.tvCoachName);
            tvStudentNum = view.findViewById(R.id.tvStudentNum);
            ivQian = view.findViewById(R.id.ivQian);
            btnQian = view.findViewById(R.id.btnQian);
        }

    }
}
