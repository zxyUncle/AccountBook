package com.zxy.accountbook.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zxy.accountbook.R


/**
 * Created by zxy on 2020/4/22 0022 11:23
 * ******************************************
 * * BottomSheetDialog底部弹出框
 * ******************************************
 */
class BottomSheetDialogUtilsMonth {
    private lateinit var bottomSheetDialog: BottomSheetDialog

    //zxy 单例模式
    private constructor() {}

    companion object {
        val instance: BottomSheetDialogUtilsMonth by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BottomSheetDialogUtilsMonth()
        }
    }

    /**
     * 返回当前BottomSheetDialog对象
     */
    fun bottomSheetDialog(): BottomSheetDialog? {
        if (bottomSheetDialog != null)
            return bottomSheetDialog
        return null
    }

    fun setCancelable() {
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * @param Context 上下文
     * @param minimumHeight 弹窗的高度 0.1-1 默认为0.8
     * @param style: 样式文件
     */
    fun init(mContext: Context, view: View, minimumHeight: Float = 1f, style: Int = R.style.bottomSheetDialog) {
        bottomSheetDialog = BottomSheetDialog(mContext, style)
        var heigit = 0
        heigit = if (minimumHeight > 0 && minimumHeight <= 1) {
            getPeekHeight(mContext, minimumHeight).toInt()
        } else {
            getPeekHeight(mContext, 0.8f).toInt()
        }
        view.rootView.minimumHeight = heigit
        bottomSheetDialog.setContentView(view)

        var mDialogBehavior =
            BottomSheetBehavior.from(view.parent as View)
        mDialogBehavior.peekHeight = heigit
        mDialogBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        mDialogBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (i == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetDialog.dismiss()
                    mDialogBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            override fun onSlide(view: View, v: Float) {
            }
        })


        //设置透明背景
        bottomSheetDialog.window?.findViewById<View>(R.id.design_bottom_sheet)
            ?.setBackgroundResource(android.R.color.transparent)
        (mContext as Activity).window.setWindowAnimations(style)//设置动画效果
    }


    /**
     * 弹窗高度，
     * @return height
     */
    private fun getPeekHeight(mContext: Context, minimumHeight: Float): Float {
        val windowManager = mContext
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val peekHeight = mContext.resources.displayMetrics.heightPixels
        val peekHeight = windowManager.defaultDisplay.height
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight * minimumHeight
    }

}