package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.ext.sss
import com.cgy.wanandroid.mvp.contract.MainContract
import com.cgy.wanandroid.mvp.model.MainModel

/**
 * @author: cgy
 * @date: 2021/2/3 11:06 AM
 * @description:
 */
class MainPresenter : BasePresenter<MainContract.Model,MainContract.View>(), MainContract.Presenter{

    override fun createModel(): MainContract.Model? = MainModel()

    override fun logout() {
        mModel?.logout()?.ss(mModel, mView) {
            mView?.showLogoutSuccess(success = true)
        }
    }

    override fun getUserInfo() {
        mModel?.getUserInfo()?.sss(mView, false, {
            mView?.showUserInfo(it.data)
        }, {})
    }
}