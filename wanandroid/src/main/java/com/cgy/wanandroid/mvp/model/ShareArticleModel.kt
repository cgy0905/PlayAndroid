package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.ShareArticleContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 10:33 AM
 * @description:
 */
class ShareArticleModel : BaseModel(), ShareArticleContract.Model {
    override fun shareArticle(map: MutableMap<String, Any>): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.shareArticle(map)
    }
}