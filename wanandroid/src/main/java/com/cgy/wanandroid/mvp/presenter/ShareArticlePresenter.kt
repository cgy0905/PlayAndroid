package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.ShareArticleContract
import com.cgy.wanandroid.mvp.model.ShareArticleModel

/**
 * @author: cgy
 * @date: 2021/3/5 10:32 AM
 * @description:
 */
class ShareArticlePresenter : BasePresenter<ShareArticleModel, ShareArticleContract.View>(), ShareArticleContract.Presenter {

    override fun createModel(): ShareArticleModel? = ShareArticleModel()

    override fun shareArticle() {
        val title = mView?.getArticleTitle().toString()
        val link = mView?.getArticleLink().toString()

        if (title.isEmpty()) {
            mView?.showMsg("文章标题不能为空")
            return
        }

        if (link.isEmpty()) {
            mView?.showMsg("文章链接不能为空")
        }
        val map = mutableMapOf<String, Any>()
        map[title] = title
        map[link] = link
        mModel?.shareArticle(map)?.ss(mModel, mView, true) {
            mView?.showShareArticle(true)
        }
    }
}