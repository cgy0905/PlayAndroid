package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.NavigationContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 5:17 PM
 * @description:
 */
class NavigationModel : BaseModel(), NavigationContract.Model {

    override fun requestNavigationList(): Observable<HttpResult<List<NavigationBean>>> {
        return RetrofitHelper.service.getNavigationList()
    }
}