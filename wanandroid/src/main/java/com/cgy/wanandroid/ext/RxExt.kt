package com.cgy.wanandroid.ext

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.BaseBean
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * @author: cgy
 * @date: 2021/2/1 4:46 PM
 * @description:
 */
fun <T : BaseBean> Observable<T>.ss(
    model : IModel?,
    view : IView?,
    isShowLoading : Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
}