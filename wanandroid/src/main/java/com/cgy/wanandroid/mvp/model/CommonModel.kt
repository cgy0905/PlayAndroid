package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.CommonContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/7 1:42 PM
 * @description:
 */
open class CommonModel : BaseModel(), CommonContract.Model{
    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }
}