package com.zxy.zxymvp.utils

import org.greenrobot.eventbus.EventBus

/**
 * Created by zxy on 2020/6/9 0009 22:00
 * ******************************************
 * * EventBus  工具类
 * ******************************************
 */
class EventBusUtil {
    companion object {
        const val CODE_100: String = "CODE_100"

        fun register(subscriber: Any) {
            EventBus.getDefault().register(subscriber)
        }

        fun unregister(subscriber: Any) {
            EventBus.getDefault().unregister(subscriber)
        }

        fun sendEvent(event: MessageEvent) {
            EventBus.getDefault().post(event)
        }

        fun sendStickyEvent(event: MessageEvent) {
            EventBus.getDefault().postSticky(event)
        }
    }
}