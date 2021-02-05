package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/4 3:26 PM
 * @description:
 */
interface LoginContract {

    interface View : IView {
        fun loginSuccess(data : LoginData)

        fun loginFail()
    }

    interface Presenter : IPresenter<View> {

        fun login(username : String, password : String)
    }

    interface Model : IModel {
        fun login(username: String, password: String) : Observable<HttpResult<LoginData>>
    }
}