package com.zero.yoga.base

import android.app.Fragment
import android.os.Bundle
import android.view.View

/**
 * Created by zero on 2018/8/7.
 */
abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}