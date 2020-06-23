package com.zxy.accountbook.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.view.WindowManager
import com.zxy.accountbook.R


/**
 * Created by zxy on 2019/8/24-10:13
 * Class functions
 * ******************************************
 * * 建造者模式
 * * 自定义万能布局AletDialog
 * ******************************************
 */
class AlertDialogUtils private constructor() {
    lateinit var layoutView: View                           //Dialog的布局文件
    private var cancelable: Boolean = true                          //是否可以取消  true可以
    lateinit var dialog: AlertDialog                        // AlertDilaog
    lateinit var alertDilaogBuilder: AlertDialog.Builder    // AlertDilaog.Builder
    var listView: MutableList<Int>? = null
    var transparency: Float = 0.5f                              // 透明度
    var TYPE: Int =
            4                                           //三种类型 1=样式1    2=样式2     3=自定义样式  4=自定义布局不能为null

    companion object {
        fun build(mContext: Context): Builder {
            return Builder(mContext)
        }
    }

    class Builder(mContext: Context) {
        var mContext = mContext
        var alertDialogUtils = AlertDialogUtils()

        /**
         * 自定义接口
         */
        interface AlertDialogUtilsListener {
            fun onClickDialog(view: View)
        }

        /**
         * 设置布局
         * @param layoutView View
         */
        fun setView(layoutId: Int): Builder {
            alertDialogUtils.layoutView = LayoutInflater.from(mContext).inflate(layoutId, null)
            if (alertDialogUtils.layoutView==null) {
                alertDialogUtils.TYPE = 4
            } else {
                alertDialogUtils.TYPE = 3
            }
            return this
        }

        /**
         * 设置点击事件
         * @param viewId IntArray
         */
        fun setOnClick(vararg viewId: Int): Builder {
            alertDialogUtils.listView = viewId.toTypedArray().toMutableList()
            return this
        }

        /**
         * 是否可以取消  默认true可以
         * @param cancelable Boolean
         */
        fun setCancelable(cancelable: Boolean): Builder {
            alertDialogUtils.cancelable = cancelable
            return this
        }

        /**
         * 设置窗口透明度，默认0.5    0为全透明  1全黑
         * @param transparency Float
         * @return Builder
         */
        fun setTransparency(transparency: Float): Builder {
            alertDialogUtils.transparency = transparency
            return this
        }

        /**
         * 创建AlertDialog
         */
        fun create(alertDialogUtilsListener: AlertDialogUtilsListener? = null): AlertDialogUtils {
            alertDialogUtils.alertDilaogBuilder = AlertDialog.Builder(mContext, R.style.MyDilog)
            alertDialogUtils.alertDilaogBuilder.setView(alertDialogUtils.layoutView)
            alertDialogUtils.dialog = alertDialogUtils.alertDilaogBuilder.create()
            alertDialogUtils.dialog.setCancelable(alertDialogUtils.cancelable)
            alertDialogUtils.dialog.show()

            val lp = alertDialogUtils.dialog.window!!.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            alertDialogUtils.dialog.window!!.setDimAmount(alertDialogUtils.transparency)//设置黑色遮罩层的透明度
            alertDialogUtils.dialog.window!!.attributes = lp
            if (alertDialogUtilsListener!=null) {
                when (alertDialogUtils.TYPE) {
                    3 -> {
                        if (alertDialogUtils.listView!=null) {
                            for (index in 0 until alertDialogUtils.listView!!.size step 1) {
                                alertDialogUtils.layoutView.findViewById<View>(alertDialogUtils.listView!![index])
                                        .setOnClickListener {
                                            alertDialogUtilsListener!!.onClickDialog(it)
                                        }
                            }
                        }
                    }
                    else -> {
                        Toast.makeText(mContext, "AlertDilaog的setView()不能为null", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
            return alertDialogUtils
        }

    }


    fun cancel() {
        if (dialog!=null)
            dialog!!.cancel()
    }


}