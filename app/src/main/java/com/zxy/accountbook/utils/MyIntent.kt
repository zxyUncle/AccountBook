package com.zxy.accountbook.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import com.zxy.accountbook.R

/**
 * Created by ZXY on 2019/6/6 16:20.
 * Class functions
 * *********************************************************
 * * 跳转
 * *********************************************************
 */
/**
 * 普通跳转
 * @param mContext Context
 * @param zClass Class<*>
 * @param bundel Bundle
 */
fun Any.startActivity(mContext: Context, zClass: Class<*>, bundle: Bundle? = null) {
    val intent = Intent()
    intent.setClass(mContext, zClass)
    if (bundle != null) intent.putExtras(bundle)
    mContext.startActivity(intent)
}

/**
 * 转场动画
 * @param mContext Context
 * @param view View
 * @param sharedElementName String
 * @param zClass Class<*>
 * @param bundel Bundle
 */
fun Any.startActivityOptionsCompatBundel(
    mContext: Context,
    view: View,
    sharedElementName: String,
    zClass: Class<*>,
    bundle: Bundle? = null
) {
    val makeSceneTransitionAnimation =
        ActivityOptionsCompat.makeSceneTransitionAnimation(
            mContext as Activity,
            view,
            sharedElementName
        )
    val intent = Intent()
    if (bundle != null) intent.putExtras(bundle)
    intent.setClass(
        mContext, zClass
    )
    mContext.startActivity(intent, makeSceneTransitionAnimation.toBundle())
}

/**
 * 有返回值的转场动画
 * @param mContext Context
 * @param view View
 * @param sharedElementName String
 * @param zClass Class<*>
 * @param bundel Bundle
 */
fun Any.startActivityOptionsCompatBundelResult(
    mContext: Context,
    view: View,
    sharedElementName: String,
    zClass: Class<*>,
    bundle: Bundle? = null
) {
    val makeSceneTransitionAnimation =
        ActivityOptionsCompat.makeSceneTransitionAnimation(
            mContext as Activity,
            view,
            sharedElementName
        )
    val intent = Intent()
    if (bundle != null) intent.putExtras(bundle)
    intent.setClass(mContext, zClass)
    mContext.startActivityForResult(intent, 1, makeSceneTransitionAnimation.toBundle())
}


/**
 * 左右转场动画
 * @param mContext Context
 * @param zClass Class<*>
 */
fun Any.startActivityAnimationLeft(
    mContext: Context,
    zClass: Class<*>,
    bundel: Bundle? = null,
    nameBundle: String? = null
) {
    val optionsCompat = ActivityOptionsCompat
        .makeCustomAnimation(mContext, R.anim.translate_in, R.anim.translate_out)
    val intent = Intent()
    intent.setClass(mContext, zClass)
    if (bundel != null) {
        if (nameBundle != null) {
            intent.putExtra(nameBundle, bundel)
        } else {
            intent.putExtras(bundel)
        }
    }
    mContext.startActivity(intent, optionsCompat.toBundle())
}


fun Any.startActivityAnimationLeftReslt(
    mContext: Activity,
    zClass: Class<*>,
    bundle: Bundle? = null, requestCode: Int = 10086
) {
    val optionsCompat = ActivityOptionsCompat
        .makeCustomAnimation(mContext, R.anim.translate_in, R.anim.translate_out)
    val intent = Intent()
    intent.setClass(mContext, zClass)
    if (bundle != null) intent.putExtras(bundle)
    mContext.startActivityForResult(intent, requestCode, optionsCompat.toBundle())
}


/**
 * 上下转场动画
 * @param mContext Context
 * @param zClass Class<*>
 */
fun Any.startActivityAnimationTop(mContext: Context, zClass: Class<*>, bundel: Bundle? = null) {
    val optionsCompat = ActivityOptionsCompat
        .makeCustomAnimation(mContext, R.anim.translate_top, R.anim.translate_bottom)
    val intent = Intent()
    intent.setClass(mContext, zClass)
    if (bundel != null) intent.putExtras(bundel)
    mContext.startActivity(intent, optionsCompat.toBundle())
}

fun Any.finishLeft(activity: Activity) {
    activity.overridePendingTransition(R.anim.translate_out, R.anim.translate_in)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.finishAfterTransition()
    } else {
        activity.finish()
    }

}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Any.finishTop(activity: Activity) {
    activity.overridePendingTransition(R.anim.translate_top, R.anim.translate_bottom)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.finishAfterTransition()
    } else {
        activity.finish()
    }
}
