package com.zxy.accountbook.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by zxy on 2019/8/24-10:13
 * Class functions
 * ******************************************
 * * View 的扩展
 * ******************************************
 */

/**
 * visible:0-VISIBLE
 *  visible:1-GONE
 *   visible:orther-INVISIBLE
 */
fun View.visible(visible: Int = 2) {
    when (visible) {
        0 -> this.visibility = View.VISIBLE
        1 -> this.visibility = View.GONE
        else -> this.visibility = View.INVISIBLE
    }
}