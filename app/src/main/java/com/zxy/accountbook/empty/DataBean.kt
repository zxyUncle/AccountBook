package com.zxy.accountbook.empty

import org.litepal.crud.LitePalSupport

/**
 * Created by zxy on 2020/6/23 0023 17:43
 * ******************************************
 * *
 * ******************************************
 */
class DataBean(
    var state: Int,//账单类型：1、支出，2、收入
    var type: Int,//水电煤、
    var content: String,//账单内容
    var price: Double,//价格
    var bid:Long,//日期类的ID
    var id: Long=0
) : LitePalSupport()