package com.zero.yoga.mine;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseFragment;
import com.zero.yoga.base.BaseLazyFragment;
import com.zero.yoga.stadiums.StadiumHistoryActivity;
import com.zero.yoga.utils.Config;
import com.zero.yoga.utils.GlideCircleTransform;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.File;

/**
 * Created by zero on 2018/8/13.
 */

public class MineFragment extends BaseFragment {

    private static final String TAG = "Mine";

    private ImageView ivIcon;
    private ImageView ivSex;

    private TextView tvPersonalName;
    private TextView tvPersonalPhoneN0;
    private TextView tvMyCourseList;
    private TextView tvFeedBack;
    private TextView tvAbout;

    private LinearLayout llUserInfo;


    public static MineFragment newInstance() {
//        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
//        fragment.setArguments(args);
        return fragment;
    }


    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, @android.support.annotation.Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        AutoUtils.auto(root);
        ivIcon = root.findViewById(R.id.ivIcon);
        ivSex = root.findViewById(R.id.ivSex);

        tvPersonalName = root.findViewById(R.id.tvPersonalName);
        tvPersonalPhoneN0 = root.findViewById(R.id.tvPersonalPhoneN0);
        tvMyCourseList = root.findViewById(R.id.tvMyCourseList);
        tvFeedBack = root.findViewById(R.id.tvFeedBack);
        tvAbout = root.findViewById(R.id.tvAbout);
        llUserInfo = root.findViewById(R.id.llUserInfo);

        llUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserCenterActivity.jump2UserCenterActivity(getActivity());
            }
        });

        tvMyCourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyCourseList();
            }
        });

        tvFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFeedBack();
            }
        });

        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAbout();
            }
        });
        initData();


        return root;
    }


    public void initData() {
        Logger.t(TAG).i(Config.UserInfo.toStrings());
        if (!TextUtils.isEmpty(Config.UserInfo.getPhotoPath())) {
            Uri uri = Uri.fromFile(new File(Config.UserInfo.getPhotoPath()));
            Glide.with(getActivity()).load(uri).placeholder(R.drawable.personal_ic_pic)
                    .crossFade()
                    .transform(new GlideCircleTransform(getActivity()))
                    .into(ivIcon);
        } else {
            Glide.with(getActivity()).load(Config.UserInfo.getHeaderPicture()).placeholder(R.drawable.personal_ic_pic)
                    .crossFade()
                    .transform(new GlideCircleTransform(getActivity()))
                    .into(ivIcon);
        }


        tvPersonalName.setText(Config.UserInfo.getNickname());
        tvPersonalPhoneN0.setText(Config.UserInfo.getPhoneNo());
        if (Config.UserInfo.getGrade() == 0) {
            ivSex.setImageResource(R.drawable.personal_ic_man);
        } else {
            ivSex.setImageResource(R.drawable.personal_ic_woman);
        }

    }


    private void onMyCourseList() {
        StadiumHistoryActivity.jump2StadiumHistoryActivity(getActivity());
    }

    private void onFeedBack() {
        FeedBackActivity.jump2FeedBackActivity(getActivity());
    }

    private void onAbout() {
        AboutActivity.jump2AboutActivity(getActivity());
    }
}
