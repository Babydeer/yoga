package com.zero.yoga.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.zero.yoga.R;
import com.zero.yoga.base.BaseActivity;

/**
 * Created by zero on 2018/8/28.
 */

public class DialogUtils {

    public static void showNormalDialog(final BaseActivity activity, final int iconRes, final String title, final String message, final String posvText, final String negaText, final DialogInterface.OnClickListener onPosiListener, final DialogInterface.OnClickListener onNegaListener) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(activity);
        normalDialog.setIcon(iconRes);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton(posvText, onPosiListener);
        normalDialog.setNegativeButton(negaText, onNegaListener);
        // 显示
        normalDialog.show();
    }


    public static void showListDialog(final BaseActivity activity, final String[] items, final String message, final DialogInterface.OnClickListener onListener) {
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(activity);
        listDialog.setTitle(message);
        listDialog.setItems(items, onListener);
        listDialog.show();
    }

}
