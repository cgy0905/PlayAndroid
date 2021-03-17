package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.Banner
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/7 11:38 AM
 * @description:
 */
interface HomeContract {
    interface View : CommonContract.View {
        fun scrollToTop()

        fun setBanner(banners : List<Banner>)

        fun setArticles(articles : ArticleResponseBody)
    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestBanner()

        fun requestHomeData()

        fun requestArticles(num : Int)
    }

    interface Model : CommonContract.Model {

        fun requestBanner() : Observable<HttpResult<List<Banner>>>

        fun requestTopArticles() : Observable<HttpResult<MutableList<Article>>>

        fun requestArticles(num : Int) : Observable<HttpResult<ArticleResponseBody>>
    }

}