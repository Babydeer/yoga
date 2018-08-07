package com.zero.yoga

import android.os.Bundle
import com.zero.yoga.base.BaseActivity
import com.zero.yoga.extentions.info

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        info { "MainActivity onCreate" }
    }
}
