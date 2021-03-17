package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.ShareContract
import com.cgy.wanandroid.mvp.model.ShareModel

/**
 * @author: cgy
 * @date: 2021/3/15 10:10 AM
 * @description:
 */
class SharePresenter : CommonPresenter<ShareModel, ShareContract.View>(), ShareContract.Presenter {

    override fun createModel(): ShareModel? = ShareModel()

    override fun getShareList(page: Int) {
        mModel?.getShareList(page)?.ss(mModel, mView, page == 1) {
            mView?.showShareList(it.data)
        }
    }

    override fun deleteShareArticle(id: Int) {
        mModel?.deleteShareArticle(id)?.ss(mModel, mView, true) {
            mView?.showDeleteArticle(true)
        }
    }
}