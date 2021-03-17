package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.UserInfoBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/2/3 11:00 AM
 * @description:
 */
interface MainContract {

    interface View : IView {
        fun showLogoutSuccess(success : Boolean)
        fun showUserInfo(bean : UserInfoBody)
    }

    interface Presenter : IPresenter<View> {
        fun logout()
        fun getUserInfo()
    }

    interface Model : IModel {
        fun logout() : Observable<HttpResult<Any>>
        fun getUserInfo() : Observable<HttpResult<UserInfoBody>>
    }
}