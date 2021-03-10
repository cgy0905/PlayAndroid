package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.ProjectListContract
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/10 12:01 PM
 * @description:
 */
class ProjectListModel : CommonModel(), ProjectListContract.Model {
    override fun requestProjectList(
        page: Int,
        cid: Int
    ): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getProjectList(page, cid)
    }
}