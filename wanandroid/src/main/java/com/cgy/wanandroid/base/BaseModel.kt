package com.cgy.wanandroid.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author: cgy
 * @date: 2021/1/19 10:25 AM
 * @description:
 */
abstract class BaseModel : IModel, LifecycleObserver {

    private var mCompositeDisposable : CompositeDisposable? = null

    override fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    override fun onDetach() {
        unDispose()
    }

    private fun unDispose() {
        mCompositeDisposable?.clear() //保证Activity结束时取消
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }
}