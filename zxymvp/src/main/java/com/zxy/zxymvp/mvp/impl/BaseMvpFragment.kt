package com.zxy.zxymvp.mvp.impl

import android.os.Bundle
import com.zxy.zxymvp.mvp.base.BasePresenter
import com.zxy.zxymvp.mvp.IMvpView
import com.zxy.zxymvp.mvp.IPresenter
import com.zxy.zxymvp.mvp.base.BaseFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

/**
 * Created by zxy on 2020/6/9 0009 18:13
 * ******************************************
 * * 
 * ******************************************
 */
abstract class BaseMvpFragment<out P : BasePresenter<BaseMvpFragment<P>>> : BaseFragment(),
        IMvpView<P> {

    final override val presenter: P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    private fun createPresenter(): P {

        sequence {
            var thisClass: KClass<*> = this@BaseMvpFragment::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap { clazz -> clazz.arguments }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewStateRestored(savedInstanceState)
    }


}