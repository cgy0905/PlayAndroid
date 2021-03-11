package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.ScoreContract
import com.cgy.wanandroid.mvp.model.ScoreModel

/**
 * @author: cgy
 * @date: 2021/3/11 3:10 PM
 * @description:
 */
class ScorePresenter : BasePresenter<ScoreContract.Model, ScoreContract.View>(), ScoreContract.Presenter {

    override fun createModel(): ScoreContract.Model? = ScoreModel()

    override fun getUserScoreList(page: Int) {
        mModel?.getUserScoreList(page)?.ss(mModel, mView) {
            mView?.showUserScoreList(it.data)
        }
    }

}