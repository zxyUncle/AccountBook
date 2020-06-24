package com.zxy.accountbook.presenter

import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.empty.DataBean
import com.zxy.accountbook.utils.TimeUitls
import com.zxy.accountbook.utils.snackbar.ZToast
import com.zxy.accountbook.view.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter
import org.litepal.LitePal
import org.litepal.extension.find

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
     * 查询所有年
     */
    fun selectYearAll() {
        var list: MutableList<BillBean> = mutableListOf()
        var listBill = LitePal.where("yearName > 1993")
            .find<BillBean>() as MutableList<BillBean>
        list.addAll(listBill)
        if (listBill.isNotEmpty()) {
            list = removeDuplicateYear(listBill)
            for (index in list.indices) {
                if (list[index].yearName == TimeUitls.getYear()) {
                    list[index].isExtend = true
                }

            }
        }
        view.bindDataYear(list)

    }

    /**
     * 查询所有月
     */
    fun selectMonthAll(todyYear:Int) {
        var list: MutableList<BillBean> = mutableListOf()
        var listBill = LitePal.where("yearName=?",todyYear.toString())
            .find<BillBean>() as MutableList<BillBean>
        list.addAll(listBill)
        if (listBill.isNotEmpty()) {
            list = removeDuplicateMonth(listBill)
            for (index in list.indices) {
                if (list[index].yearName == TimeUitls.getYear()) {
                    list[index].isExtend = true
                }
            }
        }
        view.bindDataMonth(list)

    }

    //1.循环list中所有的元素然后删除
    private fun removeDuplicateYear(list: MutableList<BillBean>): MutableList<BillBean> {
        for (i in 0 until list.size) {
            for (j in list.size - 1 downTo i + 1) {
                if (list[i].yearName == list[j].yearName) list.removeAt(j)
            }
        }
        return list
    }
    //1.循环list中所有的元素然后删除
    private fun removeDuplicateMonth(list: MutableList<BillBean>): MutableList<BillBean> {
        for (i in 0 until list.size) {
            for (j in list.size - 1 downTo i + 1) {
                if (list[i].monthName == list[j].monthName) list.removeAt(j)
            }
        }
        return list
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