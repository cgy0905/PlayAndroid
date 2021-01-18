package com.cgy.wanandroid.ext

import android.content.Context
import com.cgy.wanandroid.widget.CustomToast

/**
 * @author: cgy
 * @date: 2021/1/18 3:19 PM
 * @description:
 */

fun Context.showToast(content : String) {
    CustomToast(this, content).show()
}