package com.cgy.wanandroid.base

import io.reactivex.disposables.Disposable

/**
 * @author: cgy
 * @date: 2021/1/19 10:26 AM
 * @description:
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()
}