package com.cxz.wanandroid.utils

/**
 * @author: cgy
 * @date: 2021/1/26 1:52 PM
 * @description:
 */
object Utils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private const val MIN_CLICK_DELAY_TIME = 1000
    private var lastClickTime: Long = 0
    val isFastClick: Boolean
        get() {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime <= MIN_CLICK_DELAY_TIME) {
                flag = true
            }
            lastClickTime = curClickTime
            return flag
        }
}