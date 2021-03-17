package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 1:34 PM
 * @description:
 */
interface KnowledgeContract {

    interface View : CommonContract.View {
        fun scrollToTop()

        fun setKnowledgeList(articles : ArticleResponseBody)
    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestKnowledgeList(page : Int, cid : Int)
    }

    interface Model : CommonContract.Model {

        fun requestKnowledgeList(page: Int, cid: Int) : Observable<HttpResult<ArticleResponseBody>>
    }
}