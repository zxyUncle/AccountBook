package com.zxy.accountbook.empty

import org.litepal.crud.LitePalSupport

/**
 * Created by zxy on 2020/6/23 0023 15:19
 * ******************************************
 * * 账单数据
 * ******************************************
 */
data class BillBean(
    var id:String,
    var yearName:Int,
    var monthName:Int,
    var dayName:Int,
    var timestamp:Long,//时间戳
    var state:Int,//账单类型：1、支出，2、收入
    var type:Int,//水电煤、
    var content:String,//账单内容
    var price:Double//账单内容
):LitePalSupport()