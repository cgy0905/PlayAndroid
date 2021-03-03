package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.SearchListContract
import com.cgy.wanandroid.mvp.model.SearchListModel

/**
 * @author: cgy
 * @date: 2021/3/3 4:45 PM
 * @description:
 */
class SearchListPresenter : CommonPresenter<SearchListContract.Model, SearchListContract.View>(), SearchListContract.Presenter {

    override fun createModel(): SearchListContract.Model?  = SearchListModel()

    override fun queryBySearchKey(page: Int, key: String) {
        mModel?.queryBySearchKey(page, key)?.ss(mModel, mView, page == 0) {
            mView?.showArticles(it.data)
        }
    }
}