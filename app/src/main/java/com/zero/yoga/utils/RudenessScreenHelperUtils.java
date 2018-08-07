package com.zero.yoga.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by San on 2018/6/7.
 */

public class RudenessScreenHelperUtils {
   /**
    *  designWidth 设计稿宽度
    *  720*1080
    *  */
    private static final int designWidth = 360;

    public static void initApplication(Application context) {
        new RudenessScreenHelper(context, designWidth).activate();
    }
    /**
     * 若存在webview导致适配失效的问题
     * 可以先继承WebView并重写setOverScrollMode(int mode)方法，在方法中调用super之后调用一遍RudenessScreenHelper.resetDensity(getContext(), designWidth)规避
     * 若存在dialog中适配失效的问题
     * 可以在dialog的oncreate中调用一遍RudenessScreenHelper.resetDensity(getContext(), designWidth)规避
     * 旋转屏幕之后适配失效
     * 可以在onConfigurationChanged中调用RudenessScreenHelper.resetDensity(getContext(), designWidth)规避
     * 特定国产机型ROM中偶先fragment失效
     * 可以在fragment的onCreateView中调用RudenessScreenHelper.resetDensity(getContext(), designWidth)规避
     * */
    public static void initView(Context context) {
        RudenessScreenHelper.resetDensity(context, designWidth);
    }
}
