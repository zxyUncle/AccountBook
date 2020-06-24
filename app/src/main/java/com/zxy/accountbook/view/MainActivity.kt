package com.zxy.accountbook.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zxy.accountbook.R
import com.zxy.accountbook.adapter.DayParentAdapter
import com.zxy.accountbook.adapter.MonthAdapter
import com.zxy.accountbook.adapter.YearAdapter
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.empty.DataBean
import com.zxy.accountbook.presenter.MainPresenter
import com.zxy.accountbook.utils.BottomSheetDialogUtilsMonth
import com.zxy.accountbook.utils.BottomSheetDialogUtilsYear
import com.zxy.accountbook.utils.TimeUitls
import com.zxy.accountbook.utils.snackbar.ZToast
import com.zxy.zxymvp.mvp.impl.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_year.view.*

/**
 * Created by zxy on 2020/6/23 0023 11:30
 * ******************************************
 * * 主入口
 * ******************************************
 */
class MainActivity : BaseMvpActivity<MainPresenter>() {
    private var firstClickBack: Long = 0
    private lateinit var viewYearDialog: View
    private lateinit var viewMonthDialog: View
    private var todyYear = 0
    private var todyMonth = 0

    private val mDayAdapter by lazy {
        DayParentAdapter()
    }
    private val mMonthAdapter by lazy {
        MonthAdapter()
    }
    private val mYearAdapter by lazy {
        YearAdapter()
    }

    override var layoutId: Int = R.layout.activity_main

    @SuppressLint("SetTextI18n")
    override fun initView(savedInstanceState: Bundle?) {
        //recyclerview
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mDayAdapter
        tvTitle.text = "${TimeUitls.getData(System.currentTimeMillis(), "yyyy年MM月dd日")}  张三疯的笔记"
        initYear()
        initMonth()
        initListener()
        initData()
    }

    private fun initData() {
        //查询当月数据
        presenter.selectMonthAll(TimeUitls.getYear().toString(), TimeUitls.getMonth().toString())
        presenter.selectYearAll()
        presenter.selectMonthAll(todyYear)
    }

    override fun hasToolBar() = false //不使用父类的ToolBar

    private fun initListener() {
        //年
        llTitleLeft.setOnClickListener {
            BottomSheetDialogUtilsYear.instance.bottomSheetDialog()?.show()
        }
        //月
        llTitleRightIv.setOnClickListener {
            BottomSheetDialogUtilsMonth.instance.bottomSheetDialog()?.show()
        }
        //扩展开
        mDayAdapter.setOnItemClickListener { adapter, view, position ->
            mDayAdapter.setExtend(position)
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
     * 月
     */
    @SuppressLint("SetTextI18n")
    private fun initMonth() {
        todyMonth = TimeUitls.getYear()
        viewMonthDialog = layoutInflater.inflate(R.layout.bottom_sheet_year, null)
        BottomSheetDialogUtilsMonth.instance.init(this, viewMonthDialog, 0.8f)
        viewMonthDialog.rvBottomSheetYear.layoutManager = LinearLayoutManager(this)
        viewMonthDialog.rvBottomSheetYear.adapter = mMonthAdapter
        mMonthAdapter.setOnItemClickListener { adapter, view, position ->
            todyMonth = mMonthAdapter.data[position].monthName
            tvTitle.text = "${TimeUitls.getData(
                mMonthAdapter.data[position].timestamp,
                "yyyy年MM月dd日"
            )}  张三疯的笔记"
            BottomSheetDialogUtilsMonth.instance.bottomSheetDialog()?.cancel()
        }
    }

    //年
    @SuppressLint("SetTextI18n")
    private fun initYear() {
        todyYear = TimeUitls.getYear()
        viewYearDialog = layoutInflater.inflate(R.layout.bottom_sheet_year, null)
        BottomSheetDialogUtilsYear.instance.init(this, viewYearDialog, 0.8f)
        viewYearDialog.rvBottomSheetYear.layoutManager = LinearLayoutManager(this)
        viewYearDialog.rvBottomSheetYear.adapter = mYearAdapter
        mYearAdapter.setOnItemClickListener { adapter, view, position ->
            todyYear = mYearAdapter.data[position].yearName
            tvTitle.text =
                "${TimeUitls.getData(mYearAdapter.data[position].timestamp, "yyyy年MM月dd日")}  张三疯的笔记"
            BottomSheetDialogUtilsYear.instance.bottomSheetDialog()?.cancel()
        }
    }

    /**
     * 获取到数据的绑定方法
     */
    fun bindData(listData: MutableList<BillBean>) {
        mDayAdapter.setNewInstance(listData)
    }

    /**
     * 获取到数据年
     */
    fun bindDataYear(listData: MutableList<BillBean>) {
        mYearAdapter.setNewInstance(listData)
    }

    /**
     * 获取到数据月
     */
    fun bindDataMonth(listData: MutableList<BillBean>) {
        mMonthAdapter.setNewInstance(listData)
    }

    /**
     * 返回
     */
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