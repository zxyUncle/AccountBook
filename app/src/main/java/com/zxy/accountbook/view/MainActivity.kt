package com.zxy.accountbook.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zxy.accountbook.R
import com.zxy.accountbook.adapter.DayParentAdapter
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.empty.DataBean
import com.zxy.accountbook.presenter.MainPresenter
import com.zxy.accountbook.utils.TimeUitls
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
    private var firstClickBack: Long = 0
    private val mDayAdapter by lazy {
        DayParentAdapter()
    }
    override var layoutId: Int = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        //recyclerview
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mDayAdapter
        //监听
        initListener()
        initData()
    }

    private fun initData() {
        presenter.selectMonthAll(TimeUitls.getYear().toString(), TimeUitls.getMonth().toString())
    }

    override fun hasToolBar() = false

    private fun initListener() {
        llTitleLeft.setOnClickListener {
            Toast.makeText(this, "年", Toast.LENGTH_SHORT).show()
        }
        llTitleRightIv.setOnClickListener {
            Toast.makeText(this, "月", Toast.LENGTH_SHORT).show()
        }

        mDayAdapter.setOnItemClickListener { adapter, view, position ->

        }
        //增加数据
        ivMainAdd.setOnClickListener {
            val saveBillBean = presenter.saveBillBean()
            val bid = saveBillBean.id
            var dataBean =
                DataBean(1, 1, "煤气账单内容", 500.0, bid)
            val saveDataBean = presenter.saveDataBean(dataBean)
            if (saveDataBean) {
                initData()
            }
        }
    }

    /**
     * 获取到数据的绑定方法
     */
    fun bindData(listData: MutableList<BillBean>) {
        mDayAdapter.setNewInstance(listData)
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