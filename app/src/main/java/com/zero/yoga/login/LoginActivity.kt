package com.zero.yoga.login

import android.os.Bundle
import com.zero.yoga.R
import com.zero.yoga.base.BaseActivity
import com.zero.yoga.extentions.info
import org.jetbrains.anko.toast

/**
 * Created by zero on 2018/8/7.
 */
class LoginActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        info("LoginActivity onCreate")
        info { "Longin onCreate" }
        toast("login onCreate")

    }
}