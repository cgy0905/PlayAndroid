package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 4:26 PM
 * @description:
 */
interface KnowledgeTreeContract {

    interface View : IView {

        fun scrollToTop()

        fun setKnowledgeTree(lists : List<KnowledgeTreeBody>)
    }

    interface Presenter : IPresenter<View> {

        fun requestKnowledgeTree()

    }

    interface Model : IModel {

        fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>>

    }
}