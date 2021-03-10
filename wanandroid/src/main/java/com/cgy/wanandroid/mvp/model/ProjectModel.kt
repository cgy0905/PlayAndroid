package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.ProjectContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/10 11:06 AM
 * @description:
 */
class ProjectModel : BaseModel(), ProjectContract.Model {

    override fun requestProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>> {
        return RetrofitHelper.service.getProjectTree()
    }
}