package com.zero.yoga.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by zero on 2018/8/7.
 */
abstract class BaseFragment : RxFragment() {

    fun switchContent(from: Fragment, to: Fragment, layoutId: Int) {
        val fm: FragmentManager = childFragmentManager
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
        val fm: FragmentManager = childFragmentManager
        //添加渐隐渐现的动画
//        val ft: FragmentTransaction = fm.beginTransaction()
        fm.beginTransaction().add(layoutId, to).commit()
    }

}