package com.zxy.accountbook.view

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.zxy.accountbook.utils.visible
import com.zxy.accountbook.R
import com.zxy.accountbook.adapter.DayParentAdapter
import com.zxy.accountbook.presenter.MainPresenter
import com.zxy.accountbook.utils.snackbar.ZToast
import com.zxy.zxymvp.mvp.impl.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zxy on 2020/6/23 0023 11:30
 * ******************************************
 * * 主入口
 * ******************************************
 */
class MainActivity : BaseMvpActivity<MainPresenter>() {
    private var listData = mutableListOf<String>("1", "2", "2", "2", "2")
    private var firstClickBack: Long = 0
    private val mDayAdapter by lazy {
        DayParentAdapter()
    }
    override var layoutId: Int = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        //recyclerview
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mDayAdapter
        mDayAdapter.setNewInstance(listData)
        //监听
        initListener()

    }

    override fun hasToolBar() = false

    private fun initListener() {
        llTitleLeft.setOnClickListener {
            presenter.add()
            Toast.makeText(this, "年", Toast.LENGTH_SHORT).show()
        }
        llTitleRightIv.setOnClickListener {
            presenter.select()
            Toast.makeText(this, "月", Toast.LENGTH_SHORT).show()
        }

        mDayAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    /**
     * 获取到数据的绑定方法
     */
    fun bindContent() {

    }


    override fun onBackPressed() {
        val secondClickBack = System.currentTimeMillis()
        if (secondClickBack - firstClickBack > 1500) {
            ZToast.showS(this, "再按一次退出程序")
            firstClickBack = secondClickBack
            true
        } else {
            finish()
            true
        }
    }
}