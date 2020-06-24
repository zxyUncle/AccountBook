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
class DaySonAdapter : BaseQuickAdapter<DataBean, BaseViewHolder>(R.layout.adapter_day_son) {
    override fun convert(holder: BaseViewHolder, item: DataBean) {
       holder.itemView.run {
           tvDaySonType.text = item.content
           tvDaySonPrice.text = item.price.toString()
          when(item.state){
              1->{
                  tvDaySonPrice.setTextColor(context.resources.getColor(R.color.color_333333))
              }
              2->{
                  tvDaySonPrice.setTextColor(context.resources.getColor(R.color.color_2dd186))
              }
          }
       }

    }

}