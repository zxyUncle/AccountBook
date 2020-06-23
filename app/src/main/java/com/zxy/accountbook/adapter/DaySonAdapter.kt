package com.zxy.accountbook.adapter

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.accountbook.R
import com.zxy.accountbook.empty.DataBean
import com.zxy.accountbook.utils.MyItemTouchHelperCallback
import kotlinx.android.synthetic.main.adapter_day_parent.view.*
import kotlinx.android.synthetic.main.adapter_day_son.view.*

/**
 * Created by zxy on 2020/6/23 0023 12:10
 * ******************************************
 * * 天子级适配器
 * ******************************************
 */
class DaySonAdapter : BaseQuickAdapter<DataBean, BaseViewHolder>(R.layout.adapter_day_son),
    MyItemTouchHelperCallback.OnItemPositionListener  {
    override fun convert(holder: BaseViewHolder, item: DataBean) {
       holder.itemView.run {
           tvDaySonType.text = item.content
           tvDaySonPrice.text = item.price.toString()
       }

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