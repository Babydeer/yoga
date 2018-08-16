package com.zero.yoga.base

import android.os.Bundle
import com.orhanobut.logger.Logger


/**
 * Created by zero on 2018/8/9.
 */
abstract class BaseLazyFragment :BaseFragment(){
    protected var isViewInitiated: Boolean = false
    protected var isVisibleToUser: Boolean = false
    protected var isDataInitiated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.t("Zero").i("onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.t("Zero").i("onActivityCreated")
        isViewInitiated = true
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        Logger.t("Zero").i("setUserVisibleHint")

        prepareFetchData()
    }

    abstract fun fetchData()

    fun prepareFetchData(): Boolean {
        Logger.t("Zero").i("setUserVisibleHint")
        return prepareFetchData(false)
    }

    fun prepareFetchData(forceUpdate: Boolean): Boolean {
        Logger.t("Zero").i("isVisibleToUser=${isVisibleToUser},isViewInitiated=${isViewInitiated},isDataInitiated=${isDataInitiated},forceUpdate=${forceUpdate}")
        val flag = isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)
        Logger.t("Zero").i("flag=${flag}")
        if (flag) {
            Logger.t("Zero").i("prepareFetchData")
            fetchData()
            isDataInitiated = true
            return true
        }
        return false
    }
}