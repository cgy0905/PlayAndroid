package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.LoginContract
import com.cgy.wanandroid.mvp.model.LoginModel

/**
 * @author: cgy
 * @date: 2021/2/5 4:30 PM
 * @description:
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter{

    override fun createModel(): LoginContract.Model? = LoginModel()

    override fun login(username: String, password: String) {
        mModel?.login(username, password)?.ss(mModel, mView) {
            mView?.loginSuccess(it.data)
        }
    }
}