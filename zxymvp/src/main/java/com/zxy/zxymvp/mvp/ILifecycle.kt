package com.zxy.zxymvp.mvp

import android.content.res.Configuration
import android.os.Bundle

/**
 * Created by zxy on 2020/6/9 0009 18:13
 * ******************************************
 * *
 * ******************************************
 */
interface ILifecycle {

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}