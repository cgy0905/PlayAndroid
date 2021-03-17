package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.SearchListContract
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/3 4:46 PM
 * @description:
 */
class SearchListModel : CommonModel(), SearchListContract.Model {
    override fun queryBySearchKey(page: Int, key: String): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.queryBySearchKey(page, key)
    }
}