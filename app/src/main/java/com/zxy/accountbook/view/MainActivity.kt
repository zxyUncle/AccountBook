package com.zxy.accountbook.view

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.kd.kotlin.extend.utils.visible
import com.zxy.accountbook.R
import com.zxy.accountbook.adapter.DayParentAdapter
import com.zxy.accountbook.presenter.MainPresenter
import com.zxy.accountbook.utils.MyItemTouchHelperCallback
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
    private var listData = mutableListOf<String>("1","2","2","2","2")
    private var firstClickBack: Long = 0
    private val mDayAdapter by lazy {
        DayParentAdapter()
    }
    private  val myItemTouchHelperCallback by lazy {
        MyItemTouchHelperCallback(mDayAdapter, this)
    }

    override var layoutId: Int = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        //返回按钮点击事件
        llTitleLeft.visible()
        //recyclerview
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mDayAdapter
        mDayAdapter.setNewInstance(listData)
        //拖动
        var helper = ItemTouchHelper(myItemTouchHelperCallback)
        helper.attachToRecyclerView(mRecyclerView)
        //监听
        initListener()

    }

    private fun initListener() {
        llTitleRightIv1.setOnClickListener {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
        }
        llTitleRightTv.setOnClickListener {
            Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show()
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