package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.ShareContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.ShareResponseBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/15 10:10 AM
 * @description:
 */
class ShareModel : CommonModel(), ShareContract.Model {

    override fun getShareList(page: Int): Observable<HttpResult<ShareResponseBody>> {
        return RetrofitHelper.service.getShareList(page)
    }

    override fun deleteShareArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.deleteShareArticle(id)
    }
}