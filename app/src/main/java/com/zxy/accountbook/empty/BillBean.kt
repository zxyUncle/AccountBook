package com.zxy.accountbook.empty

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by zxy on 2020/6/23 0023 15:19
 * ******************************************
 * * 账单数据
 * ******************************************
 */
data class BillBean(
    var yearName: Int,
    var monthName: Int,
    var dayName: Int,
    var timestamp: Long,//时间戳
    @Column(ignore = true)
    var isExtend: Boolean = false,
    var id: Long = 0,
    @Column(ignore = true)
    var listData: ArrayList<DataBean> = arrayListOf()
) : LitePalSupport()
