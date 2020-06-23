package com.zxy.zxymvp.mvp.base

import android.content.res.Configuration
import android.os.Bundle
import com.zxy.zxymvp.mvp.IMvpView
import com.zxy.zxymvp.mvp.IPresenter

/**
 * Created by zxy on 2020/6/9 0009 18:13
 * ******************************************
 * *
 * ******************************************
 */
abstract class BasePresenter<out V: IMvpView<BasePresenter<V>>>: IPresenter<V> {

    override lateinit var view:@UnsafeVariance V

    override fun onCreate(savedInstanceState: Bundle?) = Unit
    override fun onSaveInstanceState(outState: Bundle) = Unit
    override fun onViewStateRestored(savedInstanceState: Bundle?) = Unit
    override fun onConfigurationChanged(newConfig: Configuration) = Unit
    override fun onStart() = Unit
    override fun onResume() = Unit
    override fun onPause() = Unit
    override fun onStop() = Unit
    override fun onDestroy() = Unit

}