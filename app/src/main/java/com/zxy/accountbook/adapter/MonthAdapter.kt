package com.zxy.accountbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.accountbook.R
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.utils.MyItemTouchHelperCallback
import kotlinx.android.synthetic.main.adapter_month.view.*

/**
 * Created by zxy on 2020/6/23 0023 12:10
 * ******************************************
 * * 月份适配器
 * ******************************************
 */
class MonthAdapter : BaseQuickAdapter<BillBean, BaseViewHolder>(R.layout.adapter_month) {
    override fun convert(holder: BaseViewHolder, item: BillBean) {
        holder.itemView.run {
            tvYearName.text = "${item.monthName}"
            if (item.isExtend) {
                tvYearName.setTextColor(context.resources.getColor(R.color.color_5381ff))
            } else {
                tvYearName.setTextColor(context.resources.getColor(R.color.color_333333))
            }
        }
    }

}