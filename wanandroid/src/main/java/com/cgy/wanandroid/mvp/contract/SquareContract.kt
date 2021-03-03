package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/3 6:00 PM
 * @description:
 */
interface SquareContract {

    interface View : CommonContract.View {
        fun scrollToTop()
        fun showSquareList(data: ArticleResponseBody)
    }

    interface Presenter : CommonContract.Presenter<View> {
        fun getSquareList(page : Int)
    }

    interface Model : CommonContract.Model {
        fun getSquareList(page : Int) : Observable<HttpResult<ArticleResponseBody>>
    }
}