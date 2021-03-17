package com.cgy.wanandroid.utils

import com.cgy.wanandroid.rx.IoMainScheduler

/**
 * @author: cgy
 * @date: 2021/2/1 4:51 PM
 * @description:
 */
object SchedulerUtils {

    fun <T> ioToMain() : IoMainScheduler<T> {
        return IoMainScheduler()
    }
}