package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.CoinInfoBean
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/11 4:07 PM
 * @description:
 */
interface RankContract {

    interface View : IView {
        fun showRankList(data : BaseListResponseBody<CoinInfoBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getRankList(page : Int)
    }

    interface Model : IModel {
        fun getRankList(page : Int) : Observable<HttpResult<BaseListResponseBody<CoinInfoBean>>>
    }
}