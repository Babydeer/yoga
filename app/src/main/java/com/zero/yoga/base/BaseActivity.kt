package com.zero.yoga.base

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.zero.yoga.YogoApplication


/**
 * Created by zero on 2018/8/7.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    var sNoncompatDesity: Float = 0.toFloat()
    var sNoncompatScaledDesity: Float = 0.toFloat()
    fun setCustomDesity(activity: Activity, application: Application, isWidth: Boolean) {
        val appdisplayMetrics = application.resources.displayMetrics
        if (sNoncompatDesity == 0f) {
            sNoncompatDesity = appdisplayMetrics.density
            sNoncompatScaledDesity = appdisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(configuration: Configuration?) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNoncompatScaledDesity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }
            })
        }
        val targetDesity: Float
        if (isWidth) {
            targetDesity = appdisplayMetrics.widthPixels.toFloat() / 375//375 设计图的宽度dp 根据宽度适配
        } else {
            targetDesity = appdisplayMetrics.heightPixels.toFloat() / 667//667 设计图的高度dp 根据高度适配
        }
        val targetScaleDesity = targetDesity * (sNoncompatScaledDesity / sNoncompatDesity)
        val targetDesityDpi = (160 * targetDesity).toInt()

        appdisplayMetrics.density = targetDesity
        appdisplayMetrics.scaledDensity = targetScaleDesity
        appdisplayMetrics.densityDpi = targetDesityDpi

        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDesity
        activityDisplayMetrics.scaledDensity = targetScaleDesity
        activityDisplayMetrics.densityDpi = targetDesityDpi
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setCustomDesity(this, YogoApplication.INSTANCE, false)
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    fun switchContent(from: Fragment, to: Fragment, layoutId: Int) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
        val ft: FragmentTransaction = fm.beginTransaction()
        if (!to.isAdded) {
            // 先判断是否被add过
            ft.hide(from).add(layoutId, to) // 隐藏当前的fragment，add下一个到Activity中
        } else {
            ft.hide(from).show(to) // 隐藏当前的fragment，显示下一个
        }
        ft.commit()
    }

    fun initFragment(to: Fragment, layoutId: Int) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
//        val ft: FragmentTransaction = fm.beginTransaction()
        fm.beginTransaction().add(layoutId, to).commit()
    }
}