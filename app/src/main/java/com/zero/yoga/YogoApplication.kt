package com.zero.yoga

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.zero.yoga.utils.Config
import com.zero.yoga.utils.RudenessScreenHelper
import com.zero.yoga.utils.RudenessScreenHelperUtils
import kotlin.properties.Delegates


/**
 * Created by zero on 2018/8/7.
 */
class YogoApplication() : Application() {

    companion object {
        var INSTANCE: YogoApplication by Delegates.notNull()
    }


    override fun onCreate() {
        super.onCreate()
        initStetho()
        initLogger()
        INSTANCE = this
        Config.init(this)
//        RudenessScreenHelperUtils.initApplication(this)
    }

    fun initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())
    }

    fun initLogger() {
        Logger.addLogAdapter(if (BuildConfig.DEBUG) AndroidLogAdapter() else DiskLogAdapter())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}