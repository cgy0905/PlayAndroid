package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.CollectionArticle
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/11 10:28 AM
 * @description:
 */
interface CollectContract {

    interface View : IView {

        fun setCollectList(articles : BaseListResponseBody<CollectionArticle>)

        fun showRemoveCollectSuccess(success: Boolean)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {

        fun getCollectList(page : Int)

        fun removeCollectArticle(id : Int, originId : Int)
    }

    interface Model : IModel {

        fun getCollectList(page: Int) : Observable<HttpResult<BaseListResponseBody<CollectionArticle>>>

        fun removeCollectArticle(id: Int, originId: Int) : Observable<HttpResult<Any>>
    }
}