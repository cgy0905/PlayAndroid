package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/10 11:54 AM
 * @description:
 */
interface ProjectListContract {

    interface View : CommonContract.View {

        fun scrollToTop()

        fun setProjectList(articles : ArticleResponseBody)
    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestProjectList(page : Int, cid : Int)
    }

    interface Model : CommonContract.Model {

        fun requestProjectList(page : Int, cid: Int) : Observable<HttpResult<ArticleResponseBody>>
    }
}