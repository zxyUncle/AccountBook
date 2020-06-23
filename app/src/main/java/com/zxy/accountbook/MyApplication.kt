package com.zxy.accountbook

import android.app.Application
import org.litepal.LitePal

/**
 * Created by zxy on 2020/6/23 0023 15:32
 * ******************************************
 * *
 * ******************************************
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
    }
}