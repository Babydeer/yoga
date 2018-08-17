package com.zero.yoga.utils;

import android.view.View;
import android.widget.ImageView;

import com.zero.yoga.base.BaseActivity;

/**
 * Created by zero on 2018/8/17.
 */

public class BackUtils {


    public static void onBackPress(final BaseActivity activity,final ImageView ivBack){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }

}
