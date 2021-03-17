package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.CollectContract
import com.cgy.wanandroid.mvp.model.CollectModel

/**
 * @author: cgy
 * @date: 2021/3/11 10:37 AM
 * @description:
 */
class CollectPresenter : BasePresenter<CollectContract.Model, CollectContract.View>(), CollectContract.Presenter {

    override fun createModel(): CollectContract.Model?  = CollectModel()

    override fun getCollectList(page: Int) {
        mModel?.getCollectList(page)?.ss(mModel, mView, page == 0) {
            mView?.setCollectList(it.data)
        }
    }

    override fun removeCollectArticle(id: Int, originId: Int) {
        mModel?.removeCollectArticle(id, originId)?.ss(mModel, mView) {
            mView?.showRemoveCollectSuccess(true)
        }
    }
}