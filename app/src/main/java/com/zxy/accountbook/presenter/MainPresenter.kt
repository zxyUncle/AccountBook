package com.zxy.accountbook.presenter

import android.util.Log
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.utils.TimeUitls
import com.zxy.accountbook.view.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter
import org.litepal.LitePal
import org.litepal.extension.findAsync
import java.util.*

/**
 * Created by zxy on 2020/6/9 0009 22:14
 * ******************************************
 * * M层
 * ******************************************
 */
class MainPresenter : BasePresenter<MainActivity>() {
//    var id:Long,
//    var yearName:Int,
//    var monthName:Int,
//    var dayName:Int,
//    var timestamp:Long,//时间戳
//    var state:Int,//账单类型：1、支出，2、收入
//    var type:Int,//水电煤、
//    var content:String,//账单内容
//    var price:Double//账单内容

    fun add() {
        val randomUUID = UUID.randomUUID().toString()
        val currentTimeMillis = System.currentTimeMillis()
        val year = TimeUitls.getData(currentTimeMillis, "yyyy").toInt()
        val month = TimeUitls.getData(currentTimeMillis, "MM").toInt()
        val day = TimeUitls.getData(currentTimeMillis, "dd").toInt()
        var billBean =
            BillBean(randomUUID, year, month, day, currentTimeMillis, 1, 1, "账单内容", 533.0)
        val save = billBean.save()
        Log.e("zxy","$save")
    }

    fun select() {
        Log.e("zxy","stringBuffer.toString()")
        var stringBuffer = StringBuffer()
        LitePal.where("yearName like ?","").findAsync<BillBean>().listen {
            stringBuffer.append("${it}\n")

            Log.e("zxy",stringBuffer.toString())
        }

    }
}