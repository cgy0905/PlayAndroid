package com.cgy.wanandroid.base

/**
 * @author: cgy
 * @date: 2021/1/19 10:27 AM
 * @description:
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView : V)

    /**
     * 解绑 View
     */
    fun detachView()
}