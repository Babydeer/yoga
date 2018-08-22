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
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;
import com.zero.yoga.base.TBaseRecyclerAdapter;
import com.zero.yoga.bean.response.CourseAddResponse;
import com.zero.yoga.bean.response.CourseDelResponse;
import com.zero.yoga.bean.response.MerCourseResponce;
import com.zero.yoga.internet.HttpUtils;
import com.zero.yoga.internet.RxHelper;
import com.zero.yoga.internet.RxObserver;
import com.zero.yoga.internet.YogaAPI;
import com.zero.yoga.stadiums.MerchanModel;
import com.zero.yoga.stadiums.StadiumDetailsActivity;
import com.zero.yoga.utils.InputUtils;
import com.zero.yoga.utils.ToastUtils;

import java.text.SimpleDateFormat;

/**
 * Created by zero on 2018/8/14.
 */

public class CourseListAdapter extends TBaseRecyclerAdapter<MerCourseResponce.DataBean.RowsBean> {


    public CourseListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_stadiumdetails_list, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    private static String getTime(long start, long end) {
        String startTime = InputUtils.getDataTime("HH:mm", start);
        String endTime = InputUtils.getDataTime("HH:mm", end);
        return startTime + "-" + endTime;
    }


    @Override
    public void BindData(RecyclerView.ViewHolder holder, final MerCourseResponce.DataBean.RowsBean data, int position) {
        final ViewHolder vholder = (ViewHolder) holder;
        Glide.with(mContext).load(data.getCourseUrl()).placeholder(R.drawable.list_pic)
                .crossFade()
                .into(vholder.ivIcon);
        vholder.tvCourseName.setText(data.getCourseName());
        vholder.tvCoachName.setText(data.getTercherName());
        vholder.tvStudentNum.setText(data.getSignNum() + "");
        vholder.tvCourseTime.setText(getTime(data.getStartTime(), data.getEndTime()));
        vholder.btnSubscribe.setTag(data);
        vholder.btnCancel.setTag(data);

        vholder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MerCourseResponce.DataBean.RowsBean data = (MerCourseResponce.DataBean.RowsBean) view.getTag();
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
                HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).userCourseDeleteById(data.getId())
                        .compose(new RxHelper<CourseDelResponse>().io_main((BaseActivity) mContext, true))
                        .subscribe(new RxObserver<CourseDelResponse>() {
                            @Override
                            public void _onNext(CourseDelResponse response) {
                                ToastUtils.showShortToast(response.getMsg());
                            }

                            @Override
                            public void _onError(String msg) {
                                ToastUtils.showShortToast(msg);
                            }
                        });
            }
        });

        vholder.btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MerCourseResponce.DataBean.RowsBean data = (MerCourseResponce.DataBean.RowsBean) view.getTag();
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
                HttpUtils.getOnlineCookieRetrofit().create(YogaAPI.class).userCourseAddOne(data.getCourseId())
                        .compose(new RxHelper<CourseAddResponse>().io_main((BaseActivity) mContext, true))
                        .subscribe(new RxObserver<CourseAddResponse>() {
                            @Override
                            public void _onNext(CourseAddResponse response) {
                                ToastUtils.showShortToast(response.getMsg());
                            }

                            @Override
                            public void _onError(String msg) {
                                ToastUtils.showShortToast(msg);
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

        public Button btnCancel;
        public Button btnSubscribe;

        ViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.ivIcon);
            tvCourseName = view.findViewById(R.id.tvCourseName);
            tvCourseTime = view.findViewById(R.id.tvCourseTime);
            tvCoachName = view.findViewById(R.id.tvCoachName);
            tvStudentNum = view.findViewById(R.id.tvStudentNum);
            btnCancel = view.findViewById(R.id.btnCancel);
            btnSubscribe = view.findViewById(R.id.btnSubscribe);
        }
    }
}
