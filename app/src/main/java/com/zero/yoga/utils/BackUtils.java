package com.zero.yoga.utils;

import android.view.View;
import android.widget.ImageView;

import com.zero.yoga.base.BaseActivity;

/**
 * Created by zero on 2018/8/17.
 */

public class BackUtils {


    public static void onBackPress(final BaseActivity activity, final ImageView ivBack) {
        if (ivBack == null || activity == null) {
            return;
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity == null) {
                    return;
                }
                activity.onBackPressed();
                activity.finish();
            }
        });
    }

}
