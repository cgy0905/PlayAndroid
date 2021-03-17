package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/3 11:28 AM
 * @description:
 */
interface SearchListContract {

    interface View : CommonContract.View {

        fun showArticles(articles : ArticleResponseBody)

        fun scrollToTop()
    }

    interface Presenter : CommonContract.Presenter<View> {

        fun queryBySearchKey(page : Int, key : String)
    }

    interface Model : CommonContract.Model {
        fun queryBySearchKey(page : Int, key : String) : Observable<HttpResult<ArticleResponseBody>>
    }
}