package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.HomeContract
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.Banner
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/7 1:46 PM
 * @description:
 */
class HomeModel : CommonModel(), HomeContract.Model {

    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }
}