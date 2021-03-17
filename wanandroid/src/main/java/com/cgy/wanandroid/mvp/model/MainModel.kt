package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.UserInfoBody
import com.cgy.wanandroid.mvp.contract.MainContract
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/3 11:07 AM
 * @description:
 */
class MainModel : BaseModel(), MainContract.Model{
    override fun logout(): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.logout()
    }

    override fun getUserInfo(): Observable<HttpResult<UserInfoBody>> {
        return RetrofitHelper.service.getUserInfo()
    }
}