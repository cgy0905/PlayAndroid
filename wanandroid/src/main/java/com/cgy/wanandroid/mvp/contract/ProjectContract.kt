package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/10 10:54 AM
 * @description:
 */
interface ProjectContract {

    interface View : IView {

        fun scrollToTop()

        fun setProjectTree(list: List<ProjectTreeBean>)
    }

    interface Presenter : IPresenter<View> {

        fun requestProjectTree()
    }

    interface Model : IModel {
        fun requestProjectTree() : Observable<HttpResult<List<ProjectTreeBean>>>
    }
}