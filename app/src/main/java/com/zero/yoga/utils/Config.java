package com.zero.yoga.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by zero on 2017/11/21.
 * Describe: function
 */

public class Config {

    public static int mCount = 0;

    public static void init(Context context) {
        SpfUtils.setApplication((Application) context.getApplicationContext());
    }

    public static String getCookie() {
        return SpfUtils.getString("cookie");
    }

    public static void setCookie(String cookie) {
        SpfUtils.putString("cookie", cookie);
    }

}
