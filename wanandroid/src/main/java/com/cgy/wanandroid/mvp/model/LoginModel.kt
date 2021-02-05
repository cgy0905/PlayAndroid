package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.LoginData
import com.cgy.wanandroid.mvp.contract.LoginContract
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/5 4:32 PM
 * @description:
 */
class LoginModel : BaseModel(), LoginContract.Model{
    override fun login(username: String, password: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.login(username, password)
    }
}