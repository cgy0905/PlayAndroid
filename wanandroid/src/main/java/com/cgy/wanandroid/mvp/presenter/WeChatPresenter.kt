package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.WeChatContract
import com.cgy.wanandroid.mvp.model.WeChatModel

/**
 * @author: cgy
 * @date: 2021/3/5 11:20 AM
 * @description:
 */
class WeChatPresenter : BasePresenter<WeChatContract.Model, WeChatContract.View>(), WeChatContract.Presenter {

    override fun createModel(): WeChatContract.Model? = WeChatModel()

    override fun getWXChapters() {
        mModel?.getWXChapters()?.ss(mModel, mView) {
            mView?.showWXChapters(it.data)
        }
    }
}