package com.zxy.accountbook.view

import android.os.Bundle
import com.zxy.accountbook.R
import com.zxy.accountbook.presenter.AddPresenter
import com.zxy.zxymvp.mvp.impl.BaseMvpActivity

/**
 * Created by zxy on 2020/6/24 0024 11:38
 * ******************************************
 * * 增加消费
 * ******************************************
 */
class AddActivity : BaseMvpActivity<AddPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    override var layoutId: Int = R.layout.activity_add

    override fun initView(savedInstanceState: Bundle?) {

    }
}