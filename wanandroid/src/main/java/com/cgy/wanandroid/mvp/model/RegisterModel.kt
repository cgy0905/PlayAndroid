package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.RegisterContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/5 4:59 PM
 * @description:
 */
class RegisterModel : BaseModel(), RegisterContract.Model {
    override fun register(
        username: String,
        password: String,
        confirmPwd: String
    ): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.register(username, password, confirmPwd)
    }
}