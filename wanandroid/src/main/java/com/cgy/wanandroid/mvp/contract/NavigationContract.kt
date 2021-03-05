package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 5:05 PM
 * @description:
 */
interface NavigationContract {

    interface View : IView {
        fun setNavigationData(list: List<NavigationBean>)
    }

    interface Presenter : IPresenter<View> {
        fun requestNavigationList()
    }

    interface Model : IModel {
        fun requestNavigationList(): Observable<HttpResult<List<NavigationBean>>>
    }
}