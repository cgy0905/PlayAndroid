package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/5 4:53 PM
 * @description:
 */
interface RegisterContract {

    interface View : IView {
        fun registerSuccess(data : LoginData)

        fun registerFail()
    }

    interface Presenter : IPresenter<View> {
        fun register(username : String, password : String, confirmPwd : String)
    }

    interface Model : IModel {
        fun register(username: String, password: String, confirmPwd: String) : Observable<HttpResult<LoginData>>
    }
}