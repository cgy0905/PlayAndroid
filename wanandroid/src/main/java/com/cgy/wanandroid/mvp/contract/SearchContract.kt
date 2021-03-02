package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HotSearchBean
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.SearchHistoryBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/2 11:16 AM
 * @description:
 */
interface SearchContract {

    interface View : IView {

        fun showHistoryData(historyBeans : MutableList<SearchHistoryBean>)

        fun showHotSearchData(hotSearchList : MutableList<HotSearchBean>)
    }

    interface Presenter : IPresenter<View> {

        fun queryHistory()

        fun saveSearchKey(key : String)

        fun deleteById(id : Long)

        fun clearAllHistory()

        fun getHotSearchData()
    }

    interface Model : IModel {

        fun getHotSearchData() : Observable<HttpResult<MutableList<HotSearchBean>>>
    }
}
