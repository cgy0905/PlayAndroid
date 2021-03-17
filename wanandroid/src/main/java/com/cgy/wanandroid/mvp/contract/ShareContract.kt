package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.ShareResponseBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/15 10:07 AM
 * @description:
 */
interface ShareContract {

    interface View : CommonContract.View {
        fun showShareList(data : ShareResponseBody)
        fun showDeleteArticle(success : Boolean)
    }

    interface Presenter : CommonContract.Presenter<View> {
        fun getShareList(page : Int)
        fun deleteShareArticle(id : Int)
    }
    interface Model : CommonContract.Model {
        fun getShareList(page: Int) : Observable<HttpResult<ShareResponseBody>>
        fun deleteShareArticle(id : Int) : Observable<HttpResult<Any>>
    }
}