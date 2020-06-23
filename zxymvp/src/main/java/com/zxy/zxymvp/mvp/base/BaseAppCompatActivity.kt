package com.zxy.zxymvp.mvp.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.zxy.zxymvp.R
import kotlinx.android.synthetic.main.titlebar.*
import kotlinx.android.synthetic.main.titlebar.view.*

/**
 * Created by zxy on 2020/6/9 0009 18:00
 * ******************************************
 * *
 * ******************************************
 */
open abstract class BaseAppCompatActivity : AppCompatActivity() {
    abstract var layoutId: Int //初始化布局
    abstract fun initView(savedInstanceState: Bundle?)//初始化数据

    val llTitleLeft: LinearLayout by lazy {//返回按钮
        findViewById<LinearLayout>(R.id.llTitleLeft)
    }
    val tvTitle: TextView by lazy {//标题栏
        findViewById<TextView>(R.id.tvTitle)
    }
    val llTitleRightIv1: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightIv1)
    }
    val llTitleRightIv2: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightIv2)
    }
    val llTitleRightTv: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightTv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT; // 禁用横屏
        ImmersionBar.with(this).keyboardEnable(true)
            .navigationBarWithKitkatEnable(true)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)//解决布局和状态栏重叠
            .statusBarColor(R.color.color_ffffff)
            .init()
//        EventBusUtil.register(this)
        initView(savedInstanceState)//初始化数据
    }

    override fun onStart() {
        super.onStart()
        tvTitle.isSelected = tvTitle.text.length > 9  //标题开启关闭走马灯
    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (hasToolBar()) {
            super.setContentView(R.layout.titlebar)
            LayoutInflater.from(this).inflate(layoutResID, container, true)
        } else {
            super.setContentView(layoutResID)
        }
    }

    private fun hasToolBar() = true

    override fun onDestroy() {
        super.onDestroy()
//        EventBusUtil.unregister(this)
    }
}