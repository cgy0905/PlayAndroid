package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.RankContract
import com.cgy.wanandroid.mvp.model.RankModel

/**
 * @author: cgy
 * @date: 2021/3/11 5:01 PM
 * @description:
 */
class RankPresenter : BasePresenter<RankContract.Model, RankContract.View>(), RankContract.Presenter {

    override fun createModel(): RankContract.Model?  = RankModel()

    override fun getRankList(page: Int) {
        mModel?.getRankList(page)?.ss(mModel, mView) {
            mView?.showRankList(it.data)
        }
    }
}