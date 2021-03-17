package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.CollectContract
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.CollectionArticle
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/11 10:39 AM
 * @description:
 */
class CollectModel : BaseModel(), CollectContract.Model {

    override fun getCollectList(page: Int): Observable<HttpResult<BaseListResponseBody<CollectionArticle>>> {
        return RetrofitHelper.service.getCollectList(page)
    }

    override fun removeCollectArticle(id: Int, originId: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.removeCollectArticle(id, originId)
    }
}