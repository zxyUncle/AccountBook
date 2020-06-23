package com.zxy.accountbook.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.accountbook.R
import com.zxy.accountbook.empty.BillBean
import com.zxy.accountbook.utils.MyItemTouchHelperCallback
import com.zxy.accountbook.utils.TimeUitls
import com.zxy.accountbook.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_day_parent.view.*

/**
 * Created by zxy on 2020/6/23 0023 12:10
 * ******************************************
 * * 天父级适配器
 * ******************************************
 */
class DayParentAdapter : BaseQuickAdapter<BillBean, BaseViewHolder>(R.layout.adapter_day_parent) {

    override fun convert(holder: BaseViewHolder, item: BillBean) {
        holder.itemView.run {

            tvDayParentName.text = TimeUitls.getData(item.timestamp, "MM月dd日 E")

            val daySonAdapter = DaySonAdapter()
            rvDayParent.layoutManager = LinearLayoutManager(context)
            rvDayParent.adapter = daySonAdapter
            daySonAdapter.setNewInstance(item.listData)
            //拖动
            var myItemTouchHelperCallback = MyItemTouchHelperCallback(daySonAdapter, context)
            var helper = ItemTouchHelper(myItemTouchHelperCallback)
            helper.attachToRecyclerView(rvDayParent)

            if (item.isExtend) {
                mGroup.visible(0)
                ivDayParentTop.visible(1)
            } else {
                mGroup.visible(1)
                ivDayParentTop.visible(0)
            }
        }


    }


}