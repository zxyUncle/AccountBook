package com.zxy.accountbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.accountbook.R
import com.zxy.accountbook.utils.MyItemTouchHelperCallback

/**
 * Created by zxy on 2020/6/23 0023 12:10
 * ******************************************
 * * 月份适配器
 * ******************************************
 */
class MonthAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_month),
    MyItemTouchHelperCallback.OnItemPositionListener  {
    override fun convert(holder: BaseViewHolder, item: String) {

    }

    override fun onItemSwap(from: Int, target: Int) {
        val s = data[from]
        data.removeAt(from)
        data.add(target, s)
        notifyItemMoved(from, target)
    }

    override fun onItemMoved(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}