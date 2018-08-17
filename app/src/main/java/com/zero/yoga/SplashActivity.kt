package com.zero.yoga

import android.Manifest
import android.os.Bundle
import android.os.Handler
import com.zero.yoga.base.BasePermissionsActivity
import com.zero.yoga.login.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * Created by zero on 2018/8/7.
 */
class SplashActivity : BasePermissionsActivity() {

    private val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (checkPermissions(permissions)) {
            val handler = Handler()
            handler.postDelayed({ jumps() }, 1500)
        } else {
            requestPermission(permissions, BasePermissionsActivity.REQUEST_CODE_PERMISSION)
        }
    }

    fun jumps() {
        startActivity<LoginActivity>()
    }

    override fun permissionSuccess(requestCode: Int) {
        super.permissionSuccess(requestCode)
        if (requestCode == BasePermissionsActivity.REQUEST_CODE_PERMISSION) {
            jumps()
        }

    }

}