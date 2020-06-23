package com.zxy.accountbook.presenter

import android.util.Log
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.empty.DataBean
import com.zxy.accountbook.utils.TimeUitls
import com.zxy.accountbook.utils.snackbar.ZToast
import com.zxy.accountbook.view.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter
import org.litepal.LitePal
import org.litepal.exceptions.DataSupportException
import org.litepal.extension.find
import org.litepal.extension.findAsync
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by zxy on 2020/6/9 0009 22:14
 * ******************************************
 * * M层
 * ******************************************
 */
class MainPresenter : BasePresenter<MainActivity>() {
    /**
     * 保存日期
     */
    fun saveBillBean(): BillBean {
        val currentTimeMillis = System.currentTimeMillis()
        val year = TimeUitls.getYear()
        val month = TimeUitls.getMonth()
        val day = TimeUitls.getDay()
        var list = selectBillBean(year.toString(), month.toString(), day.toString())
        if (list.isEmpty()) {
            var billBean =
                BillBean(year, month, day, currentTimeMillis)
            billBean.assignBaseObjId(0)
            val save = billBean.save()
            list = selectBillBean(year.toString(), month.toString(), day.toString())
        }
        return list[0]
    }

    /**
     * 保存数据
     */
    fun saveDataBean(dataBean: DataBean): Boolean {
        dataBean.assignBaseObjId(0)
        val save = dataBean.save()
        if (save) {
            ZToast.showS(view, "保存成功")
        } else {
            ZToast.showE(view, "保存失败")
        }
        return save

    }

    /**
     * 查询所有月
     */
    fun selectMonthAll(year: String = "", month: String = "") {
        val listBill = LitePal.where("yearName=? and monthName=?", year, month)
            .find<BillBean>()
        if (listBill.isNotEmpty()) {
            for (index in listBill.indices) {
                if (listBill[index].yearName == TimeUitls.getYear()
                    && listBill[index].monthName == TimeUitls.getMonth()
                    && listBill[index].dayName == TimeUitls.getDay()
                ) {
                    listBill[index].isExtend = true
                }
                var listData = LitePal.where("bid=? ", listBill[index].id!!.toString())
                    .find<DataBean>()
                listBill[index].listData = listData as ArrayList<DataBean>
            }

        }
        view.bindData(listBill as MutableList<BillBean>)

    }

    /**
     * 查询单个日期
     */
    private fun selectBillBean(
        year: String = "",
        month: String = "",
        day: String = ""
    ): List<BillBean> {
        return LitePal.where("yearName=? and monthName=? and dayName=?", year, month, day)
            .find<BillBean>()
    }

}