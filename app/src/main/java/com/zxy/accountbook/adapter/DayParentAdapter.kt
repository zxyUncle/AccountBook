package com.zxy.accountbook.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.accountbook.R
import com.zxy.accountbook.utils.MyItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_day_parent.view.*

/**
 * Created by zxy on 2020/6/23 0023 12:10
 * ******************************************
 * * 天父级适配器
 * ******************************************
 */
class DayParentAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_day_parent) {
    private var listData = mutableListOf<String>("1","2","2","2","2")

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.run {
            val daySonAdapter = DaySonAdapter()
            rvDayParent.layoutManager = LinearLayoutManager(context)
            rvDayParent.adapter =daySonAdapter
            daySonAdapter.setNewInstance(listData)
            //拖动
            var myItemTouchHelperCallback = MyItemTouchHelperCallback(daySonAdapter, context)
            var helper = ItemTouchHelper(myItemTouchHelperCallback)
            helper.attachToRecyclerView(rvDayParent)
        }


    }




}