package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 10:28 AM
 * @description:
 */
interface ShareArticleContract {

    interface View : IView {
        fun getArticleTitle() : String
        fun getArticleLink() : String

        fun showShareArticle(success : Boolean)
    }

    interface Presenter : IPresenter<View> {
        fun shareArticle()
    }

    interface Model : IModel {
        fun shareArticle(map: MutableMap<String, Any>) : Observable<HttpResult<Any>>
    }
}