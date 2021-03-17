package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.SquareContract
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/3 6:05 PM
 * @description:
 */
class SquareModel : CommonModel(), SquareContract.Model {
    override fun getSquareList(page: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getSquareList(page)
    }
}