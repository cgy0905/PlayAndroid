package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.RegisterContract
import com.cgy.wanandroid.mvp.model.RegisterModel

/**
 * @author: cgy
 * @date: 2021/2/5 5:02 PM
 * @description:
 */
class RegisterPresenter : BasePresenter<RegisterContract.Model, RegisterContract.View>(), RegisterContract.Presenter {

    override fun createModel(): RegisterContract.Model? = RegisterModel()

    override fun register(username: String, password: String, confirmPwd: String) {
        mModel?.register(username, password, confirmPwd)?.ss(mModel, mView) {
            mView?.apply {
                if (it.errorCode != 0) {
                    registerFail()
                } else {
                    registerSuccess(it.data)
                }
            }
        }
    }
}