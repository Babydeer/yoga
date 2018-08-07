package com.zero.yoga.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zero.yoga.extentions.YogaLogger

/**
 * Created by zero on 2018/8/7.
 */
abstract class BaseActivity: AppCompatActivity(),YogaLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}