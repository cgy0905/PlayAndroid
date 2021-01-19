package com.cgy.wanandroid.ext

import android.content.Context
import android.support.v4.app.Fragment
import com.cgy.wanandroid.widget.CustomToast

/**
 * @author: cgy
 * @date: 2021/1/18 3:19 PM
 * @description:
 */

fun Fragment.showToast(content: String) {
    CustomToast(this?.activity?.applicationContext, content).show()
}

fun Context.showToast(content: String) {
    CustomToast(this, content).show()
}