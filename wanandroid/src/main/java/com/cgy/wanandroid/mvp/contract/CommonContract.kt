package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/7 11:40 AM
 * @description:
 */
interface CommonContract {

    interface View : IView {
        fun showCollectSuccess(success : Boolean)

        fun showCancelCollectSuccess(success: Boolean)
    }

    interface Presenter<in V : View> : IPresenter<V> {
        fun addCollectArticle(id : Int)

        fun cancelCollectArticle(id : Int)
    }

    interface Model : IModel {
        fun addCollectArticle(id: Int) : Observable<HttpResult<Any>>

        fun cancelCollectArticle(id : Int) : Observable<HttpResult<Any>>
    }
}