package com.cgy.wanandroid.ext

import com.cgy.wanandroid.R
import com.cgy.wanandroid.app.App
import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.http.exception.ErrorStatus
import com.cgy.wanandroid.http.exception.ExceptionHandle
import com.cgy.wanandroid.http.function.RetryWithDelay
import com.cgy.wanandroid.mvp.model.BaseBean
import com.cgy.wanandroid.utils.NetWorkUtil
import com.cgy.wanandroid.utils.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author: cgy
 * @date: 2021/2/1 4:46 PM
 * @description:
 */
fun <T : BaseBean> Observable<T>.ss(

    model: IModel?,
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) view?.showLoading()
                model?.addDisposable(d)
                if (!NetWorkUtil.isNetworkConnected(App.instance)) {
                    view?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                    onComplete()
                }
            }

            override fun onNext(t: T) {
                when {
                    t.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                    t.errorCode == ErrorStatus.TOKEN_INVALID -> {
                        //Token过期 重新登录
                    }
                    else -> view?.showDefaultMsg(t.errorMsg)
                }
            }

            override fun onError(t: Throwable) {
                view?.hideLoading()
                view?.showError(ExceptionHandle.handleException(t))
            }
        })
}

fun <T : BaseBean> Observable<T>.sss(
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit,
    onError: ((T) -> Unit)? = null
): Disposable {
    if (isShowLoading) view?.showLoading()
    return this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe({
            when {
                it.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(it)
                it.errorCode == ErrorStatus.TOKEN_INVALID -> {
                    //Token过期,重新登录
                }

                else -> {
                    if (onError != null) {
                        onError.invoke(it)
                    } else {
                        if (it.errorMsg.isNotEmpty())
                            view?.showDefaultMsg(it.errorMsg)
                    }
                }
            }
            view?.hideLoading()
        }, {
            view?.hideLoading()
            view?.showError(ExceptionHandle.handleException(it))
        })
}