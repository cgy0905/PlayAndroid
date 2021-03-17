package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.UserScoreBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/11 3:05 PM
 * @description:
 */
interface ScoreContract {

    interface View : IView {
        fun showUserScoreList(body : BaseListResponseBody<UserScoreBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getUserScoreList(page : Int)
    }

    interface Model : IModel {
        fun getUserScoreList(page: Int) : Observable<HttpResult<BaseListResponseBody<UserScoreBean>>>
    }
}