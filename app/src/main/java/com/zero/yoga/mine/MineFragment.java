package com.zero.yoga.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zero.yoga.R;
import com.zero.yoga.base.BaseLazyFragment;

/**
 * Created by zero on 2018/8/13.
 */

public class MineFragment extends BaseLazyFragment {

    private static final String TAG = "Mine";

    private ImageView ivIcon;
    private ImageView ivSex;

    private TextView tvPersonalName;
    private TextView tvPersonalPhoneN0;
    private TextView tvMyCourseList;
    private TextView tvFeedBack;
    private TextView tvAbout;


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

        ivIcon = root.findViewById(R.id.ivIcon);
        ivSex = root.findViewById(R.id.ivSex);

        tvPersonalName = root.findViewById(R.id.tvPersonalName);
        tvPersonalPhoneN0 = root.findViewById(R.id.tvPersonalPhoneN0);
        tvMyCourseList = root.findViewById(R.id.tvMyCourseList);
        tvFeedBack = root.findViewById(R.id.tvFeedBack);
        tvAbout = root.findViewById(R.id.tvAbout);

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

        return root;
    }

    @Override
    public void fetchData() {

    }


    private void onMyCourseList() {
        Logger.t(TAG).i("onMyCourseList");
    }

    private void onFeedBack() {
        Logger.t(TAG).i("onFeedBack");
    }

    private void onAbout() {
        Logger.t(TAG).i("onAbout");
    }
}
