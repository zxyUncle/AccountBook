package com.zxy.accountbook.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zxy on 2020/6/23 0023 15:38
 * ******************************************
 * *
 * ******************************************
 */
class TimeUitls {
    companion object {
        fun getData(currentTime: Long, format: String = "yyyy年-MM月dd日-HH时mm分ss秒"): String {
            //"yyyy年-MM月dd日-HH时mm分ss秒"
            var formatter = SimpleDateFormat(format)
            var date = Date(currentTime)
            return formatter.format(date)
        }

        fun getYear(): Int {
            var currentTime = System.currentTimeMillis()
            var formatter = SimpleDateFormat("yyyy")
            var date = Date(currentTime)
            return formatter.format(date).toInt()
        }

        fun getMonth(): Int {
            var currentTime = System.currentTimeMillis()
            var formatter = SimpleDateFormat("MM")
            var date = Date(currentTime)
            return formatter.format(date).toInt()
        }

        fun getDay(): Int {
            var currentTime = System.currentTimeMillis()
            var formatter = SimpleDateFormat("dd")
            var date = Date(currentTime)
            return formatter.format(date).toInt()
        }
    }
}