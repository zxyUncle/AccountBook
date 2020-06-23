package com.zxy.zxymvp.mvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by zxy on 2020/6/9 0009 18:06
 * ******************************************
 * * BaseFragment 支持懒加载
 * ******************************************
 */
open abstract class BaseFragment : Fragment() {
    private var isViewInitiated = false//是否初始化过布局
    private var isDataInitiated = false//是否加载过数据

    protected abstract fun layoutId(): Int //传递布局R.layout.XX
    protected abstract fun initView(view: View, savedInstanceState: Bundle?)//初始化数据
    protected abstract fun loadData()//懒加载

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(activity, layoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewInitiated = true//是否初始化过布局
        prepareFetchData()//加载数据方法
        initView(view, savedInstanceState)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            prepareFetchData()
        }
    }

    /**
     * 赖加装核心部分
     */
    private fun prepareFetchData() {
        if (isViewInitiated && !isDataInitiated) {
            loadData()
            isDataInitiated = true //是否加载过数据
        }
    }

}